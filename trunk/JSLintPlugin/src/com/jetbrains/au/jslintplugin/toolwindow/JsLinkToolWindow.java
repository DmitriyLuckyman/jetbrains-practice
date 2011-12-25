package com.jetbrains.au.jslintplugin.toolwindow;

import com.intellij.ide.DataManager;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ShowSettingsUtil;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.jetbrains.au.jslintplugin.JsLintValidatorComponent;
import com.jetbrains.au.jslintplugin.js.JSLintRunner;
import com.jetbrains.au.jslintplugin.js.JSLintRunnerManager;
import com.jetbrains.au.jslintplugin.js.error.ErrorBeanWrapper;
import org.jetbrains.annotations.NotNull;
import org.mozilla.javascript.NativeObject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * User: Dmitriy Bandurin
 * Date: 29.10.11
 */
public class JsLinkToolWindow {
    private JButton runButton;
    private JButton openConfigurationButton;
    private JTable errorList;
    private JPanel rootComponent;
    private JLabel totalNumberOfErrors;

    public JsLinkToolWindow() {
        runButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Application application = ApplicationManager.getApplication();
                JsLintValidatorComponent validator = application.getComponent(JsLintValidatorComponent.class);
                try {
                    ((DefaultTableModel) errorList.getModel()).setRowCount(0);
                    totalNumberOfErrors.setText("");
                    DataContext toolContext = DataManager.getInstance().getDataContextFromFocus().getResult();
                    Project project = DataKeys.PROJECT.getData(toolContext);
                    Editor editor = FileEditorManager.getInstance(project).getSelectedTextEditor();
                    if (editor != null) {
                        Document doc = editor.getDocument();
                        JSLintRunner runner = JSLintRunnerManager.getInstance().getRunner();
                        Object[] errorBeans = runner.validateScriptString(doc.getText(),
                                validator.getJsLintOptions());
                        final DefaultTableModel model = (DefaultTableModel) errorList.getModel();
                        ErrorBeanWrapper errorBeanWrapper = new ErrorBeanWrapper();
                        if (errorBeans.length > 0) {
                            totalNumberOfErrors.setText(String.format("Total Number Of Errors:%d", errorBeans.length));
                            int counter = 1;
                            for (Object errorBean : errorBeans) {
                                errorBeanWrapper.setError((NativeObject) errorBean);
                                model.addRow(errorBeanWrapper.getErrorTableView(counter++));
                            }
                        } else {
                            model.addRow(new String[]{"Errors not found", "", ""});
                        }
                    }
                } catch (IOException e1) {
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    e1.printStackTrace(new PrintWriter(out, true));
                    Messages.showErrorDialog(out.toString(), e1.getMessage());
                }
            }
        });
        openConfigurationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DataContext toolContext = DataManager.getInstance().getDataContextFromFocus().getResult();
                Project project = DataKeys.PROJECT.getData(toolContext);
                if (project != null) {
                    Configurable component = ApplicationManager.getApplication().getComponent(JsLintValidatorComponent.class);
                    ShowSettingsUtil.getInstance().editConfigurable(project, component);
                }
            }
        });
    }

    @NotNull
    public  JPanel getRootComponent() {
        return rootComponent;
    }

    private void createUIComponents() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("№");
        model.addColumn("Line");
        model.addColumn("Character");
        model.addColumn("Reason");
        errorList = new JTable(model);
        errorList.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        final TableColumn column0 = errorList.getColumnModel().getColumn(0);
        column0.setMaxWidth(100);
        column0.setMinWidth(50);
        final TableColumn column1 = errorList.getColumnModel().getColumn(1);
        column1.setMaxWidth(100);
        column1.setMinWidth(50);
        final TableColumn column2 = errorList.getColumnModel().getColumn(2);
        column2.setMaxWidth(100);
        column2.setMinWidth(50);
        errorList.getColumnModel().getColumn(3).setMinWidth(300);
    }
}
