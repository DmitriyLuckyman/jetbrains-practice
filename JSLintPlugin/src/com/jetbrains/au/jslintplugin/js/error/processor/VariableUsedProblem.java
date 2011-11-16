package com.jetbrains.au.jslintplugin.js.error.processor;

import com.intellij.openapi.util.TextRange;
import com.jetbrains.au.jslintplugin.js.error.ErrorBean;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * User: Dmitriy Bandurin
 * Date: 16.11.11
 * Time: 20:14
 */
public class VariableUsedProblem implements ErrorProcessor {
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
}
