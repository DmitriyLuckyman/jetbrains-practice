package com.intellij.puzzlers.ui;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.fileChooser.FileChooserFactory;
import com.intellij.openapi.fileChooser.FileSaverDescriptor;
import com.intellij.openapi.fileChooser.FileSaverDialog;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileWrapper;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import java.io.IOException;
import java.io.StringReader;

public class Results extends JComponent {
    private JButton okButton;
    private JPanel mainPanel;
    private JTable resultTable;
    private JTableHeader resultsHeader;
    private JButton exportButton;
    private JButton sendButton;
    private JButton xsltButton;
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

    public JButton getExportButton() {
        return exportButton;
    }

    public JButton getSendButton() {
        return sendButton;
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

    public JButton getXsltButton() {
        return xsltButton;
    }

    public String exportResultsToXML(String login) {
        StringBuilder export = new StringBuilder();
        export.append("<?xml version=\"1.0\"?>");
        export.append("<whole>");
        export.append("<title> Results of " + login + " </title> <howto>");
        for (int i = 0; i < resultTable.getRowCount(); ++i) {
            export.append("<topic><number>" + resultTable.getValueAt(i, 0).toString() + "</number><result>" + resultTable.getValueAt(i, 1).toString() + "</result></topic>");
        }
        export.append("</howto> </whole>");
        return export.toString();

    }

    public byte[] exportResultsToHtml(String login) {
        StringBuilder export = new StringBuilder();
        export.append(" <!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\"\n\"http://www.w3.org/TR/html4/strict.dtd\">");
        export.append("<html> <head> <title> Results of " + login + " </title> </head> <body> <table border=\"1\">");
        export.append("<tr><td> Question </td><td> Answer is </td></tr>");
        for (int i = 0; i < resultTable.getRowCount(); ++i) {
            export.append("<tr><td>" + resultTable.getValueAt(i, 0).toString() + "</td><td>" + resultTable.getValueAt(i, 1).toString() + "</td></tr>");
        }
        export.append("</table></body></html>");
        return export.toString().getBytes();

    }

    public void transformXML(final Project project, final String name, VirtualFile xsl) {
        try {

            final FileSaverDescriptor saverDescriptor = new FileSaverDescriptor("Save results to", "");
            final FileSaverDialog saverDialog = FileChooserFactory.getInstance().createSaveFileDialog(
                    saverDescriptor, project);
            VirtualFile base = project.getBaseDir();

            final VirtualFileWrapper fileWrapper = saverDialog.save(base, "");
            if (fileWrapper != null) {
                TransformerFactory tFactory = TransformerFactory.newInstance();

                final Transformer transformer = tFactory.newTransformer(new javax.xml.transform.stream.StreamSource(xsl.getInputStream()));

                ApplicationManager.getApplication().invokeLater(new Runnable() {
                    public void run() {
                        final VirtualFile fileToSave = fileWrapper.getVirtualFile(true);
                        assert fileToSave != null;

                        try {
                            transformer.transform(new javax.xml.transform.stream.StreamSource
                                    (new StringReader(exportResultsToXML(name))),
                                    new javax.xml.transform.stream.StreamResult
                                            (fileToSave.getOutputStream(Results.this)));
                            fileToSave.setBinaryContent(exportResultsToHtml(name));
                        } catch (IOException e) {
                            e.printStackTrace(System.err);
                        } catch (TransformerException e) {
                            e.printStackTrace(System.err);
                        }
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
