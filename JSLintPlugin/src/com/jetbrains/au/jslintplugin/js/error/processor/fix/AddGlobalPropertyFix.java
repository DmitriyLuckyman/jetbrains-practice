package com.jetbrains.au.jslintplugin.js.error.processor.fix;

import com.intellij.codeInsight.intention.AbstractIntentionAction;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import com.intellij.util.IncorrectOperationException;
import com.jetbrains.au.jslintplugin.JsLintValidatorComponent;
import com.jetbrains.au.jslintplugin.config.JsLintOption;
import com.jetbrains.au.jslintplugin.config.JsLintState;
import com.jetbrains.au.jslintplugin.js.error.ErrorBean;
import org.jetbrains.annotations.NotNull;

/**
 * User: Dmitriy Bandurin
 * Date: 18.11.11
 */
public class AddGlobalPropertyFix extends AbstractIntentionAction {
    private ErrorBean errorBean;

    public AddGlobalPropertyFix(ErrorBean errorBean) {
        this.errorBean = errorBean;
    }

    @NotNull
    public String getText() {
        return "Mark \"" + errorBean.getA() + "\" as predefined";
    }

    public void invoke(@NotNull Project project, Editor editor, PsiFile psiFile) throws IncorrectOperationException {
        final JsLintValidatorComponent component = ApplicationManager.getApplication().getComponent(JsLintValidatorComponent.class);
        final JsLintState state = component.getState();
        final String s = state.options.get(JsLintOption.PREDEF.getName());
        if(!s.contains(errorBean.getA())){
            state.options.put(JsLintOption.PREDEF.getName(), String.format("%s,%s", s, errorBean.getA()));
        }
        ApplicationManager.getApplication().saveSettings();
        //TODO: needs to restart inspection.
    }
}
