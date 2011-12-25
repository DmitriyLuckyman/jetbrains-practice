package com.jetbrains.au.jslintplugin.js.error.processor.fix;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.options.ShowSettingsUtil;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import com.jetbrains.au.jslintplugin.JsLintValidatorComponent;
import com.jetbrains.au.jslintplugin.js.error.ErrorBeanWrapper;
import org.jetbrains.annotations.NotNull;

/**
 * User: Dmitriy Bandurin
 * Date: 17.11.11
 */
public class OpenConfigurationFix extends AbstractFix{
    private String relatedOption;

    public OpenConfigurationFix(@NotNull final ErrorBeanWrapper beanWrapper) {
        relatedOption = beanWrapper.getProcessor().getRelatedOption(beanWrapper);
    }

    @NotNull
    public String getText() {
        return "Open JSLint plugin configuration";
    }

    @Override
    public void fix(@NotNull Project project, Editor editor, PsiFile psiFile) {
        final JsLintValidatorComponent component = ApplicationManager.getApplication().getComponent(JsLintValidatorComponent.class);
        ShowSettingsUtil.getInstance().editConfigurable(project, component, new Runnable() {
            public void run() {
                if (relatedOption != null) {
                    component.setOptionToHiglight(relatedOption);
                }
            }
        });
    }
}
