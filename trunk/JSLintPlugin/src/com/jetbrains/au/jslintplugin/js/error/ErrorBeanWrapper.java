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
public class ErrorBeanWrapper {
    private NativeObject error;

    public void setError(NativeObject error) {
        this.error = error;
    }

    public ErrorProcessor getProcessor(){
        return ErrorBeanHelper.getProcessor(this);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append( getReason() )
                .append("(").append(getLine()).append(":").append(getCharacter()).append(")");
                if(getEvidence() != null){
                    builder.append("\n evidence='").append(getEvidence()).append("'");
                }
        return builder.toString();
    }
    
    /**
     *@return The line (relative to 0) at which the lint was found
     */    
    public Integer getLine() {
        return getIntValue("line");
    }

    /**
     *@return The character (relative to 0) at which the lint was found
     */
    public Integer getCharacter() {
        return getIntValue("character");
    }
        
    /**
     *@return The problem
     */
    public String getReason() {
        return getStringValue("reason");
    }

    /**
     *@return The text line in which the problem occurred
     */
    public String getEvidence() {
        return getStringValue("evidence");
    }
    
    /**
     *@return The raw message before the details were inserted
     */
    public String getRaw() {
        return getStringValue("raw");
    }

    /**
     * @return The first detail
     */
    public String getA() {
        return getStringValue("a");
    }

    /**
     * @return The second detail
     */
    public String getB() {
        return getStringValue("b");
    }

    /**
     * @return The third detail
     */
    public String getC() {
        return getStringValue("c");
    }

    /**
     * @return The fourth detail
     */
    public String getD() {
        return getStringValue("d");
    }

    @Nullable
    private String getStringValue(@NotNull final String field){
        final Object o = error.get(field);
        return o == null ? null : String.valueOf(o);
    }
    @Nullable
    private Integer getIntValue(@NotNull final String field){
        final Object o = error.get(field);
        return o instanceof Double ?  ((Double) o).intValue() : null;
    }

    public Object[] getErrorTableView(int orderNumber) {
        return new Object[]{orderNumber, getLine(), getCharacter(), getReason()};
    }
}
