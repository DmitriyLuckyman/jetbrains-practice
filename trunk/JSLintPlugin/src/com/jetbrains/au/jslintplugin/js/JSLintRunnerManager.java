package com.jetbrains.au.jslintplugin.js;

import java.util.HashSet;
import java.util.Set;

/**
 * User: Dmitriy Bandurin
 * Date: 31.10.11
 * Time: 14:12
 */
public class JSLintRunnerManager {
    private JSLintRunner runner;
    private static Set<Class<? extends JSLintRunner>> runners = new HashSet<Class<? extends JSLintRunner>>();
    static {
        runners.add(RhinoJSLintRunner.class);
    }
    private static final JSLintRunnerManager instance = new JSLintRunnerManager();

    public JSLintRunnerManager() {
        this.runner = new RhinoJSLintRunner();
    }

    public static JSLintRunnerManager getInstance() {
        return instance;
    }

    public JSLintRunner getRunner() {
        return runner;
    }

    public void setRunner(JSLintRunner runner) {
        this.runner = runner;
    }

    public Set<Class<? extends JSLintRunner>> getAvailableRunners(){
        return runners;
    }
}
