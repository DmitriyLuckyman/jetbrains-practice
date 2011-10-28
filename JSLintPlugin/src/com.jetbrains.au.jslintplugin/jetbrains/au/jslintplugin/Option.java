package com.jetbrains.au.jslintplugin;

/**
 * User: Dmitriy Bandurin
 */
public class Option<E> {
    private JsLintOption option;
    private E value;

    public Option(JsLintOption<E> jsLintOption, E value) {
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

    public E getValue() {
        return OptionType.STRING_ARRAY.equals(option.getType()) ? value : value;
    }

    public OptionType getOption() {
        return option.getType();
    }

    public void setValue(E value) {
        this.value = value;
    }
}
