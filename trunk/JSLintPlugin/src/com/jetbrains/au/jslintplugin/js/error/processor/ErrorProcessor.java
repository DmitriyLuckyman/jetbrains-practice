package com.jetbrains.au.jslintplugin.js.error.processor;

import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.openapi.util.TextRange;
import com.jetbrains.au.jslintplugin.js.error.ErrorBean;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * User: Dmitriy Bandurin
 * Date: 16.11.11
 * Time: 20:07
 *
 * Main interface for processing errors from JSLint
 */
public interface ErrorProcessor {
    /**
     *
     * @param text verified text
     * @param offset current offset of error in text parameter
     * @param error information about find error
     * @return text range to highlight error in text
     */
    @NotNull
    TextRange getSelectionRange(@NotNull final String text, final int offset, @NotNull ErrorBean error);

    /**
     *
     * @param error error info
     * @return configuration option linked with error
     */
    @Nullable
    String getRelatedOption(@NotNull ErrorBean error);

    /**
     *
     * @param errorBean error info
     * @return message for annotation tooltip
     */
    @NotNull
    String getMessage(@NotNull final ErrorBean errorBean);

    /**
     *
     * @param error error info
     * @return list of available fix for error
     */
    @NotNull
    List<IntentionAction> getFixes(ErrorBean error);
}
