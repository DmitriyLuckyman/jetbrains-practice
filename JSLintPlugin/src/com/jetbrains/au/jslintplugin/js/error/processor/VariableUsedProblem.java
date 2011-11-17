package com.jetbrains.au.jslintplugin.js.error.processor;

import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.openapi.util.TextRange;
import com.jetbrains.au.jslintplugin.js.error.ErrorBean;
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
    public TextRange getSelectionRange(@NotNull String text, int offset, @NotNull ErrorBean error) {
        final int startIndex = text.indexOf(error.getA(), offset);
        return new TextRange(startIndex, startIndex + error.getA().length());
    }

    @Nullable public String getRelatedOption(@NotNull ErrorBean error) {
        return null;
    }

    @NotNull
    public String getMessage(@NotNull ErrorBean errorBean) {
        return errorBean.getReason();
    }

    @NotNull
    @Override
    public List<IntentionAction> getFixes(ErrorBean error) {
        final List<IntentionAction> fixes = super.getFixes(error);
        fixes.add(new AddGlobalPropertyFix(error));
        return fixes;
    }
}
