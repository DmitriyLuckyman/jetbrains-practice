package com.jetbrains.au.jslintplugin;

import java.util.*;

/**
 * User: Dmitriy Bandurin
 * Date: 26.10.11
 * Time: 17:50
 */
public class JsLintOption<Type> {
    private final static HashMap<String, JsLintOption> options = new HashMap<String, JsLintOption>();
    /**
     * true, if ADsafe rules should be enforced
     */
    public static final JsLintOption<Boolean> ADSAFE = new JsLintOption<Boolean>("adsafe",
            "true, if ADsafe rules should be enforced", OptionType.BOOLEAN);
    /**
     * true, if bitwise operators should be allowed
     */
    public static final JsLintOption<Boolean> BITWISE = new JsLintOption<Boolean>("bitwise", "true, if bitwise operators should be allowed", OptionType.BOOLEAN);
    /**
     * true, if the standard browser globals should be predefined
     */
    public static final JsLintOption<Boolean> BROWSER = new JsLintOption<Boolean>("browser", "true, if the standard browser globals should be predefined", OptionType.BOOLEAN);
    /**
     * true, if upper case HTML should be allowed
     */
    public static final JsLintOption<Boolean> CAP = new JsLintOption<Boolean>("cap", "true, if upper case HTML should be allowed", OptionType.BOOLEAN);
    /**
     * true, if types can be used inconsistently
     */
    public static final JsLintOption<Boolean> CONFUSION = new JsLintOption<Boolean>("confusion", "true, if types can be used inconsistently", OptionType.BOOLEAN);
    /**
     * true, if the continuation statement should be tolerated
     */
    public static final JsLintOption<Boolean> CONTINUE = new JsLintOption<Boolean>("'continue'", "true, if the continuation statement should be tolerated", OptionType.BOOLEAN);

    /**
     * true, if CSS workarounds should be tolerated
     */
    public static final JsLintOption<Boolean> CSS = new JsLintOption<Boolean>("css", "true, if CSS workarounds should be tolerated", OptionType.BOOLEAN);

    /**
     * true, if debugger statements should be allowed
     */
    public static final JsLintOption<Boolean> DEBUG = new JsLintOption<Boolean>("debug", "true, if debugger statements should be allowed", OptionType.BOOLEAN);

    /**
     * true, if logging should be allowed (console, alert, etc.)
     */
    public static final JsLintOption<Boolean> DEVEL = new JsLintOption<Boolean>("devel", "true, if logging should be allowed (console, alert, etc.)", OptionType.BOOLEAN);

    /**
     * true, if == should be allowed
     */
    public static final JsLintOption<Boolean> EQEQ = new JsLintOption<Boolean>("eqeq", "true, if == should be allowed", OptionType.BOOLEAN);

    /**
     * true, if ES5 syntax should be allowed
     */
    public static final JsLintOption<Boolean> ES5 = new JsLintOption<Boolean>("es5", "true, if ES5 syntax should be allowed", OptionType.BOOLEAN);

    /**
     * true, if eval should be allowed
     */
    public static final JsLintOption<Boolean> EVIL = new JsLintOption<Boolean>("evil", "true, if eval should be allowed", OptionType.BOOLEAN);

    /**
     * true, if for in statements need not filter
     */
    public static final JsLintOption<Boolean> FORIN = new JsLintOption<Boolean>("forin", "true, if for in statements need not filter", OptionType.BOOLEAN);

    /**
     * true, if HTML fragments should be allowed
     */
    public static final JsLintOption<Boolean> FRAGMENT = new JsLintOption<Boolean>("fragment", "true, if HTML fragments should be allowed", OptionType.BOOLEAN);

    /**
     * the indentation factor
     */
    public static final JsLintOption<Integer> INDENT = new JsLintOption<Integer>("indent", "the indentation factor", OptionType.NUMBER);

    /**
     * the maximum number of errors to allow
     */
    public static final JsLintOption<Integer> MAXERR = new JsLintOption<Integer>("maxerr", "the maximum number of errors to allow", OptionType.NUMBER);

    /**
     * the maximum length of a source line
     */
    public static final JsLintOption<Integer> MAXLEN = new JsLintOption<Integer>("maxlen", "the maximum length of a source line", OptionType.NUMBER);

    /**
     * true, if constructor names capitalization is ignored
     */
    public static final JsLintOption<Boolean> NEWCAP = new JsLintOption<Boolean>("newcap", "true, if constructor names capitalization is ignored", OptionType.BOOLEAN);

    /**
     * true, if Node.js globals should be predefined
     */
    public static final JsLintOption<Boolean> NODE = new JsLintOption<Boolean>("node", "true, if Node.js globals should be predefined", OptionType.BOOLEAN);

