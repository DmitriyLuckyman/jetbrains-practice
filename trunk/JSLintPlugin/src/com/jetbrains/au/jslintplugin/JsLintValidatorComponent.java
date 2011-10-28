package com.jetbrains.au.jslintplugin;

import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * User: luckyman
 * Date: 18.10.11
 */
@State( name = "JsLintValidationPlugin",
        storages = {
                @Storage(id = "jsLintValidationPlugin", file = "$APP_CONFIG$/jsLintValidationPlugin.xml")
        })
public class JsLintValidatorComponent implements ApplicationComponent, Configurable, PersistentStateComponent<JsLintState> {

    private JsLintState state;
    private ConfigurationForm form;

    public JsLintValidatorComponent() {}

    public void initComponent() {}

    public void disposeComponent() {}

    @NotNull
    public String getComponentName() {
        return "com.jetbrains.au.jslintplugin";
    }

    @Nls
    public String getDisplayName() {
        return "JsLintPlugin";
    }

    public Icon getIcon() {
        return Messages.getInformationIcon();
    }

    public String getHelpTopic() {
        return "";
    }

    public JComponent createComponent() {
       // if(form == null){
            form = new ConfigurationForm();
       // }
        return form.getRootComponent();
    }

    public boolean isModified() {
        return form != null && form.isModified(state);
    }

    public void apply() throws ConfigurationException {
        if(form != null){
            this.state = form.getJsLintState();
        }
    }

    public void reset() {
        if(form != null){
            this.form.setJsLintState(this.state);
        }
    }

    public void disposeUIResources() {}

    public JsLintState getState() {
        return state;
    }

    public void loadState(JsLintState state) {
        this.state = state;
    }
}
