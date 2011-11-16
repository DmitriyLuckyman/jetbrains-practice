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
import com.jetbrains.au.jslintplugin.js.error.ErrorBean;
import com.jetbrains.au.jslintplugin.js.JSLintRunner;
import com.jetbrains.au.jslintplugin.js.JSLintRunnerManager;
import org.jetbrains.annotations.NotNull;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * User: Dmitriy Bandurin
 * Date: 29.10.11
 */
public class JsLinkToolWindow {
    private JButton runButton;
    private JButton openConfigurationButton;
    private JList errorList;
    private JPanel rootComponent;

    public JsLinkToolWindow() {
        runButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Application application = ApplicationManager.getApplication();
                JsLintValidatorComponent validator = application.getComponent(JsLintValidatorComponent.class);
                try {
                    errorList.removeAll();
                    DataContext toolContext = DataManager.getInstance().getDataContextFromFocus().getResult();
                    Project project = DataKeys.PROJECT.getData(toolContext);
                    Editor editor = FileEditorManager.getInstance(project).getSelectedTextEditor();
                    if (editor != null) {
                        Document doc = editor.getDocument();
                        JSLintRunner runner = JSLintRunnerManager.getInstance().getRunner();
                        List<ErrorBean> errorBeans = runner.validateScriptString(doc.getText(),
                                validator.getJsLintOptions());
                        if (errorBeans.size() > 0) {
                            errorList.setListData(errorBeans.toArray());
                        } else {
                            errorList.setListData(new String[]{"Errors not found"});
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
}
