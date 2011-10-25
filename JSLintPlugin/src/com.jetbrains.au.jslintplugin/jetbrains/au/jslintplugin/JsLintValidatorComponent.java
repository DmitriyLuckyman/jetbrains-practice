package com.jetbrains.au.jslintplugin;

import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.ui.Messages;
import com.intellij.util.xmlb.annotations.Tag;
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
public class JsLintValidatorComponent implements ApplicationComponent, Configurable, PersistentStateComponent<JsLintValidatorComponent.CustomState> {

    private String phrase;
    private ConfigurationForm form;

    public JsLintValidatorComponent() {
        this.phrase = "Hello";
    }

    public void initComponent() {}

    public void disposeComponent() {}

    @NotNull
    public String getComponentName() {
        return "ru.au.JsLintValidatorComponent";
    }

    public String getPhrase() {
        return phrase;
    }

    public void sayHello(){
        Messages.showMessageDialog(phrase, "That works", Messages.getInformationIcon());
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
        return form != null && form.isModified(this);
    }

    public void apply() throws ConfigurationException {
        if(form != null){
            this.phrase = form.getPhrase();
        }
    }

    public void reset() {
        if(form != null){
            this.form.setPhrase(this.phrase);
        }
    }

    public void disposeUIResources() {

    }

    public CustomState getState() {
      CustomState customState = new CustomState();
      customState.phase = this.phrase;
      return customState;
    }

    public void loadState(CustomState state) {
        this.phrase = state.phase;
    }

    public static class CustomState {
        @Tag("phase")
        public String phase;
    }
}
