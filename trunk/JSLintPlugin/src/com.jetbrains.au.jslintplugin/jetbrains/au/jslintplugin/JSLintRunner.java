package com.jetbrains.au.jslintplugin;

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
     /*   options.add(new Option("sloppy", true));
        options.add(new Option("rhino", true));
        options.add(new Option("vars", true));
        options.add(new Option("maxerr", 1000));
        NativeArray predef = new NativeArray(new String[]{"Ext", "Sigma", "console"});
        options.add(new Option("predef", predef));*/
        execute(args[0], options);
    }

    public static List<ErrorBean> execute(String scriptFileName, List<Option> options) throws IOException {
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
            object.defineProperty(option.getName(), option.getValue(), ScriptableObject.READONLY);
        }
        return object;
    }

    private static Object getJSLintFunction(Context cx, Scriptable scope) throws IOException {
        URL resource = Thread.currentThread().getContextClassLoader().getResource("com/jetbrains/au/jslintplugin/jslint.js");
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

    private static String getSourceAsString(String scriptFileName) throws IOException {
        BufferedInputStream scriptBufferedInputStream = null;
        try {
            scriptBufferedInputStream = new BufferedInputStream(new FileInputStream(scriptFileName));
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
