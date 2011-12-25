package com.jetbrains.au.jslintplugin;

import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.ExternalAnnotator;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.psi.PsiFile;
import com.jetbrains.au.jslintplugin.js.JSLintRunner;
import com.jetbrains.au.jslintplugin.js.JSLintRunnerManager;
import com.jetbrains.au.jslintplugin.js.error.ErrorBeanWrapper;
import com.jetbrains.au.jslintplugin.js.error.processor.ErrorProcessor;
import org.jetbrains.annotations.NotNull;
import org.mozilla.javascript.NativeObject;

import java.io.IOException;

/**
 * User: Dmitriy Bandurin
 * Date: 31.10.11
 * Time: 15:36
 */
public class JsLintExternalAnnotator extends ExternalAnnotator {

    public void annotate(@NotNull final PsiFile psiFile, @NotNull final AnnotationHolder annotationHolder) {
        final String text = psiFile.getText();
        final JSLintRunner runner = JSLintRunnerManager.getInstance().getRunner();
        final Application application = ApplicationManager.getApplication();
        final JsLintValidatorComponent validator = application.getComponent(JsLintValidatorComponent.class);
        try {
            final Object[] errorBeans = runner.validateScriptString(text, validator.getJsLintOptions());
            int currentLine = 1;
            int currentOffset = 0;
            ErrorBeanWrapper errorBean = new ErrorBeanWrapper();
            for (final Object error : errorBeans) {
                if(error != null){
                    errorBean.setError((NativeObject) error);
                    if (errorBean.getLine() != 0) {
                        final int line = errorBean.getLine();
                        while (currentLine < line) {
                            currentOffset = text.indexOf("\n", currentOffset) + 1;
                            currentLine++;
                        }
                        final ErrorProcessor processor = errorBean.getProcessor();
                        final Annotation annotation = annotationHolder.createWarningAnnotation(
                                processor.getSelectionRange(text, currentOffset, errorBean),
                                processor.getMessage(errorBean));

                        for (IntentionAction intentionAction : processor.getFixes(errorBean)) {
                            annotation.registerFix(intentionAction);
                            annotation.setNeedsUpdateOnTyping(true);
                        }
                    }
                }
            }
        } catch (IOException e) {
            //ignore
        }
    }
}
