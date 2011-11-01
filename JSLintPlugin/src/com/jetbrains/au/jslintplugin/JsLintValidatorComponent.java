package com.jetbrains.au.jslintplugin;

import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.ui.Messages;
import com.jetbrains.au.jslintplugin.config.ConfigurationForm;
import com.jetbrains.au.jslintplugin.config.JsLintOption;
import com.jetbrains.au.jslintplugin.config.JsLintState;
import com.jetbrains.au.jslintplugin.config.Option;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * User: luckyman
 * Date: 18.10.11
 */
@State( name = "JsLintValidationPlugin",
        storages = {
                @Storage(id = "jsLintValidationPlugin", file = "$APP_CONFIG$/jsLintValidationPlugin.xml")
        })
public class JsLintValidatorComponent implements ApplicationComponent, Configurable, PersistentStateComponent<JsLintState> {

    private JsLintState state = new JsLintState();
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
        if(form == null){
            form = new ConfigurationForm();
        }
        return form.getRootComponent();
    }

    public boolean isModified() {
        return form != null && form.isModified(Collections.unmodifiableMap(state.options));
    }

    public void apply() throws ConfigurationException {
        if(form != null){
            this.state = new JsLintState();
            this.state.options = form.getJsLintOptions();
        }
    }

    public void reset() {
        if(form != null){
            this.form.setJsLintState(Collections.unmodifiableMap(this.state.options));
        }
    }

    public void disposeUIResources() {}

    @NotNull
    public List<Option> getJsLintOptions(){
        List<Option> optionList = new ArrayList<Option>();
        for (String optionName : state.options.keySet()) {
            optionList.add(new Option(JsLintOption.getOptions().get(optionName), state.options.get(optionName)));
        }
        return optionList;
    }

    @NotNull
    public JsLintState getState() {
        return state;
    }

    public void loadState(@Nullable final JsLintState state) {
        if(state != null){
            this.state = state;
        }
    }
}
