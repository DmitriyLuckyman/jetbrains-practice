package com.jetbrains.au.jslintplugin.js.error.processor.fix;

import com.intellij.codeInsight.daemon.DaemonCodeAnalyzer;
import com.intellij.codeInsight.intention.AbstractIntentionAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;

/**
 * User: Dmitriy Bandurin
 * Date: 26.11.11
 */
public abstract class AbstractFix extends AbstractIntentionAction {

    public void invoke(@NotNull Project project, Editor editor, PsiFile psiFile) throws IncorrectOperationException {
        fix(project, editor, psiFile);
        DaemonCodeAnalyzer.getInstance(project).restart(psiFile);
    }

    public abstract void fix(@NotNull Project project, Editor editor, PsiFile psiFile);
}