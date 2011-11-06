package com.intellij.puzzlers.ui;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Login {
    private JTextField loginField;
    private JPasswordField passwordField;
    private JButton OKButton;
    private JButton registrationButton;
    private JPanel mainPanel;

    public void addOKActionListener(ActionListener al) {
        OKButton.addActionListener(al);
    }

    public void addRegistrationActionListener(ActionListener al) {
        registrationButton.addActionListener(al);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public String getLogin() {
        return loginField.getText();
    }

    public String getPasswordHash() {
        try {
            char[] chars = passwordField.getPassword();
            byte[] bytes = new byte[chars.length];
            for (int i = 0; i < chars.length; ++i) {
                bytes[i] = (byte) chars[i];
            }
            return new String(MessageDigest.getInstance("MD5").digest(bytes));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace(System.err);
        }
        return null;
    }
}
