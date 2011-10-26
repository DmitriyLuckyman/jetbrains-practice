package com.jetbrains.au.jslintplugin;

import java.util.List;

/**
 * User: Dmitriy Bandurin
 * Date: 26.10.11
 * Time: 18:31
 */
public class OptionType<Type> {
    public static final OptionType<Boolean> BOOLEAN = new OptionType<Boolean>();
    public static final OptionType<Integer> NUMBER = new OptionType<Integer>();
    public static final OptionType<List<String>> STRING_ARRAY = new OptionType<List<String>>();

    private OptionType() {
    }
}
