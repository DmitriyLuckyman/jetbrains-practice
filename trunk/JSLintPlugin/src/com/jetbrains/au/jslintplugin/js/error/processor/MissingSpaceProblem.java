package com.jetbrains.au.jslintplugin.js.error.processor;

import com.intellij.openapi.util.TextRange;
import com.jetbrains.au.jslintplugin.js.error.ErrorBean;
import org.jetbrains.annotations.NotNull;

/**
 * User: Dmitriy Bandurin
 * Date: 17.11.11
 * Time: 15:23
 */
public class MissingSpaceProblem implements ErrorProcessor{
    @NotNull
    public TextRange getSelectionRange(@NotNull String text, int offset, @NotNull ErrorBean error) {
        final int startOffset = offset + error.getCharacter() - 2;
        final int endIndex = text.indexOf(error.getB(), startOffset) + 1;
        return new TextRange(startOffset, endIndex);
    }

    public String getRelatedOption(@NotNull ErrorBean error) {
        return null;
    }

    @NotNull
    public String getMessage(@NotNull ErrorBean errorBean) {
        return errorBean.getReason();
    }
}
