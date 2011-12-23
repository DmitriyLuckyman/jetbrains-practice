package com.intellij.puzzlers.ui;

import javax.swing.*;
import javax.swing.table.JTableHeader;

public class Results extends JComponent {
    private JButton okButton;
    private JPanel mainPanel;
    private JTable resultTable;
    private JTableHeader resultsHeader;
    private JTextArea resultTextArea;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public boolean isModified(Results data) {
        return false;
    }

    public JButton getOkButton() {
        return okButton;
    }

    public JTextArea getResultTextArea() {
        return resultTextArea;
    }

    public JTable getResultTable() {
        return resultTable;
    }

    public JTableHeader getResultsHeader() {
        return resultsHeader;
    }

}
