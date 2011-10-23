package ru.au;

import javax.swing.*;

/**
 * User: luckyman
 */
public class ConfigurationForm {
    private JPanel rootComponent;
    private JTextField message;
    private JLabel messageLabel;
    private String phrase;

    public String getPhrase() {
        return phrase;
    }

    public JComponent getRootComponent() {
        return rootComponent;
    }

    public void setPhrase(final String phrase) {
        this.phrase = phrase;
    }

    public void setData(ConfigurationForm data) {
        message.setText(data.getPhrase());
    }

    public void getData(ConfigurationForm data) {
        data.setPhrase(message.getText());
    }

    public boolean isModified(JsLintValidatorComponent data) {
        if (message.getText() != null ? !message.getText().equals(data.getPhrase()) : data.getPhrase() != null)
            return true;
        return false;
    }
}
