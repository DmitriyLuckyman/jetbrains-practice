package com.jetbrains.au.jslintplugin;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;

/**
 * User: luckyman
 */
public class HelloAction extends AnAction {
    public void actionPerformed(AnActionEvent e) {
        Application application = ApplicationManager.getApplication();
        JsLintValidatorComponent validator = application.getComponent(JsLintValidatorComponent.class);
        validator.sayHello();
    }
}
