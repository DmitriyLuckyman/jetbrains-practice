package com.jetbrains.au.jslintplugin;

import javax.swing.*;

/**
 * User: luckyman
 */
public class ConfigurationForm {
    private JPanel rootComponent;
    private JTextField message;
    private JLabel messageLabel;

    public String getPhrase() {
        return message.getText();
    }

    public JComponent getRootComponent() {
        return rootComponent;
    }

    public void setPhrase(final String phrase) {
        this.message.setText(phrase);
    }

    public boolean isModified(JsLintValidatorComponent data) {
        if (message.getText() != null ? !message.getText().equals(data.getPhrase()) : data.getPhrase() != null)
            return true;
        return false;
    }
}
