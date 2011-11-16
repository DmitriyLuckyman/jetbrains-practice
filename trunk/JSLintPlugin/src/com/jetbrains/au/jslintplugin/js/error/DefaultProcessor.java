package com.jetbrains.au.jslintplugin.js.error;

import com.intellij.openapi.util.TextRange;
import com.jetbrains.au.jslintplugin.js.error.processor.ErrorProcessor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * User: Dmitriy Bandurin
 * Date: 16.11.11
 * Time: 20:38
 */
public class DefaultProcessor implements ErrorProcessor {
    @NotNull
    public TextRange getSelectionRange(@NotNull String text, int offset, @NotNull ErrorBean error) {
        final TextRange textRange;
        final int startIndex = text.indexOf(error.getEvidence(), offset);
        if (startIndex != -1) {
            final int endOffset = startIndex + error.getEvidence().length();
            textRange = new TextRange(startIndex, endOffset);
        } else {
            textRange = new TextRange(offset, offset);
        }
        return textRange;
    }

    @Nullable
    public String getRelatedOption(@NotNull ErrorBean error) {
        return null;
    }

    @NotNull
    public String getMessage(@NotNull ErrorBean errorBean) {
        return errorBean.toString();
    }
}
