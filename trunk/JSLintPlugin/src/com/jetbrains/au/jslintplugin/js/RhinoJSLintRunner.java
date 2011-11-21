package com.jetbrains.au.jslintplugin.js;

import com.jetbrains.au.jslintplugin.config.Option;
import com.jetbrains.au.jslintplugin.js.error.ErrorBean;
import org.jetbrains.annotations.NotNull;
import org.mozilla.javascript.*;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * User: Dmitriy Bandurin
 */
public class RhinoJSLintRunner implements JSLintRunner {
    private static final String JS_LINT_SOURCE_PATH = "com/jetbrains/au/jslintplugin/js/jslint.js";
    private String jsLintSourceString;

    public RhinoJSLintRunner() throws IOException {
        URL resource = RhinoJSLintRunner.class.getClassLoader().getResource(JS_LINT_SOURCE_PATH);
        jsLintSourceString = getSourceAsString(resource.openStream());
    }

    public List<ErrorBean> validateScriptFile(@NotNull String scriptFileName, @NotNull List<Option> options) throws IOException {
          return validateScriptInputStream(new FileInputStream(scriptFileName), options);
    }

    public List<ErrorBean> validateScriptInputStream(@NotNull InputStream scriptInputStream, @NotNull List<Option> options) throws IOException {
        return validateScriptString(getSourceAsString(scriptInputStream), options);
    }

    public List<ErrorBean> validateScriptString(@NotNull String scriptString, @NotNull List<Option> options) throws IOException {
        ArrayList<ErrorBean> result = new ArrayList<ErrorBean>();
        Context cx = Context.enter();
        try {
            Scriptable scope = cx.initStandardObjects();
            Function jsLint = getJSLintFunction(cx, scope);
            Object functionArgs[] = {scriptString, convertToNativeObject(options)};
            Object status = jsLint.call(cx, scope, scope, functionArgs);
            Boolean noErrors = (Boolean) Context.jsToJava(status, Boolean.class);
            if (!noErrors) {
                for (Object error : ((NativeArray) jsLint.get("errors", scope)).toArray()) {
                    final ErrorBean e = ErrorBean.create((NativeObject) error);
                    if(e.getReason() != null){
                        result.add(e);
                    }
                }
            }
        } finally {
            Context.exit();
        }
        return result;
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

    private Function getJSLintFunction(@NotNull Context cx, @NotNull Scriptable scope) throws IOException {
        cx.evaluateString(scope, jsLintSourceString, "jslint", 1, null);
        Object jsLint = scope.get("JSLINT", scope);
        if (!(jsLint instanceof Function)) {
            throw new RuntimeException("JSLINT is undefined or not a function.");
        }
        return (Function) jsLint;
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
