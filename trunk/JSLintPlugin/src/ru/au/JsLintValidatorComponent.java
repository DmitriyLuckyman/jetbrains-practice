package ru.au;

import com.intellij.openapi.components.*;
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
                @Storage(id = "other", file = "$APP_CONFIG$/jsLintValidationPlugin.xml")
        })
public class JsLintValidatorComponent implements ApplicationComponent, Configurable, PersistentStateComponent<String> {

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

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    public void sayHello(){
        Messages.showMessageDialog("That works", phrase, Messages.getInformationIcon());
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
            this.phrase = "reset";
        }
    }

    public void disposeUIResources() {

    }

    public String getState() {
        return phrase;
    }

    public void loadState(String state) {
        this.phrase = state;
    }
}
