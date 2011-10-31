package com.jetbrains.au.jslintplugin;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.ExternalAnnotator;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.fileTypes.StdFileTypes;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiFile;
import com.jetbrains.au.jslintplugin.js.ErrorBean;
import com.jetbrains.au.jslintplugin.js.JSLintRunner;
import com.jetbrains.au.jslintplugin.js.JSLintRunnerManager;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

/**
 * User: Dmitriy Bandurin
 * Date: 31.10.11
 * Time: 15:36
 */
public class JsLintExternalAnnotator implements ExternalAnnotator{
    public void annotate(@NotNull final PsiFile psiFile, @NotNull final AnnotationHolder annotationHolder) {
        if(StdFileTypes.JS.equals(psiFile.getFileType())){
            String text = psiFile.getText();
            JSLintRunner runner = JSLintRunnerManager.getInstance().getRunner();
            Application application = ApplicationManager.getApplication();
            JsLintValidatorComponent validator = application.getComponent(JsLintValidatorComponent.class);
            try {
                List<ErrorBean> errorBeans = runner.validateScriptString(text, validator.getJsLintOptions());
                String[] split = text.split("\n");
                int[] offsets = new int[split.length + 1];
                offsets[0] = 0;
                offsets[1] = 0;
                for (int lineNumber = 2; lineNumber <= split.length; lineNumber++ ) {
                    offsets[lineNumber] = split[lineNumber - 2].length() + offsets[lineNumber - 1] + 1;
                }

                for (ErrorBean errorBean : errorBeans) {
                    if(errorBean.getEvidence() != null){
                        int startIndex = text.indexOf(errorBean.getEvidence(), offsets[errorBean.getLine()]);
                        if(startIndex != -1){
                            int endOffset = startIndex + errorBean.getEvidence().length();
                            TextRange textRange = new TextRange(startIndex, endOffset);
                            annotationHolder.createWarningAnnotation(textRange, errorBean.toString());
                        }
                    }
                }
            } catch (IOException e) {
                //ignore
            }
        }
    }
}