    /**
     * true, if names may have dangling _
     */
    public static final JsLintOption<Boolean> NOMEN = new JsLintOption<Boolean>("nomen", "true, if names may have dangling _", OptionType.BOOLEAN);

    /**
     * true, if HTML event handlers should be allowed
     */
    public static final JsLintOption<Boolean> ON = new JsLintOption<Boolean>("on", "true, if HTML event handlers should be allowed", OptionType.BOOLEAN);

    /**
     * true, if the scan should stop on first error
     */
    public static final JsLintOption<Boolean> PASSFAIL = new JsLintOption<Boolean>("passfail", "true, if the scan should stop on first error", OptionType.BOOLEAN);

    /**
     * true, if increment/decrement should be allowed
     */
    public static final JsLintOption<Boolean> PLUSPLUS = new JsLintOption<Boolean>("plusplus", "true, if increment/decrement should be allowed", OptionType.BOOLEAN);

    /**
     * true, if all property names must be declared with /*properties
     */
    public static final JsLintOption<Boolean> PROPERTIES = new JsLintOption<Boolean>("properties", "true, if all property names must be declared with /*properties*/", OptionType.BOOLEAN);

    /**
     * true, if the . should be allowed in regexp literals
     */
    public static final JsLintOption<Boolean> REGEXP = new JsLintOption<Boolean>("regexp", "true, if the . should be allowed in regexp literals", OptionType.BOOLEAN);

    /**
     * true, if the Rhino environment globals should be predefined
     */
    public static final JsLintOption<Boolean> RHINO = new JsLintOption<Boolean>("rhino", "true, if the Rhino environment globals should be predefined", OptionType.BOOLEAN);

    /**
     * true, if variables can be declared out of order
     */
    public static final JsLintOption<Boolean> UNDEF = new JsLintOption<Boolean>("undef", "true, if variables can be declared out of order", OptionType.BOOLEAN);

    /**
     * true, if unused parameters should be tolerated
     */
    public static final JsLintOption<Boolean> UNPARAM = new JsLintOption<Boolean>("unparam", "true, if unused parameters should be tolerated", OptionType.BOOLEAN);

    /**
     * true, if use of some browser features should be restricted
     */
    public static final JsLintOption<Boolean> SAFE = new JsLintOption<Boolean>("safe", "true, if use of some browser features should be restricted", OptionType.BOOLEAN);

    /**
     * true, if the 'use strict'; pragma is optional
     */
    public static final JsLintOption<Boolean> SLOPPY = new JsLintOption<Boolean>("sloppy", "true, if the 'use strict'; pragma is optional", OptionType.BOOLEAN);

    /**
     * true, if all forms of subscript notation are tolerated
     */
    public static final JsLintOption<Boolean> SUB = new JsLintOption<Boolean>("sub", "true, if all forms of subscript notation are tolerated", OptionType.BOOLEAN);

    /**
     * true, if multiple var statements per function should be allowed
     */
    public static final JsLintOption<Boolean> VARS = new JsLintOption<Boolean>("vars", "true, if multiple var statements per function should be allowed", OptionType.BOOLEAN);

    /**
     * true, if sloppy whitespace is tolerated
     */
    public static final JsLintOption<Boolean> WHITE = new JsLintOption<Boolean>("white", "true, if sloppy whitespace is tolerated", OptionType.BOOLEAN);

    /**
     * true  if the Yahoo Widgets globals should be predefined
     */
    public static final JsLintOption<Boolean> WIDGET = new JsLintOption<Boolean>("widget", "true  if the Yahoo Widgets globals should be predefined", OptionType.BOOLEAN);

    /**
     * true, if MS Windows-specific globals should be predefined
     */
    public static final JsLintOption<Boolean> WINDOWS = new JsLintOption<Boolean>("windows", "true, if MS Windows-specific globals should be predefined", OptionType.BOOLEAN);
    /**
     * List of String for global object
     */
    public static final JsLintOption<List<String>> PREDEF = new JsLintOption<List<String>>("predef", "List of String for global object devided by colom", OptionType.STRING_ARRAY);

    private String name;
    private String description;
    private OptionType type;

    private JsLintOption(String name, String description, OptionType<Type> type) {
        this.name = name;
        this.description = description;
        this.type = type;
        options.put(name, this);
    }

    public static Map<String, JsLintOption> getOptions() {
        return Collections.unmodifiableMap(options);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public OptionType getType() {
        return type;
    }

    public void setType(OptionType type) {
        this.type = type;
    }
}
