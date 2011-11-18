package com.jetbrains.au.jslintplugin.js.error;

import com.jetbrains.au.jslintplugin.js.error.processor.ErrorProcessor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mozilla.javascript.NativeObject;

/**
 * User: Dmitriy Bandurin
 * Date: 26.10.11
 * Time: 15:34
 */
public class ErrorBean {

    public ErrorProcessor getProcessor(){
        return ErrorBeanHelper.getProcessor(this);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append( reason )
                .append("(").append(line).append(":").append(character).append(")");
                if(evidence != null){
                    builder.append("\n evidence='").append(evidence).append("'");
                }
        return builder.toString();
    }

    /**
     * The line (relative to 0) at which the lint was found
     */
    private int line;
    /**
     * The character (relative to 0) at which the lint was found
     */
    private int character;
    /**
     * The problem
     */
    private String reason;
    /**
     * The text line in which the problem occurred
     */
    private String evidence;
    /**
     * The raw message before the details were inserted
     */
    private String raw;
    /**
     * The first detail
     */
    private String a;
    /**
     * The second detail
     */
    private String b;
    /**
     * The third detail
     */
    private String c;
    /**
     * The fourth detail
     */
    private String d;

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getCharacter() {
        return character;
    }

    public void setCharacter(int character) {
        this.character = character;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getEvidence() {
        return evidence;
    }

    public void setEvidence(String evidence) {
        this.evidence = evidence;
    }

    public String getRaw() {
        return raw;
    }

    public void setRaw(String raw) {
        this.raw = raw;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    @NotNull
    public static ErrorBean create(NativeObject error) {
        ErrorBean bean = new ErrorBean();
        if(error != null){
            Object lineNumber = error.get("line");
            if(lineNumber instanceof Double){
                bean.line = ((Double) lineNumber).intValue();
            }

            Object charPosition = error.get("character");
            if(charPosition instanceof Double){
                bean.character = ((Double) charPosition).intValue();
            }

            bean.reason = (String) error.get("reason");
            bean.evidence = (String) error.get("evidence");
            bean.raw = String.valueOf(error.get("raw"));
            bean.a = getNullableStringValue(error.get("a"));
            bean.b = getNullableStringValue(error.get("b"));
            bean.c = getNullableStringValue(error.get("c"));
            bean.d = getNullableStringValue(error.get("d"));
        }
        return bean;
    }

    @Nullable
    private static String getNullableStringValue(@Nullable final Object object){
        return object == null ? null : String.valueOf(object);
    }
}
