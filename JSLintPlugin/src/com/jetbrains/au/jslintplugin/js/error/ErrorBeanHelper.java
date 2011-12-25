package com.jetbrains.au.jslintplugin.js.error;

import com.jetbrains.au.jslintplugin.js.error.processor.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

/**
 * User: Dmitriy Bandurin
 * Date: 16.11.11
 * Time: 19:50
 */
public class ErrorBeanHelper {
    public static Map<String, Class<? extends ErrorProcessor>> errorMapping = new HashMap<String, Class<? extends ErrorProcessor>>();

    static {
        final Class<? extends ErrorProcessor> defaultProcessor = DefaultProcessor.class;
        errorMapping.put("'{a}' is a statement label.", defaultProcessor);
        errorMapping.put("'{a}' is not allowed.", defaultProcessor);
        errorMapping.put("'{a}' is not defined.", defaultProcessor);
        errorMapping.put("'{a}' used out of scope.", defaultProcessor);
        errorMapping.put("ADsafe violation: '{a}'.", defaultProcessor);
        errorMapping.put("ADsafe autocomplete violation.", defaultProcessor);
        errorMapping.put("ADSAFE violation: bad id.", defaultProcessor);
        errorMapping.put("ADsafe violation: Wrap the widget in a div.", defaultProcessor);
        errorMapping.put("ADSAFE: Use the fragment option.", defaultProcessor);
        errorMapping.put("ADsafe violation: Misformed ADSAFE.go.", defaultProcessor);
        errorMapping.put("Currently, ADsafe does not operate on whole HTML " +
                "documents. It operates on <div> fragments and .js files.", defaultProcessor);
        errorMapping.put("ADsafe violation: id does not match.", defaultProcessor);
        errorMapping.put("ADsafe violation: Missing ADSAFE.id or ADSAFE.go.", defaultProcessor);
        errorMapping.put("ADsafe lib violation.", defaultProcessor);
        errorMapping.put("ADsafe: The second argument to lib must be a function.", defaultProcessor);
        errorMapping.put("ADSAFE violation: missing ID_.", defaultProcessor);
        errorMapping.put("ADsafe name violation: '{a}'.", defaultProcessor);
        errorMapping.put("ADsafe script placement violation.", defaultProcessor);
        errorMapping.put("ADsafe violation: An id must have a '{a}' prefix", defaultProcessor);
        errorMapping.put("ADsafe script violation.", defaultProcessor);
        errorMapping.put("ADsafe unapproved script source.", defaultProcessor);
        errorMapping.put("ADsafe subscript '{a}'.", defaultProcessor);
        errorMapping.put("ADsafe violation: Disallowed tag '{a}'.", defaultProcessor);
        errorMapping.put("'{a}' is already defined.", defaultProcessor);
        errorMapping.put("The '&&' subexpression should be wrapped in parens.", defaultProcessor);
        errorMapping.put("Do not assign to the exception parameter.", defaultProcessor);
        errorMapping.put("Expected an assignment or " +
                "function call and instead saw an expression.", defaultProcessor);
        errorMapping.put("Attribute '{a}' not all lower case.", defaultProcessor);
        errorMapping.put("Avoid '{a}'.", defaultProcessor);
        errorMapping.put("Bad assignment.", defaultProcessor);
        errorMapping.put("Bad hex color '{a}'.", defaultProcessor);
        errorMapping.put("Bad constructor.", defaultProcessor);
        errorMapping.put("Bad entity.", defaultProcessor);
        errorMapping.put("Bad HTML string", defaultProcessor);
        errorMapping.put("Bad id: '{a}'.", defaultProcessor);
        errorMapping.put("Bad for in variable '{a}'.", defaultProcessor);
        errorMapping.put("Bad invocation.", defaultProcessor);
        errorMapping.put("Bad name: '{a}'.", defaultProcessor);
        errorMapping.put("Do not use 'new' for side effects.", defaultProcessor);
        errorMapping.put("Bad number '{a}'.", defaultProcessor);
        errorMapping.put("Bad operand.", defaultProcessor);
        errorMapping.put("Bad style.", defaultProcessor);
        errorMapping.put("Bad type.", defaultProcessor);
        errorMapping.put("Bad url '{a}'.", defaultProcessor);
        errorMapping.put("Do not wrap function literals in parens unless they " +
                "are to be immediately invoked.", defaultProcessor);
        errorMapping.put("Combine this with the previous 'var' statement.", defaultProcessor);
        errorMapping.put("Expected a conditional expression and " +
                "instead saw an assignment.", defaultProcessor);
        errorMapping.put("Confusing use of '{a}'.", defaultProcessor);
        errorMapping.put("Confusing regular expression.", defaultProcessor);
        errorMapping.put("A constructor name '{a}' should start with " +
                "an uppercase letter.", defaultProcessor);
        errorMapping.put("Unexpected control character '{a}'.", defaultProcessor);
        errorMapping.put("A css file should begin with @charset 'UTF-8';", defaultProcessor);
        errorMapping.put("Unexpected dangling '_' in '{a}'.", defaultProcessor);
        errorMapping.put("Dangerous comment.", defaultProcessor);
        errorMapping.put("Only properties should be deleted.", defaultProcessor);
        errorMapping.put("Duplicate '{a}'.", defaultProcessor);
        errorMapping.put("Empty block.", defaultProcessor);
        errorMapping.put("Empty case.", defaultProcessor);
        errorMapping.put("Empty class.", defaultProcessor);
        errorMapping.put("This is an ES5 feature.", defaultProcessor);
        errorMapping.put("eval is evil.", defaultProcessor);
        errorMapping.put("Expected '{a}'.", defaultProcessor);
        errorMapping.put("Expected '{a}' and instead saw '{b}'.", ExpectedInsteadProblem.class);
        errorMapping.put("Expected '{a}' to match '{b}' from line " +
                "{c} and instead saw '{d}'.", defaultProcessor);
        errorMapping.put("Expected an at-rule, and instead saw @{a}.", defaultProcessor);
        errorMapping.put("Expected '{a}' at column {b}, not column {c}.", defaultProcessor);
        errorMapping.put("Expected an attribute, and instead saw [{a}].", defaultProcessor);
        errorMapping.put("Expected an attribute value and " +
                "instead saw '{a}'.", defaultProcessor);
        errorMapping.put("Expected a class, and instead saw .{a}.", defaultProcessor);
        errorMapping.put("Expected a number between 0 and 1 and " +
                "instead saw '{a}'", defaultProcessor);
        errorMapping.put("Expected an id, and instead saw #{a}.", defaultProcessor);
        errorMapping.put("Expected an identifier and instead saw '{a}'.", defaultProcessor);
        errorMapping.put("Expected an identifier and " +
                "instead saw '{a}' (a reserved word).", defaultProcessor);
        errorMapping.put("Expected a linear unit and instead saw '{a}'.", defaultProcessor);
        errorMapping.put("Expected a lang code, and instead saw :{a}.", defaultProcessor);
        errorMapping.put("Expected a CSS media type, and instead saw '{a}'.", defaultProcessor);
        errorMapping.put("Expected a name and instead saw '{a}'.", defaultProcessor);
        errorMapping.put("Expected a non-standard " +
                "style attribute and instead saw '{a}'.", defaultProcessor);
        errorMapping.put("Expected a number and instead saw '{a}'.", defaultProcessor);
        errorMapping.put("Expected an operator and instead saw '{a}'.", defaultProcessor);
        errorMapping.put("Expected a percentage and instead saw '{a}'", defaultProcessor);
        errorMapping.put("Expected a positive number and instead saw '{a}'", defaultProcessor);
        errorMapping.put("Expected a pseudo, and instead saw :{a}.", defaultProcessor);
        errorMapping.put("Expected a CSS selector, and instead saw {a}.", defaultProcessor);
        errorMapping.put("Expected a small positive integer and instead saw '{a}'", defaultProcessor);
        errorMapping.put("Expected exactly one space between '{a}' and '{b}'.", MissingSpaceProblem.class);
        errorMapping.put("Expected a string and instead saw {a}.", defaultProcessor);
        errorMapping.put("Excepted a style attribute, and instead saw '{a}'.", defaultProcessor);
        errorMapping.put("Expected a style pattern, and instead saw '{a}'.", defaultProcessor);
        errorMapping.put("Expected a tagName, and instead saw {a}.", defaultProcessor);
        errorMapping.put("Expected a type, and instead saw {a}.", defaultProcessor);
        errorMapping.put("The body of a for in should be wrapped in an if " +
                "statement to filter unwanted properties from the prototype.", defaultProcessor);
        errorMapping.put("Function statements should not be placed in blocks. " +
                "Use a function expression or move the statement to the top of " +
                "the outer function.", defaultProcessor);
        errorMapping.put("The Function constructor is eval.", defaultProcessor);
        errorMapping.put("Don't make functions within a loop.", defaultProcessor);
        errorMapping.put("Function statements are not invocable. " +
                "Wrap the whole function invocation in parens.", defaultProcessor);
        errorMapping.put("Use the function form of 'use strict'.", defaultProcessor);
        errorMapping.put("HTML confusion in regular expression '<{a}'.", defaultProcessor);
        errorMapping.put("Avoid HTML event handlers.", defaultProcessor);
        errorMapping.put("Expected an identifier in an assignment " +
                "and instead saw a function invocation.", defaultProcessor);
        errorMapping.put("Implied eval is evil. Pass a function instead of a string.", defaultProcessor);
        errorMapping.put("Unexpected 'in'. Compare with undefined, or use the " +
                "hasOwnProperty method instead.", defaultProcessor);
        errorMapping.put("Insecure '{a}'.", defaultProcessor);
        errorMapping.put("Use the isNaN function to compare with NaN.", defaultProcessor);
        errorMapping.put("Label '{a}' on '{b}' statement.", defaultProcessor);
        errorMapping.put("lang is deprecated.", defaultProcessor);
        errorMapping.put("A leading decimal point can be confused with a dot: '.{a}'.", defaultProcessor);
        errorMapping.put("Missing '{a}'.", defaultProcessor);
        errorMapping.put("Missing '{a}' after '{b}'.", defaultProcessor);
        errorMapping.put("Missing option value.", defaultProcessor);
        errorMapping.put("Missing property name.", defaultProcessor);
        errorMapping.put("Missing space between '{a}' and '{b}'.", MissingSpaceProblem.class);
        errorMapping.put("Missing url.", defaultProcessor);
        errorMapping.put("Missing 'use strict' statement.", defaultProcessor);
        errorMapping.put("Mixed spaces and tabs.", defaultProcessor);
        errorMapping.put("Move the invocation into the parens that " +
                "contain the function.", defaultProcessor);
        errorMapping.put("Move 'var' declarations to the top of the function.", defaultProcessor);
        errorMapping.put("Missing name in function statement.", defaultProcessor);
        errorMapping.put("Nested comment.", defaultProcessor);
        errorMapping.put("Nested not.", defaultProcessor);
        errorMapping.put("Do not use {a} as a constructor.", defaultProcessor);
        errorMapping.put("'{a}' has not been fully defined yet.", defaultProcessor);
        errorMapping.put("'{a}' is not a function.", defaultProcessor);
        errorMapping.put("'{a}' is not a label.", defaultProcessor);
        errorMapping.put("'{a}' is out of scope.", defaultProcessor);
        errorMapping.put("'{a}' should not be greater than '{b}'.", defaultProcessor);
        errorMapping.put("Unexpected parameter '{a}' in get {b} function.", defaultProcessor);
        errorMapping.put("Expected parameter (value) in set {a} function.", defaultProcessor);
        errorMapping.put("Missing radix parameter.", defaultProcessor);
        errorMapping.put("Read only.", defaultProcessor);
        errorMapping.put("Redefinition of '{a}'.", defaultProcessor);
        errorMapping.put("Reserved name '{a}'.", defaultProcessor);
        errorMapping.put("{a} ({b}% scanned).", defaultProcessor);
        errorMapping.put("A regular expression literal can be confused with '/='.", defaultProcessor);
        errorMapping.put("Expected to see a statement and instead saw a block.", defaultProcessor);
        errorMapping.put("Stopping. ", defaultProcessor);
        errorMapping.put("Strange loop.", defaultProcessor);
        errorMapping.put("Strict violation.", defaultProcessor);
        errorMapping.put("['{a}'] is better written in dot notation.", defaultProcessor);
        errorMapping.put("A '<{a}>' must be within '<{b}>'.", defaultProcessor);
        errorMapping.put("Line too long.", defaultProcessor);
        errorMapping.put("Too many errors.", defaultProcessor);
        errorMapping.put("A trailing decimal point can be confused " +
                "with a dot: '.{a}'.", defaultProcessor);
        errorMapping.put("type is unnecessary.", defaultProcessor);
        errorMapping.put("Type confusion: {a} and {b}.", defaultProcessor);
        errorMapping.put("Unclosed string.", defaultProcessor);
        errorMapping.put("Unclosed comment.", defaultProcessor);
        errorMapping.put("Unclosed regular expression.", defaultProcessor);
        errorMapping.put("Unescaped '{a}'.", defaultProcessor);
        errorMapping.put("Unexpected '{a}'.", defaultProcessor);
        errorMapping.put("Unexpected character '{a}' in {b}.", defaultProcessor);
        errorMapping.put("Unexpected comment.", defaultProcessor);
        errorMapping.put("Unexpected /*property*/ '{a}'.", defaultProcessor);
        errorMapping.put("Unexpected space between '{a}' and '{b}'.", defaultProcessor);
        errorMapping.put("It is not necessary to initialize '{a}' " +
                "to 'undefined'.", defaultProcessor);
        errorMapping.put("Unnecessary 'use strict'.", defaultProcessor);
        errorMapping.put("Unreachable '{a}' after '{b}'.", defaultProcessor);
        errorMapping.put("Unrecognized style attribute '{a}'.", defaultProcessor);
        errorMapping.put("Unrecognized tag '<{a}>'.", defaultProcessor);
        errorMapping.put("Unsafe character.", defaultProcessor);
        errorMapping.put("JavaScript URL.", defaultProcessor);
        errorMapping.put("Use the array literal notation [].", defaultProcessor);
        errorMapping.put("Spaces are hard to count. Use {{a}}.", defaultProcessor);
        errorMapping.put("Use the charAt method.", defaultProcessor);
        errorMapping.put("Use the object literal notation {}.", defaultProcessor);
        errorMapping.put("Use the || operator.", defaultProcessor);
        errorMapping.put("Use a named parameter.", defaultProcessor);
        errorMapping.put("'{a}' was used before it was defined.", VariableUsedProblem.class);
        errorMapping.put("Variable {a} was not declared correctly.", defaultProcessor);
        errorMapping.put("Weird assignment.", defaultProcessor);
        errorMapping.put("Weird condition.", defaultProcessor);
        errorMapping.put("Weird construction. Delete 'new'.", defaultProcessor);
        errorMapping.put("Weird program.", defaultProcessor);
        errorMapping.put("Weird relation.", defaultProcessor);
        errorMapping.put("Weird ternary.", defaultProcessor);
        errorMapping.put("Wrap an immediate function invocation in parentheses " +
                "to assist the reader in understanding that the expression " +
                "is the result of a function, and not the function itself.", defaultProcessor);
        errorMapping.put("Wrap the /regexp/ literal in parens to " +
                "disambiguate the slash operator.", defaultProcessor);
        errorMapping.put("document.write can be a form of eval.", defaultProcessor);
    }

    public static ErrorProcessor getProcessor(@NotNull final ErrorBeanWrapper errorBeanWrapper){
        return getProcessor(errorBeanWrapper.getRaw());
    }

    public static ErrorProcessor getProcessor(@Nullable final String key) {
        ErrorProcessor instance = null;
        if (errorMapping.containsKey(key)) {
            Class<? extends ErrorProcessor> processor = errorMapping.get(key);
            try {
                instance = processor.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (instance == null) {
            instance = new DefaultProcessor();
        }

        return instance;
    }
}
