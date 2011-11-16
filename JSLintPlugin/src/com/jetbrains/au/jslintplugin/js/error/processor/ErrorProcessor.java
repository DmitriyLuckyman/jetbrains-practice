package com.jetbrains.au.jslintplugin.js.error.processor;

import com.intellij.openapi.util.TextRange;
import com.jetbrains.au.jslintplugin.js.error.ErrorBean;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * User: Dmitriy Bandurin
 * Date: 16.11.11
 * Time: 20:07
 */
public interface ErrorProcessor {
    @NotNull public TextRange getSelectionRange(@NotNull final String text, final int offset, @NotNull ErrorBean error);
    @Nullable public String getRelatedOption(@NotNull ErrorBean error);
    @NotNull String getMessage(@NotNull final ErrorBean errorBean);
}
