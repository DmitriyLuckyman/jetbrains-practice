package com.jetbrains.au.jslintplugin.js.error.processor.fix;

import com.intellij.codeInsight.intention.AbstractIntentionAction;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.options.ShowSettingsUtil;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import com.intellij.util.IncorrectOperationException;
import com.jetbrains.au.jslintplugin.JsLintValidatorComponent;
import com.jetbrains.au.jslintplugin.js.error.ErrorBean;
import org.jetbrains.annotations.NotNull;

/**
 * User: Dmitriy Bandurin
 * Date: 17.11.11
 */
public class OpenConfigurationFix extends AbstractIntentionAction{
    ErrorBean errorBean;

    public OpenConfigurationFix(@NotNull final ErrorBean bean) {
        errorBean = bean;
    }

    @NotNull
    public String getText() {
        return "Open JSLint plugin configuration";
    }

    public void invoke(@NotNull Project project, Editor editor, PsiFile psiFile) throws IncorrectOperationException {
        final JsLintValidatorComponent component = ApplicationManager.getApplication().getComponent(JsLintValidatorComponent.class);
        ShowSettingsUtil.getInstance().editConfigurable(project, component, new Runnable() {
            public void run() {
                final String relatedOption = errorBean.getProcessor().getRelatedOption(errorBean);
                if (relatedOption != null) {
                    component.setOptionToHiglight(relatedOption);
                }
            }
        });
    }
}
