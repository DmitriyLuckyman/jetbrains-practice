package com.jetbrains.au.jslintplugin;

import com.intellij.util.xmlb.annotations.MapAnnotation;

import java.util.Map;

/**
 * User: Dmitriy Bandurin
 * Date: 28.10.11
 * Time: 15:48
 */
public class JsLintState {

    @MapAnnotation(surroundKeyWithTag = true, surroundValueWithTag = true,
            surroundWithTag = true, entryTagName = "JsLintOption")
    public Map<String, String> options;

}
