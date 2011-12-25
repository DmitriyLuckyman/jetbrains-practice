package com.jetbrains.au.jslintplugin.js.error.processor.fix;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import com.jetbrains.au.jslintplugin.JsLintValidatorComponent;
import com.jetbrains.au.jslintplugin.config.JsLintOption;
import com.jetbrains.au.jslintplugin.config.JsLintState;
import com.jetbrains.au.jslintplugin.js.error.ErrorBeanWrapper;
import org.jetbrains.annotations.NotNull;

/**
 * User: Dmitriy Bandurin
 * Date: 18.11.11
 */
public class AddGlobalPropertyFix extends AbstractFix {
    private String  globalProperty;
    private String text;

    public AddGlobalPropertyFix(ErrorBeanWrapper errorBeanWrapper) {
        this.globalProperty = errorBeanWrapper.getA();
        this.text = String.format("Mark \"%s\" as predefined", this.globalProperty);
    }

    @NotNull
    public String getText() {
        return text;
    }

    @Override
    public void fix(@NotNull Project project, Editor editor, PsiFile psiFile) {
        final JsLintValidatorComponent component = ApplicationManager.getApplication().getComponent(JsLintValidatorComponent.class);
        final JsLintState state = component.getState();
        final String s = state.options.get(JsLintOption.PREDEF.getName());
        if(!s.contains(globalProperty)) {
            state.options.put(JsLintOption.PREDEF.getName(), String.format("%s,%s", s, globalProperty));
        }
        ApplicationManager.getApplication().saveSettings();
    }
}
