package com.jetbrains.au.jslintplugin.js.error;

import com.jetbrains.au.jslintplugin.js.error.processor.ErrorProcessor;
import com.jetbrains.au.jslintplugin.js.error.processor.VariableUsedProblem;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * User: Dmitriy Bandurin
 * Date: 16.11.11
 * Time: 19:50
 */
public class ErrorBeanHelper {
    public static Map<String, ErrorProcessor> errorMapping = new HashMap<String, ErrorProcessor>();

    static {
        errorMapping.put("'{a}' is a statement label.", new DefaultProcessor());
        errorMapping.put("'{a}' is not allowed.", new DefaultProcessor());
        errorMapping.put("'{a}' is not defined.", new DefaultProcessor());
        errorMapping.put("'{a}' used out of scope.", new DefaultProcessor());
        errorMapping.put("ADsafe violation: '{a}'.", new DefaultProcessor());
        errorMapping.put("ADsafe autocomplete violation.", new DefaultProcessor());
        errorMapping.put("ADSAFE violation: bad id.", new DefaultProcessor());
        errorMapping.put("ADsafe violation: Wrap the widget in a div.", new DefaultProcessor());
        errorMapping.put("ADSAFE: Use the fragment option.", new DefaultProcessor());
        errorMapping.put("ADsafe violation: Misformed ADSAFE.go.", new DefaultProcessor());
        errorMapping.put("Currently, ADsafe does not operate on whole HTML " +
                "documents. It operates on <div> fragments and .js files.", new DefaultProcessor());
        errorMapping.put("ADsafe violation: id does not match.", new DefaultProcessor());
        errorMapping.put("ADsafe violation: Missing ADSAFE.id or ADSAFE.go.", new DefaultProcessor());
        errorMapping.put("ADsafe lib violation.", new DefaultProcessor());
        errorMapping.put("ADsafe: The second argument to lib must be a function.", new DefaultProcessor());
        errorMapping.put("ADSAFE violation: missing ID_.", new DefaultProcessor());
        errorMapping.put("ADsafe name violation: '{a}'.", new DefaultProcessor());
        errorMapping.put("ADsafe script placement violation.", new DefaultProcessor());
        errorMapping.put("ADsafe violation: An id must have a '{a}' prefix", new DefaultProcessor());
        errorMapping.put("ADsafe script violation.", new DefaultProcessor());
        errorMapping.put("ADsafe unapproved script source.", new DefaultProcessor());
        errorMapping.put("ADsafe subscript '{a}'.", new DefaultProcessor());
        errorMapping.put("ADsafe violation: Disallowed tag '{a}'.", new DefaultProcessor());
        errorMapping.put("'{a}' is already defined.", new DefaultProcessor());
        errorMapping.put("The '&&' subexpression should be wrapped in parens.", new DefaultProcessor());
        errorMapping.put("Do not assign to the exception parameter.", new DefaultProcessor());
        errorMapping.put("Expected an assignment or " +
                "function call and instead saw an expression.", new DefaultProcessor());
        errorMapping.put("Attribute '{a}' not all lower case.", new DefaultProcessor());
        errorMapping.put("Avoid '{a}'.", new DefaultProcessor());
        errorMapping.put("Bad assignment.", new DefaultProcessor());
        errorMapping.put("Bad hex color '{a}'.", new DefaultProcessor());
        errorMapping.put("Bad constructor.", new DefaultProcessor());
        errorMapping.put("Bad entity.", new DefaultProcessor());
        errorMapping.put("Bad HTML string", new DefaultProcessor());
        errorMapping.put("Bad id: '{a}'.", new DefaultProcessor());
        errorMapping.put("Bad for in variable '{a}'.", new DefaultProcessor());
        errorMapping.put("Bad invocation.", new DefaultProcessor());
        errorMapping.put("Bad name: '{a}'.", new DefaultProcessor());
        errorMapping.put("Do not use 'new' for side effects.", new DefaultProcessor());
        errorMapping.put("Bad number '{a}'.", new DefaultProcessor());
        errorMapping.put("Bad operand.", new DefaultProcessor());
        errorMapping.put("Bad style.", new DefaultProcessor());
        errorMapping.put("Bad type.", new DefaultProcessor());
        errorMapping.put("Bad url '{a}'.", new DefaultProcessor());
        errorMapping.put("Do not wrap function literals in parens unless they " +
                "are to be immediately invoked.", new DefaultProcessor());
        errorMapping.put("Combine this with the previous 'var' statement.", new DefaultProcessor());
        errorMapping.put("Expected a conditional expression and " +
                "instead saw an assignment.", new DefaultProcessor());
        errorMapping.put("Confusing use of '{a}'.", new DefaultProcessor());
        errorMapping.put("Confusing regular expression.", new DefaultProcessor());
        errorMapping.put("A constructor name '{a}' should start with " +
                "an uppercase letter.", new DefaultProcessor());
        errorMapping.put("Unexpected control character '{a}'.", new DefaultProcessor());
        errorMapping.put("A css file should begin with @charset 'UTF-8';", new DefaultProcessor());
        errorMapping.put("Unexpected dangling '_' in '{a}'.", new DefaultProcessor());
        errorMapping.put("Dangerous comment.", new DefaultProcessor());
        errorMapping.put("Only properties should be deleted.", new DefaultProcessor());
        errorMapping.put("Duplicate '{a}'.", new DefaultProcessor());
        errorMapping.put("Empty block.", new DefaultProcessor());
        errorMapping.put("Empty case.", new DefaultProcessor());
        errorMapping.put("Empty class.", new DefaultProcessor());
        errorMapping.put("This is an ES5 feature.", new DefaultProcessor());
        errorMapping.put("eval is evil.", new DefaultProcessor());
        errorMapping.put("Expected '{a}'.", new DefaultProcessor());
        errorMapping.put("Expected '{a}' and instead saw '{b}'.", new DefaultProcessor());
        errorMapping.put("Expected '{a}' to match '{b}' from line " +
                "{c} and instead saw '{d}'.", new DefaultProcessor());
        errorMapping.put("Expected an at-rule, and instead saw @{a}.", new DefaultProcessor());
        errorMapping.put("Expected '{a}' at column {b}, not column {c}.", new DefaultProcessor());
        errorMapping.put("Expected an attribute, and instead saw [{a}].", new DefaultProcessor());
        errorMapping.put("Expected an attribute value and " +
                "instead saw '{a}'.", new DefaultProcessor());
        errorMapping.put("Expected a class, and instead saw .{a}.", new DefaultProcessor());
        errorMapping.put("Expected a number between 0 and 1 and " +
                "instead saw '{a}'", new DefaultProcessor());
        errorMapping.put("Expected an id, and instead saw #{a}.", new DefaultProcessor());
        errorMapping.put("Expected an identifier and instead saw '{a}'.", new DefaultProcessor());
        errorMapping.put("Expected an identifier and " +
                "instead saw '{a}' (a reserved word).", new DefaultProcessor());
        errorMapping.put("Expected a linear unit and instead saw '{a}'.", new DefaultProcessor());
        errorMapping.put("Expected a lang code, and instead saw :{a}.", new DefaultProcessor());
        errorMapping.put("Expected a CSS media type, and instead saw '{a}'.", new DefaultProcessor());
        errorMapping.put("Expected a name and instead saw '{a}'.", new DefaultProcessor());
        errorMapping.put("Expected a non-standard " +
                "style attribute and instead saw '{a}'.", new DefaultProcessor());
        errorMapping.put("Expected a number and instead saw '{a}'.", new DefaultProcessor());
        errorMapping.put("Expected an operator and instead saw '{a}'.", new DefaultProcessor());
        errorMapping.put("Expected a percentage and instead saw '{a}'", new DefaultProcessor());
        errorMapping.put("Expected a positive number and instead saw '{a}'", new DefaultProcessor());
        errorMapping.put("Expected a pseudo, and instead saw :{a}.", new DefaultProcessor());
        errorMapping.put("Expected a CSS selector, and instead saw {a}.", new DefaultProcessor());
        errorMapping.put("Expected a small positive integer and instead saw '{a}'", new DefaultProcessor());
        errorMapping.put("Expected exactly one space between '{a}' and '{b}'.", new DefaultProcessor());
        errorMapping.put("Expected a string and instead saw {a}.", new DefaultProcessor());
        errorMapping.put("Excepted a style attribute, and instead saw '{a}'.", new DefaultProcessor());
        errorMapping.put("Expected a style pattern, and instead saw '{a}'.", new DefaultProcessor());
        errorMapping.put("Expected a tagName, and instead saw {a}.", new DefaultProcessor());
        errorMapping.put("Expected a type, and instead saw {a}.", new DefaultProcessor());
        errorMapping.put("The body of a for in should be wrapped in an if " +
                "statement to filter unwanted properties from the prototype.", new DefaultProcessor());
        errorMapping.put("Function statements should not be placed in blocks. " +
                "Use a function expression or move the statement to the top of " +
                "the outer function.", new DefaultProcessor());
        errorMapping.put("The Function constructor is eval.", new DefaultProcessor());
        errorMapping.put("Don't make functions within a loop.", new DefaultProcessor());
        errorMapping.put("Function statements are not invocable. " +
                "Wrap the whole function invocation in parens.", new DefaultProcessor());
        errorMapping.put("Use the function form of 'use strict'.", new DefaultProcessor());
        errorMapping.put("HTML confusion in regular expression '<{a}'.", new DefaultProcessor());
        errorMapping.put("Avoid HTML event handlers.", new DefaultProcessor());
        errorMapping.put("Expected an identifier in an assignment " +
                "and instead saw a function invocation.", new DefaultProcessor());
        errorMapping.put("Implied eval is evil. Pass a function instead of a string.", new DefaultProcessor());
        errorMapping.put("Unexpected 'in'. Compare with undefined, or use the " +
                "hasOwnProperty method instead.", new DefaultProcessor());
        errorMapping.put("Insecure '{a}'.", new DefaultProcessor());
        errorMapping.put("Use the isNaN function to compare with NaN.", new DefaultProcessor());
        errorMapping.put("Label '{a}' on '{b}' statement.", new DefaultProcessor());
        errorMapping.put("lang is deprecated.", new DefaultProcessor());
        errorMapping.put("A leading decimal point can be confused with a dot: '.{a}'.", new DefaultProcessor());
        errorMapping.put("Missing '{a}'.", new DefaultProcessor());
        errorMapping.put("Missing '{a}' after '{b}'.", new DefaultProcessor());
        errorMapping.put("Missing option value.", new DefaultProcessor());
        errorMapping.put("Missing property name.", new DefaultProcessor());
        errorMapping.put("Missing space between '{a}' and '{b}'.", new DefaultProcessor());
        errorMapping.put("Missing url.", new DefaultProcessor());
        errorMapping.put("Missing 'use strict' statement.", new DefaultProcessor());
        errorMapping.put("Mixed spaces and tabs.", new DefaultProcessor());
        errorMapping.put("Move the invocation into the parens that " +
                "contain the function.", new DefaultProcessor());
        errorMapping.put("Move 'var' declarations to the top of the function.", new DefaultProcessor());
        errorMapping.put("Missing name in function statement.", new DefaultProcessor());
        errorMapping.put("Nested comment.", new DefaultProcessor());
        errorMapping.put("Nested not.", new DefaultProcessor());
        errorMapping.put("Do not use {a} as a constructor.", new DefaultProcessor());
        errorMapping.put("'{a}' has not been fully defined yet.", new DefaultProcessor());
        errorMapping.put("'{a}' is not a function.", new DefaultProcessor());
        errorMapping.put("'{a}' is not a label.", new DefaultProcessor());
        errorMapping.put("'{a}' is out of scope.", new DefaultProcessor());
        errorMapping.put("'{a}' should not be greater than '{b}'.", new DefaultProcessor());
        errorMapping.put("Unexpected parameter '{a}' in get {b} function.", new DefaultProcessor());
        errorMapping.put("Expected parameter (value) in set {a} function.", new DefaultProcessor());
        errorMapping.put("Missing radix parameter.", new DefaultProcessor());
        errorMapping.put("Read only.", new DefaultProcessor());
        errorMapping.put("Redefinition of '{a}'.", new DefaultProcessor());
        errorMapping.put("Reserved name '{a}'.", new DefaultProcessor());
        errorMapping.put("{a} ({b}% scanned).", new DefaultProcessor());
        errorMapping.put("A regular expression literal can be confused with '/='.", new DefaultProcessor());
        errorMapping.put("Expected to see a statement and instead saw a block.", new DefaultProcessor());
        errorMapping.put("Stopping. ", new DefaultProcessor());
        errorMapping.put("Strange loop.", new DefaultProcessor());
        errorMapping.put("Strict violation.", new DefaultProcessor());
        errorMapping.put("['{a}'] is better written in dot notation.", new DefaultProcessor());
        errorMapping.put("A '<{a}>' must be within '<{b}>'.", new DefaultProcessor());
        errorMapping.put("Line too long.", new DefaultProcessor());
        errorMapping.put("Too many errors.", new DefaultProcessor());
        errorMapping.put("A trailing decimal point can be confused " +
                "with a dot: '.{a}'.", new DefaultProcessor());
        errorMapping.put("type is unnecessary.", new DefaultProcessor());
        errorMapping.put("Type confusion: {a} and {b}.", new DefaultProcessor());
        errorMapping.put("Unclosed string.", new DefaultProcessor());
        errorMapping.put("Unclosed comment.", new DefaultProcessor());
        errorMapping.put("Unclosed regular expression.", new DefaultProcessor());
        errorMapping.put("Unescaped '{a}'.", new DefaultProcessor());
        errorMapping.put("Unexpected '{a}'.", new DefaultProcessor());
        errorMapping.put("Unexpected character '{a}' in {b}.", new DefaultProcessor());
        errorMapping.put("Unexpected comment.", new DefaultProcessor());
        errorMapping.put("Unexpected /*property*/ '{a}'.", new DefaultProcessor());
        errorMapping.put("Unexpected space between '{a}' and '{b}'.", new DefaultProcessor());
        errorMapping.put("It is not necessary to initialize '{a}' " +
                "to 'undefined'.", new DefaultProcessor());
        errorMapping.put("Unnecessary 'use strict'.", new DefaultProcessor());
        errorMapping.put("Unreachable '{a}' after '{b}'.", new DefaultProcessor());
        errorMapping.put("Unrecognized style attribute '{a}'.", new DefaultProcessor());
        errorMapping.put("Unrecognized tag '<{a}>'.", new DefaultProcessor());
        errorMapping.put("Unsafe character.", new DefaultProcessor());
        errorMapping.put("JavaScript URL.", new DefaultProcessor());
        errorMapping.put("Use the array literal notation [].", new DefaultProcessor());
        errorMapping.put("Spaces are hard to count. Use {{a}}.", new DefaultProcessor());
        errorMapping.put("Use the charAt method.", new DefaultProcessor());
        errorMapping.put("Use the object literal notation {}.", new DefaultProcessor());
        errorMapping.put("Use the || operator.", new DefaultProcessor());
        errorMapping.put("Use a named parameter.", new DefaultProcessor());
        errorMapping.put("'{a}' was used before it was defined.", new VariableUsedProblem());
        errorMapping.put("Variable {a} was not declared correctly.", new DefaultProcessor());
        errorMapping.put("Weird assignment.", new DefaultProcessor());
        errorMapping.put("Weird condition.", new DefaultProcessor());
        errorMapping.put("Weird construction. Delete 'new'.", new DefaultProcessor());
        errorMapping.put("Weird program.", new DefaultProcessor());
        errorMapping.put("Weird relation.", new DefaultProcessor());
        errorMapping.put("Weird ternary.", new DefaultProcessor());
        errorMapping.put("Wrap an immediate function invocation in parentheses " +
                "to assist the reader in understanding that the expression " +
                "is the result of a function, and not the function itself.", new DefaultProcessor());
        errorMapping.put("Wrap the /regexp/ literal in parens to " +
                "disambiguate the slash operator.", new DefaultProcessor());
        errorMapping.put("document.write can be a form of eval.", new DefaultProcessor());
    }

    public static ErrorProcessor getProcessor(@NotNull final ErrorBean errorBean){
        ErrorProcessor processor = new DefaultProcessor();
        if(errorMapping.containsKey(errorBean.getRaw())){
            processor = errorMapping.get(errorBean.getRaw());
        }
        return  processor;
    }
}
