package com.jetbrains.au.jslintplugin;


import com.intellij.util.xmlb.annotations.Transient;

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

    @Transient
    public E getValue() {
        return value;
    }

    public OptionType getOption() {
        return option.getType();
    }

    public void setValue(E value) {
        this.value = value;
    }
}
