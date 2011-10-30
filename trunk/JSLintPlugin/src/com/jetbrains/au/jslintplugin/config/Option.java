package com.jetbrains.au.jslintplugin.config;


/**
 * User: Dmitriy Bandurin
 */
public class Option {
    private JsLintOption option;
    private String value;

    public Option(JsLintOption jsLintOption, String value) {
        this.option = jsLintOption;
        this.value = value;
    }

    public String getName() {
        return option.getName();
    }

    public String getValue() {
        return value;
    }

    public OptionType getOption() {
        return option.getType();
    }

    public void setValue(String value) {
        this.value = value;
    }
}
