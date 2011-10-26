package com.jetbrains.au.jslintplugin;

/**
 * User: Dmitriy Bandurin
 */
public class Option<E> {
    private JsLintOption option;
    private Object value;

    public Option(JsLintOption jsLintOption, Object value) {
        this.option = jsLintOption;
        this.value = value;
    }

    public Option() {
    }

    public String getName() {
        return option.getName();
    }

    public void setName(JsLintOption jsLintOption) {
        this.option = jsLintOption;
    }

    public Object getValue() {
        return OptionType.STRING_ARRAY.equals(option.getType()) ? value : value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
