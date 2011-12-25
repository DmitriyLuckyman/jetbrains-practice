package com.jetbrains.au.jslintplugin.js.error.processor;

import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.openapi.util.TextRange;
import com.jetbrains.au.jslintplugin.js.error.ErrorBeanWrapper;
import com.jetbrains.au.jslintplugin.js.error.processor.fix.OpenConfigurationFix;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Dmitriy Bandurin
 * Date: 16.11.11
 * Time: 20:38
 */
public class DefaultProcessor implements ErrorProcessor {
    @NotNull
    public TextRange getSelectionRange(@NotNull String text, int offset, @NotNull ErrorBeanWrapper errorWrapper) {
        final TextRange textRange;
        if(errorWrapper.getEvidence() != null){
            final int startIndex = text.indexOf(errorWrapper.getEvidence(), offset);
            if (startIndex != -1) {
                final int endOffset = startIndex + errorWrapper.getEvidence().length();
                textRange = new TextRange(startIndex, endOffset);
            } else {
                textRange = new TextRange(offset, offset);
            }
        } else {
            int startIndex = offset + errorWrapper.getCharacter() - 1;
            int endIndex = offset + errorWrapper.getCharacter();
            textRange = new TextRange(startIndex, endIndex);
        }
        return textRange;
    }

    @Nullable
    public String getRelatedOption(@NotNull ErrorBeanWrapper errorWrapper) {
        return null;
    }

    @NotNull
    public String getMessage(@NotNull ErrorBeanWrapper errorBeanWrapper) {
        return errorBeanWrapper.toString();
    }

    @NotNull
    public List<IntentionAction> getFixes(ErrorBeanWrapper errorWrapper) {
        List<IntentionAction> fixes  = new ArrayList<IntentionAction>();
        fixes.add(new OpenConfigurationFix(errorWrapper));
        return fixes;
    }
}
