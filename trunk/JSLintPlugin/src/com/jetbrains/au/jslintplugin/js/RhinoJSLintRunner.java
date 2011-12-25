package com.jetbrains.au.jslintplugin.js;

import com.jetbrains.au.jslintplugin.config.Option;
import org.jetbrains.annotations.NotNull;
import org.mozilla.javascript.*;
import java.io.*;
import java.net.URL;
import java.util.List;


/**
 * User: Dmitriy Bandurin
 */
public class RhinoJSLintRunner implements JSLintRunner {
    private static final String JS_LINT_SOURCE_PATH = "com/jetbrains/au/jslintplugin/js/jslint.js";
    private String jsLintSourceString;
    private Function jsLintFunction;

    public RhinoJSLintRunner() throws IOException {
        URL resource = RhinoJSLintRunner.class.getClassLoader().getResource(JS_LINT_SOURCE_PATH);
        jsLintSourceString = getSourceAsString(resource.openStream());
        jsLintFunction = compile(9);
    }

    public Object[] validateScriptFile(@NotNull String scriptFileName, @NotNull List<Option> options) throws IOException {
          return validateScriptInputStream(new FileInputStream(scriptFileName), options);
    }

    public Object[] validateScriptInputStream(@NotNull InputStream scriptInputStream, @NotNull List<Option> options) throws IOException {
        return validateScriptString(getSourceAsString(scriptInputStream), options);
    }

    public Object[] validateScriptString(@NotNull String scriptString, @NotNull List<Option> options) throws IOException {
        Context cx = Context.enter();
        try {
            Scriptable scope = cx.initStandardObjects();
            Object functionArgs[] = {scriptString, convertToNativeObject(options)};
            Object status = jsLintFunction.call(cx, scope, scope, functionArgs);
            Boolean noErrors = (Boolean) Context.jsToJava(status, Boolean.class);
            if (!noErrors) {
                return ((NativeArray) jsLintFunction.get("errors", scope)).toArray();
            }
            return null;
        } finally {
            Context.exit();
        }
    }

    public String getEngineDescription() {
        return "Rhino";
    }

    private NativeObject convertToNativeObject(@NotNull List<Option> options) {
        NativeObject object = new NativeObject();
        for (Option option : options) {
            Object value;
            switch (option.getOption()){
                case BOOLEAN:
                    value = Boolean.parseBoolean(option.getValue());
                    break;
                case STRING_ARRAY:
                    value = new NativeArray(option.getValue().split(","));
                    break;
                case NUMBER:
                    value = Integer.valueOf(option.getValue());
                    break;
                default:
                    value = option.getValue();
                    break;
            }
            object.defineProperty(option.getName(), value, ScriptableObject.READONLY);
        }
        return object;
    }

    public  Function compile(int optimizationLevel) {
        Context context = Context.enter();
        try {
            context.setOptimizationLevel(optimizationLevel);
            Script script = context.compileString(jsLintSourceString, "<jslint>", 1, null);
            Scriptable scope = context.initStandardObjects();
            script.exec(context, scope);
            Object jsLintObj = scope.get("JSLINT", scope);
            if (jsLintObj instanceof Function) {
                return (Function) jsLintObj;
            } else {
                throw new RuntimeException("JSLINT is undefined or not a function.");
            }
        } finally {
            Context.exit();
        }
    }

    private String getSourceAsString(@NotNull InputStream script) throws IOException {
        BufferedInputStream scriptBufferedInputStream = null;
        try {
            scriptBufferedInputStream = new BufferedInputStream(script);
            StringBuilder scriptSBuilder = new StringBuilder();
            byte[] buf = new byte[1000];
            int count;
            while ((count = scriptBufferedInputStream.read(buf)) != -1) {
                scriptSBuilder.append(new String(buf, 0, count));
            }
            return scriptSBuilder.toString();
        } finally {
            if(scriptBufferedInputStream != null){
                //try to close input
                try {
                    scriptBufferedInputStream.close();
                } catch (Throwable t){
                    //ignore
                }
            }
        }
    }
}
