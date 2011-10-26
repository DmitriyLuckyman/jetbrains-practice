package com.jetbrains.au.jslintplugin;

/**
 * User: Dmitriy Bandurin
 * Date: 26.10.11
 * Time: 17:50
 */
public enum JsLintOption {

    /**
     * true, if ADsafe rules should be enforced
     */
    ADSAFE("adsafe", "true, if ADsafe rules should be enforced", OptionType.BOOLEAN),
    /**
     * true, if bitwise operators should be allowed
     */
    BITWISE("bitwise", "true, if bitwise operators should be allowed", OptionType.BOOLEAN),
    /**
     * true, if the standard browser globals should be predefined
     */
    BROWSER("browser", "true, if the standard browser globals should be predefined", OptionType.BOOLEAN),
    /**
     * true, if upper case HTML should be allowed
     */
    CAP("cap", "true, if upper case HTML should be allowed", OptionType.BOOLEAN),
    /**
     * true, if types can be used inconsistently
     */
    CONFUSION("confusion", "true, if types can be used inconsistently", OptionType.BOOLEAN),
    /**
     * true, if the continuation statement should be tolerated
     */
    CONTINUE("'continue'", "true, if the continuation statement should be tolerated", OptionType.BOOLEAN),

    /**
     * true, if CSS workarounds should be tolerated
     */
    CSS("css", "true, if CSS workarounds should be tolerated", OptionType.BOOLEAN),

    /**
     * true, if debugger statements should be allowed
     */
    DEBUG("debug", "true, if debugger statements should be allowed", OptionType.BOOLEAN),

    /**
     * true, if logging should be allowed (console, alert, etc.)
     */
    DEVEL("devel", "true, if logging should be allowed (console, alert, etc.)", OptionType.BOOLEAN),

    /**
     * true, if == should be allowed
     */
    EQEQ("eqeq", "true, if == should be allowed", OptionType.BOOLEAN),

    /**
     * true, if ES5 syntax should be allowed
     */
    ES5("es5", "true, if ES5 syntax should be allowed", OptionType.BOOLEAN),

    /**
     * true, if eval should be allowed
     */
    EVIL("evil", "true, if eval should be allowed", OptionType.BOOLEAN),

    /**
     * true, if for in statements need not filter
     */
    FORIN("forin", "true, if for in statements need not filter", OptionType.BOOLEAN),

    /**
     * true, if HTML fragments should be allowed
     */
    FRAGMENT("fragment", "true, if HTML fragments should be allowed", OptionType.BOOLEAN),

    /**
     * the indentation factor
     */
    INDENT("indent", "the indentation factor", OptionType.NUMBER),

    /**
     * the maximum number of errors to allow
     */
    MAXERR("maxerr", "the maximum number of errors to allow", OptionType.NUMBER),

    /**
     * the maximum length of a source line
     */
    MAXLEN("maxlen", "the maximum length of a source line", OptionType.NUMBER),

    /**
     * true, if constructor names capitalization is ignored
     */
    NEWCAP("newcap", "true, if constructor names capitalization is ignored", OptionType.BOOLEAN),

    /**
     * true, if Node.js globals should be predefined
     */
    NODE("node", "true, if Node.js globals should be predefined", OptionType.BOOLEAN),

    /**
     * true, if names may have dangling _
     */
    NOMEN("nomen", "true, if names may have dangling _", OptionType.BOOLEAN),

    /**
     * true, if HTML event handlers should be allowed
     */
    ON("on", "true, if HTML event handlers should be allowed", OptionType.BOOLEAN),

    /**
     * true, if the scan should stop on first error
     */
    PASSFAIL("passfail", "true, if the scan should stop on first error", OptionType.BOOLEAN),

    /**
     * true, if increment/decrement should be allowed
     */
    PLUSPLUS("plusplus", "true, if increment/decrement should be allowed", OptionType.BOOLEAN),

    /**
     * true, if all property names must be declared with /*properties
     */
    PROPERTIES("properties", "true, if all property names must be declared with /*properties*/", OptionType.BOOLEAN),

    /**
     * true, if the . should be allowed in regexp literals
     */
    REGEXP("regexp", "true, if the . should be allowed in regexp literals", OptionType.BOOLEAN),

    /**
     * true, if the Rhino environment globals should be predefined
     */
    RHINO("rhino", "true, if the Rhino environment globals should be predefined", OptionType.BOOLEAN),

    /**
     * true, if variables can be declared out of order
     */
    UNDEF("undef", "true, if variables can be declared out of order", OptionType.BOOLEAN),

    /**
     * true, if unused parameters should be tolerated
     */
    UNPARAM("unparam", "true, if unused parameters should be tolerated", OptionType.BOOLEAN),

    /**
     * true, if use of some browser features should be restricted
     */
    SAFE("safe", "true, if use of some browser features should be restricted", OptionType.BOOLEAN),

    /**
     * true, if the 'use strict'; pragma is optional
     */
    SLOPPY("sloppy", "true, if the 'use strict'; pragma is optional", OptionType.BOOLEAN),

    /**
     * true, if all forms of subscript notation are tolerated
     */
    SUB("sub", "true, if all forms of subscript notation are tolerated", OptionType.BOOLEAN),

    /**
     * true, if multiple var statements per function should be allowed
     */
    VARS("vars", "true, if multiple var statements per function should be allowed", OptionType.BOOLEAN),

    /**
     * true, if sloppy whitespace is tolerated
     */
    WHITE("white", "true, if sloppy whitespace is tolerated", OptionType.BOOLEAN),

    /**
     * true  if the Yahoo Widgets globals should be predefined
     */
    WIDGET("widget", "true  if the Yahoo Widgets globals should be predefined", OptionType.BOOLEAN),

    /**
     * true, if MS Windows-specific globals should be predefined
     */
    WINDOWS("windows", "true, if MS Windows-specific globals should be predefined", OptionType.BOOLEAN);

    private String name;
    private String description;
    private OptionType type;

    <E> JsLintOption(String name, String description, OptionType<E> tyoe) {

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
