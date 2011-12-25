package com.jetbrains.au.jslintplugin.js;

import com.jetbrains.au.jslintplugin.config.Option;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * User: Dmitriy Bandurin
 * Date: 31.10.11
 * Time: 14:05
 */
public interface JSLintRunner {
    /**
     *
     *
     *
     * @param scriptFileName script to validation
     * @param options jsLint options for validation
     * @return List of found errors
     * @throws IOException if can't read script from file
     */
    public Object[] validateScriptFile(@NotNull String scriptFileName, @NotNull List<Option> options) throws IOException;

    /**
     *
     *
     *
     * @param scriptString script to validation
     * @param options jsLint options for validation
     * @return List of found errors
     * @throws IOException if can't read script from file
     */
    public Object[] validateScriptString(@NotNull String scriptString, @NotNull List<Option> options) throws IOException;

    /**
     *
     *
     *
     * @param scriptInputStream inputStream with script to validation
     * @param options jsLint options for validation
     * @return List of found errors
     * @throws IOException if can't read script from file
     */
    public Object[] validateScriptInputStream(@NotNull InputStream scriptInputStream, @NotNull List<Option> options) throws IOException;

    /**
     *
     * @return describe the js-engine
     */
    public String getEngineDescription();
}
