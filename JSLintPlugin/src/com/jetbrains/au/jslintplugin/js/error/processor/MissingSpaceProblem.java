package com.jetbrains.au.jslintplugin.js.error.processor;

import com.intellij.openapi.util.TextRange;
import com.jetbrains.au.jslintplugin.config.JsLintOption;
import com.jetbrains.au.jslintplugin.js.error.ErrorBean;
import org.jetbrains.annotations.NotNull;

/**
 * User: Dmitriy Bandurin
 * Date: 17.11.11
 * Time: 15:23
 */
public class MissingSpaceProblem extends DefaultProcessor {
    @NotNull
    public TextRange getSelectionRange(@NotNull String text, int offset, @NotNull ErrorBean error) {
        final int startOffset = text.substring(0, offset + error.getCharacter()).lastIndexOf(error.getA());
        final int endIndex = text.indexOf(error.getB(), startOffset) + 1;
        return new TextRange(startOffset, endIndex);
    }

    public String getRelatedOption(@NotNull ErrorBean error) {
        return JsLintOption.WHITE.getName();
    }

    @NotNull
    public String getMessage(@NotNull ErrorBean errorBean) {
        return errorBean.getReason();
    }
}
