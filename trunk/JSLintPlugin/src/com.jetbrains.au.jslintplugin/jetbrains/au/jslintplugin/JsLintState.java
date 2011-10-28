package com.jetbrains.au.jslintplugin;

import com.intellij.util.xmlb.annotations.Tag;

import java.util.Map;

/**
 * User: Dmitriy Bandurin
 * Date: 28.10.11
 * Time: 15:48
 */
public class JsLintState {

    @Tag
    public Map<String, Option> options;

}
