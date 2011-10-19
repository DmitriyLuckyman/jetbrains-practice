package com.intellij.tutorial.helloWorld;

import javax.swing.*;

public class HelloWorldConfiguration {
    private JTextField phraseField;
    private JLabel phraseLabel;
    private JPanel rootComponent;

    public HelloWorldConfiguration() {
        phraseLabel.setLabelFor(phraseField);
    }

    public JComponent getRootComponent() {
        return rootComponent;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    public void setData(HelloWorldApplicationComponent data) {
        phraseField.setText(data.getPhrase());
    }

    public void getData(HelloWorldApplicationComponent data) {
        data.setPhrase(phraseField.getText());
    }

    public boolean isModified(HelloWorldApplicationComponent data) {
        if (phraseField.getText() != null ? !phraseField.getText().equals(data.getPhrase()) : data.getPhrase() != null)
            return true;
        return false;
    }
}
