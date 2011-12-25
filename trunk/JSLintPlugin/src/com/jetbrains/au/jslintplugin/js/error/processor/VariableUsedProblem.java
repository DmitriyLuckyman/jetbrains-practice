package com.jetbrains.au.jslintplugin.js.error.processor;

import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.openapi.util.TextRange;
import com.jetbrains.au.jslintplugin.js.error.ErrorBeanWrapper;
import com.jetbrains.au.jslintplugin.js.error.processor.fix.AddGlobalPropertyFix;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * User: Dmitriy Bandurin
 * Date: 16.11.11
 * Time: 20:14
 */
public class VariableUsedProblem extends DefaultProcessor  {
    @NotNull
    public TextRange getSelectionRange(@NotNull String text, int offset, @NotNull ErrorBeanWrapper errorWrapper) {
        final int startIndex = text.indexOf(errorWrapper.getA(), offset);
        return new TextRange(startIndex, startIndex + errorWrapper.getA().length());
    }

    @Nullable public String getRelatedOption(@NotNull ErrorBeanWrapper errorWrapper) {
        return null;
    }

    @NotNull
    public String getMessage(@NotNull ErrorBeanWrapper errorBeanWrapper) {
        return errorBeanWrapper.getReason();
    }

    @NotNull
    @Override
    public List<IntentionAction> getFixes(ErrorBeanWrapper errorWrapper) {
        final List<IntentionAction> fixes = super.getFixes(errorWrapper);
        fixes.add(new AddGlobalPropertyFix(errorWrapper));
        return fixes;
    }
}
