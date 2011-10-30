package com.jetbrains.au.jslintplugin.config;

import java.util.*;

/**
 * User: Dmitriy Bandurin
 * Date: 26.10.11
 * Time: 17:50
 */
public class JsLintOption {
    private final static HashMap<String, JsLintOption> options = new HashMap<String, JsLintOption>();
    /**
     * true, if ADsafe rules should be enforced
     */
    public static final JsLintOption ADSAFE = new JsLintOption("adsafe",
            "true, if ADsafe rules should be enforced", OptionType.BOOLEAN);
    /**
     * true, if bitwise operators should be allowed
     */
    public static final JsLintOption BITWISE = new JsLintOption("bitwise", "true, if bitwise operators should be allowed", OptionType.BOOLEAN);
    /**
     * true, if the standard browser globals should be predefined
     */
    public static final JsLintOption BROWSER = new JsLintOption("browser", "true, if the standard browser globals should be predefined", OptionType.BOOLEAN);
    /**
     * true, if upper case HTML should be allowed
     */
    public static final JsLintOption CAP = new JsLintOption("cap", "true, if upper case HTML should be allowed", OptionType.BOOLEAN);
    /**
     * true, if types can be used inconsistently
     */
    public static final JsLintOption CONFUSION = new JsLintOption("confusion", "true, if types can be used inconsistently", OptionType.BOOLEAN);
    /**
     * true, if the continuation statement should be tolerated
     */
    public static final JsLintOption CONTINUE = new JsLintOption("'continue'", "true, if the continuation statement should be tolerated", OptionType.BOOLEAN);

    /**
     * true, if CSS workarounds should be tolerated
     */
    public static final JsLintOption CSS = new JsLintOption("css", "true, if CSS workarounds should be tolerated", OptionType.BOOLEAN);

    /**
     * true, if debugger statements should be allowed
     */
    public static final JsLintOption DEBUG = new JsLintOption("debug", "true, if debugger statements should be allowed", OptionType.BOOLEAN);

    /**
     * true, if logging should be allowed (console, alert, etc.)
     */
    public static final JsLintOption DEVEL = new JsLintOption("devel", "true, if logging should be allowed (console, alert, etc.)", OptionType.BOOLEAN);

    /**
     * true, if == should be allowed
     */
    public static final JsLintOption EQEQ = new JsLintOption("eqeq", "true, if == should be allowed", OptionType.BOOLEAN);

    /**
     * true, if ES5 syntax should be allowed
     */
    public static final JsLintOption ES5 = new JsLintOption("es5", "true, if ES5 syntax should be allowed", OptionType.BOOLEAN);

    /**
     * true, if eval should be allowed
     */
    public static final JsLintOption EVIL = new JsLintOption("evil", "true, if eval should be allowed", OptionType.BOOLEAN);

    /**
     * true, if for in statements need not filter
     */
    public static final JsLintOption FORIN = new JsLintOption("forin", "true, if for in statements need not filter", OptionType.BOOLEAN);

    /**
     * true, if HTML fragments should be allowed
     */
    public static final JsLintOption FRAGMENT = new JsLintOption("fragment", "true, if HTML fragments should be allowed", OptionType.BOOLEAN);

    /**
     * the indentation factor
     */
    public static final JsLintOption INDENT = new JsLintOption("indent", "the indentation factor", OptionType.NUMBER);

    /**
     * the maximum number of errors to allow
     */
    public static final JsLintOption MAXERR = new JsLintOption("maxerr", "the maximum number of errors to allow", OptionType.NUMBER);

    /**
     * the maximum length of a source line
     */
    public static final JsLintOption MAXLEN = new JsLintOption("maxlen", "the maximum length of a source line", OptionType.NUMBER);

    /**
     * true, if constructor names capitalization is ignored
     */
    public static final JsLintOption NEWCAP = new JsLintOption("newcap", "true, if constructor names capitalization is ignored", OptionType.BOOLEAN);

