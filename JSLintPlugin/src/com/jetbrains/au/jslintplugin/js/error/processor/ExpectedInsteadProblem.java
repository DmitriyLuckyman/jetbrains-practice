package com.jetbrains.au.jslintplugin.js.error.processor;

import com.intellij.openapi.util.TextRange;
import com.jetbrains.au.jslintplugin.config.JsLintOption;
import com.jetbrains.au.jslintplugin.js.error.ErrorBean;
import org.jetbrains.annotations.NotNull;

/**
 * User: Dmitriy Bandurin
 * Date: 17.11.11
 * Time: 16:02
 */
public class ExpectedInsteadProblem extends DefaultProcessor {
    @NotNull
    public TextRange getSelectionRange(@NotNull String text, int offset, @NotNull ErrorBean error) {
        int startIndex = text.indexOf(error.getB(), offset);
        if(startIndex == -1) {
            startIndex = offset + error.getCharacter() - 2;
        }

        int endOffset = startIndex + error.getB().length();
        if (endOffset >= text.length()){
            endOffset = text.length() - 1;
        }
        return new TextRange(startIndex, endOffset);
    }

    public String getRelatedOption(@NotNull ErrorBean error) {
        if("===".equals(error.getA()))
            return JsLintOption.EQEQ.getName();
        return null;
    }

    @NotNull
    public String getMessage(@NotNull ErrorBean errorBean) {
        return errorBean.getReason();
    }
}
