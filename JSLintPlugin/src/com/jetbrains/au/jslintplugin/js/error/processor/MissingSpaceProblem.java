package com.jetbrains.au.jslintplugin.js.error.processor;

import com.intellij.openapi.util.TextRange;
import com.jetbrains.au.jslintplugin.config.JsLintOption;
import com.jetbrains.au.jslintplugin.js.error.ErrorBeanWrapper;
import org.jetbrains.annotations.NotNull;

/**
 * User: Dmitriy Bandurin
 * Date: 17.11.11
 * Time: 15:23
 */
public class MissingSpaceProblem extends DefaultProcessor {
    @NotNull
    public TextRange getSelectionRange(@NotNull String text, int offset, @NotNull ErrorBeanWrapper errorWrapper) {
        final int startOffset = text.substring(0, offset + errorWrapper.getCharacter()).lastIndexOf(errorWrapper.getA());
        final int endIndex = text.indexOf(errorWrapper.getB(), startOffset) + 1;
        return new TextRange(startOffset, endIndex);
    }

    public String getRelatedOption(@NotNull ErrorBeanWrapper errorWrapper) {
        return JsLintOption.WHITE.getName();
    }

    @NotNull
    public String getMessage(@NotNull ErrorBeanWrapper errorBeanWrapper) {
        return errorBeanWrapper.getReason();
    }
}
