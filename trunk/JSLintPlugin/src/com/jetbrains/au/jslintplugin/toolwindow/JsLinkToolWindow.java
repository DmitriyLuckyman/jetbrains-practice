package com.jetbrains.au.jslintplugin.toolwindow;

import com.intellij.ide.DataManager;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.fileTypes.StdFileTypes;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ShowSettingsUtil;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import com.jetbrains.au.jslintplugin.JsLintValidatorComponent;
import com.jetbrains.au.jslintplugin.config.JsLintOption;
import com.jetbrains.au.jslintplugin.config.JsLintState;
import com.jetbrains.au.jslintplugin.config.Option;
import com.jetbrains.au.jslintplugin.js.ErrorBean;
import com.jetbrains.au.jslintplugin.js.JSLintRunner;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
                JsLintState jsLintState = validator.getState();
                try {
                    DataContext toolContext = DataManager.getInstance().getDataContextFromFocus().getResult();
                    Project project = DataKeys.PROJECT.getData(toolContext);
                    FileEditor[] selectedEditors = FileEditorManager.getInstance(project).getSelectedEditors();
                    if (selectedEditors.length == 0) {
                        Messages.showErrorDialog("Open JS file in editor", "Error Message");
                        return;
                    }
                    JComponent compo = selectedEditors[0].getComponent();
                    DataContext context = DataManager.getInstance().getDataContext(compo);
                    VirtualFile file = DataKeys.VIRTUAL_FILE.getData(context);
                    if (file == null || !file.exists() || StdFileTypes.JS.equals(file.getFileType())) {
                        Messages.showErrorDialog("Open JS file in editor", "Error Message");
                        return;
                    }
                    List<ErrorBean> errorBeans = JSLintRunner.execute(file.getInputStream(), createOptionsList(jsLintState));
                    if (errorBeans.size() > 0) {
                        errorList.setListData(errorBeans.toArray());
                    } else {
                        errorList.setListData(new String[]{"Errors not found"});
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
                if(project != null){
                    Configurable component = ApplicationManager.getApplication().getComponent(JsLintValidatorComponent.class);
                    ShowSettingsUtil.getInstance().editConfigurable(project, component);
                }
            }
        });
    }

    private List<Option> createOptionsList(JsLintState jsLintState) {
        List<Option> optionList = new ArrayList<Option>();
        for (String optionName : jsLintState.options.keySet()) {
            optionList.add(new Option(JsLintOption.getOptions().get(optionName), jsLintState.options.get(optionName)));
        }
        return optionList;
    }

    public JPanel getRootComponent() {
        return rootComponent;
    }
}
