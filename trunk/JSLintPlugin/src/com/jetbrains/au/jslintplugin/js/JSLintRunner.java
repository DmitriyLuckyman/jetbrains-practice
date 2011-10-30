package com.jetbrains.au.jslintplugin.js;

import com.jetbrains.au.jslintplugin.config.JsLintOption;
import com.jetbrains.au.jslintplugin.config.Option;
import org.mozilla.javascript.*;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * User: Dmitriy Bandurin
 */
public class JSLintRunner {

    public static void main(String[] args) throws IOException {
        ArrayList<Option> options = new ArrayList<Option>();
        options.add(new Option(JsLintOption.SLOPPY, "true"));
        options.add(new Option(JsLintOption.RHINO, "true"));
        options.add(new Option(JsLintOption.VARS, "true"));
        options.add(new Option(JsLintOption.MAXERR, "1000"));
        options.add(new Option(JsLintOption.PREDEF, "Ext, Sigma, console"));
        execute(args[0], options);
    }

    public static List<ErrorBean> execute(String scriptFileName, List<Option> options) throws IOException {
          return execute(new FileInputStream(scriptFileName), options);
    }

    public static List<ErrorBean> execute(InputStream scriptFileName, List<Option> options) throws IOException {
        ArrayList<ErrorBean> result = new ArrayList<ErrorBean>();
        Context cx = Context.enter();
        try {
            Scriptable scope = cx.initStandardObjects();
            Function jsLint = (Function) getJSLintFunction(cx, scope);
            Object functionArgs[] = {getSourceAsString(scriptFileName), convertToNativeObject(options)};
            Object status = jsLint.call(cx, scope, scope, functionArgs);
            Boolean noErrors = (Boolean) Context.jsToJava(status, Boolean.class);
            if (!noErrors) {
                for (Object error : ((NativeArray) jsLint.get("errors", scope)).toArray()) {
                    result.add(ErrorBean.create((NativeObject) error));
                }
            }
        } finally {
            Context.exit();
        }
        return result;
    }

    private static NativeObject convertToNativeObject(List<Option> options) {
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

    private static Object getJSLintFunction(Context cx, Scriptable scope) throws IOException {
        URL resource = JSLintRunner.class.getClassLoader().getResource("com/jetbrains/au/jslintplugin/js/jslint.js");
        InputStreamReader jsLintReader = null;
        try{
            jsLintReader = new InputStreamReader(resource.openStream());
            cx.evaluateReader(scope, new BufferedReader(jsLintReader), "jslint", 1, null);
            Object jsLint = scope.get("JSLINT", scope);
            if (!(jsLint instanceof Function)) {
                throw new RuntimeException("JSLINT is undefined or not a function.");
            }
            return jsLint;
        } finally {
            if(jsLintReader != null){
                //try to close input
                try {
                    jsLintReader.close();
                } catch (Throwable t){
                    // ignore
                }
            }
        }
    }

    private static String getSourceAsString(InputStream script) throws IOException {
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