    /**
     * true, if Node.js globals should be predefined
     */
    public static final JsLintOption NODE = new JsLintOption("node", "true, if Node.js globals should be predefined", OptionType.BOOLEAN);

    /**
     * true, if names may have dangling _
     */
    public static final JsLintOption NOMEN = new JsLintOption("nomen", "true, if names may have dangling _", OptionType.BOOLEAN);

    /**
     * true, if HTML event handlers should be allowed
     */
    public static final JsLintOption ON = new JsLintOption("on", "true, if HTML event handlers should be allowed", OptionType.BOOLEAN);

    /**
     * true, if the scan should stop on first error
     */
    public static final JsLintOption PASSFAIL = new JsLintOption("passfail", "true, if the scan should stop on first error", OptionType.BOOLEAN);

    /**
     * true, if increment/decrement should be allowed
     */
    public static final JsLintOption PLUSPLUS = new JsLintOption("plusplus", "true, if increment/decrement should be allowed", OptionType.BOOLEAN);

    /**
     * true, if all property names must be declared with /*properties
     */
    public static final JsLintOption PROPERTIES = new JsLintOption("properties", "true, if all property names must be declared with /*properties*/", OptionType.BOOLEAN);

    /**
     * true, if the . should be allowed in regexp literals
     */
    public static final JsLintOption REGEXP = new JsLintOption("regexp", "true, if the . should be allowed in regexp literals", OptionType.BOOLEAN);

    /**
     * true, if the Rhino environment globals should be predefined
     */
    public static final JsLintOption RHINO = new JsLintOption("rhino", "true, if the Rhino environment globals should be predefined", OptionType.BOOLEAN);

    /**
     * true, if variables can be declared out of order
     */
    public static final JsLintOption UNDEF = new JsLintOption("undef", "true, if variables can be declared out of order", OptionType.BOOLEAN);

    /**
     * true, if unused parameters should be tolerated
     */
    public static final JsLintOption UNPARAM = new JsLintOption("unparam", "true, if unused parameters should be tolerated", OptionType.BOOLEAN);

    /**
     * true, if use of some browser features should be restricted
     */
    public static final JsLintOption SAFE = new JsLintOption("safe", "true, if use of some browser features should be restricted", OptionType.BOOLEAN);

    /**
     * true, if the 'use strict'; pragma is optional
     */
    public static final JsLintOption SLOPPY = new JsLintOption("sloppy", "true, if the 'use strict'; pragma is optional", OptionType.BOOLEAN);

    /**
     * true, if all forms of subscript notation are tolerated
     */
    public static final JsLintOption SUB = new JsLintOption("sub", "true, if all forms of subscript notation are tolerated", OptionType.BOOLEAN);

    /**
     * true, if multiple var statements per function should be allowed
     */
    public static final JsLintOption VARS = new JsLintOption("vars", "true, if multiple var statements per function should be allowed", OptionType.BOOLEAN);

    /**
     * true, if sloppy whitespace is tolerated
     */
    public static final JsLintOption WHITE = new JsLintOption("white", "true, if sloppy whitespace is tolerated", OptionType.BOOLEAN);

    /**
     * true  if the Yahoo Widgets globals should be predefined
     */
    public static final JsLintOption WIDGET = new JsLintOption("widget", "true  if the Yahoo Widgets globals should be predefined", OptionType.BOOLEAN);

    /**
     * true, if MS Windows-specific globals should be predefined
     */
    public static final JsLintOption WINDOWS = new JsLintOption("windows", "true, if MS Windows-specific globals should be predefined", OptionType.BOOLEAN);
    /**
     * List of String for global object
     */
    public static final JsLintOption PREDEF = new JsLintOption("predef", "List of String for global object devided by colom", OptionType.STRING_ARRAY);

    private String name;
    private String description;
    private OptionType type;

    private JsLintOption(String name, String description, OptionType type) {
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
