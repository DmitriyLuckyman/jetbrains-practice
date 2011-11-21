package com.jetbrains.au.jslintplugin.config;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.util.*;

/**
 * User: luckyman
 */
public class ConfigurationForm {
    private JPanel rootComponent;
    private Map<String, JComponent> optionComponents;

    public JComponent getRootComponent() {
        return rootComponent;
    }

    public boolean isModified(@Nullable final Map<String, String> options) {
        if (options == null) {
            return true;
        }
        for (String optionName : optionComponents.keySet()) {
            JComponent component = optionComponents.get(optionName);
            @Nullable String optionValue = options.get(optionName);
            switch (JsLintOption.getOptions().get(optionName).getType()){
                case BOOLEAN:
                    JCheckBox checkBox = (JCheckBox) component;
                    if (!Boolean.valueOf(optionValue).equals(checkBox.isSelected())) {
                        return true;
                    }
                    break;
                case NUMBER:
                    Integer integer = null;
                    Integer value = null;
                    try {
                        integer = Integer.valueOf(((JTextComponent) component).getText());

                    } catch (NumberFormatException e) {
                        // ignore
                    }

                    try
                    {
                        value = Integer.valueOf(optionValue);
                    } catch (NumberFormatException e){
                        //ignore
                    }

                    if (integer != null ? !integer.equals(value) : value != null){
                        return true;
                    }
                    break;

                case STRING_ARRAY:
                    String text = ((JTextComponent) component).getText();
                    String[] splitedValue = text == null ? new String[]{} : text.split(",");
                    String[] currentValue = optionValue == null ? new String[]{} : optionValue.split(",");

                    if (splitedValue.length != currentValue.length) {
                        return true;
                    }

                    Arrays.sort(currentValue);
                    Arrays.sort(splitedValue);
                    for (int i = 0; i < splitedValue.length; i++) {
                        if (!splitedValue[i].trim().equals(currentValue[i])) {
                            return true;
                        }
                    }
                    break;
            }
        }
        return false;
    }

    @NotNull
    public Map<String, String> getJsLintOptions() {
        Map<String, String> options = new HashMap<String, String>();
        for (String optionName : optionComponents.keySet()) {
            JComponent component = optionComponents.get(optionName);
            switch (JsLintOption.getOptions().get(optionName).getType()){
                case BOOLEAN:
                    JCheckBox checkBox = (JCheckBox) component;
                    options.put(optionName, String.valueOf(checkBox.isSelected()));
                    break;
                case NUMBER:
                    JFormattedTextField text = (JFormattedTextField) component;
                    Integer integer = null;
                    try {
                        integer = (Integer) text.getValue();
                    } catch (ClassCastException e) {
                        e.printStackTrace();
                    }
                    options.put(optionName, String.valueOf(integer));
                    break;
                case STRING_ARRAY:
                    String[] splitedValue = ((JTextComponent) component).getText().split(",");
                    Arrays.sort(splitedValue);
                    StringBuilder result = null;
                    for (String aSplitedValue : splitedValue) {
                        if (result == null) {
                            result = new StringBuilder();
                        } else {
                            result.append(",");
                        }

                        result.append(aSplitedValue.trim());
                        if(result != null){
                            options.put(optionName, result.toString());
                        }

                    }
                    break;
            }
        }

        return options;
    }

    public void setJsLintState(@NotNull final Map<String, String> options) {
        for (String optionName : optionComponents.keySet()) {
            JComponent component = optionComponents.get(optionName);
            String value = options.get(optionName);
            switch (JsLintOption.getOptions().get(optionName).getType()){
                case BOOLEAN:
                    JCheckBox checkBox = (JCheckBox) component;
                    checkBox.setSelected(Boolean.valueOf(value));
                    break;
                case NUMBER:
                    Integer integer = null;
                    try {
                        integer = Integer.valueOf(value);
                    } catch (NumberFormatException e) {
                        // ignore
                    }
                    ((JFormattedTextField) component).setValue(integer);
                    break;
                case STRING_ARRAY:
                    ((JTextArea) component).setText(value);
                    break;
            }
        }
    }

    private void createUIComponents() {
        optionComponents = new HashMap<String, JComponent>();
        rootComponent = new JPanel();
        rootComponent.setLayout(new BoxLayout(rootComponent, BoxLayout.Y_AXIS));
        rootComponent.add(createPanelForPredefProperty());
        rootComponent.add(createPanelWithNumberProperties());
        rootComponent.add(createPanelForBooleanOptions());
    }

    private JPanel createPanelForBooleanOptions() {
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
        container.setAlignmentX(0.0f);
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        container.add(leftPanel);
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        container.add(rightPanel);
        int i = 0;
        for (JsLintOption jsLintOption : JsLintOption.getOptions().values()) {
            if (OptionType.BOOLEAN.equals(jsLintOption.getType())) {
                JCheckBox jCheckBox = new JCheckBox(jsLintOption.getName());
                jCheckBox.setToolTipText(jsLintOption.getDescription());
                if (i % 2 == 0) {
                    leftPanel.add(jCheckBox);
                } else {
                    rightPanel.add(jCheckBox);
                }
                optionComponents.put(jsLintOption.getName(), jCheckBox);
                i++;
            }
        }
        return container;
    }

    private JPanel createPanelWithNumberProperties() {
        JPanel numberPanel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(numberPanel, BoxLayout.X_AXIS);
        numberPanel.setLayout(boxLayout);
        numberPanel.add(createNumberPropertyField(JsLintOption.MAXERR));
        numberPanel.add(Box.createRigidArea(new Dimension(20, 10)));
        numberPanel.add(createNumberPropertyField(JsLintOption.MAXLEN));
        numberPanel.add(Box.createRigidArea(new Dimension(20, 10)));
        numberPanel.add(createNumberPropertyField(JsLintOption.INDENT));
        numberPanel.add(Box.createVerticalGlue());
        numberPanel.setAlignmentX(0.0f);
        return numberPanel;
    }

    private JPanel createNumberPropertyField(@NotNull final JsLintOption jsLintOption) {
        JPanel component = new JPanel();
        component.setLayout(new BoxLayout(component, BoxLayout.X_AXIS));
        component.add(new JLabel(jsLintOption.getName() + " : "));
        JFormattedTextField field = new JFormattedTextField(new Integer("100"));
        field.setHorizontalAlignment(SwingConstants.RIGHT);
        field.setMaximumSize(new Dimension(50, 20));
        field.setName(jsLintOption.getName());
        field.setToolTipText(jsLintOption.getDescription());
        component.add(field);
        optionComponents.put(jsLintOption.getName(), field);
        return component;
    }

    private JPanel createPanelForPredefProperty() {
        JsLintOption jsLintOption = JsLintOption.PREDEF;
        JPanel component = new JPanel();
        component.setLayout(new BoxLayout(component, BoxLayout.Y_AXIS));
        component.setAlignmentX(0.0f);
        String label = new StringBuilder(jsLintOption.getName())
                .append(" ( ").append(jsLintOption.getDescription()).append(" ) :").toString();
        component.add(new JLabel(label));
        JTextArea textArea = new JTextArea();
        textArea.setRows(5);
        textArea.setAlignmentX(0.0f);
        textArea.invalidate();
        textArea.setName(jsLintOption.getName());
        component.setToolTipText(jsLintOption.getDescription());
        component.add(textArea);
        optionComponents.put(jsLintOption.getName(), textArea);
        return component;
    }

    /**
     * Send null if need to clear highlighting.
     * @param relatedOption option to highlight on configuration form
     */
    public void highlightOption(@Nullable final String relatedOption) {
        for (String option : this.optionComponents.keySet()) {
            final JComponent jComponent = this.optionComponents.get(option);
            final Font font = jComponent.getFont().deriveFont(option.equals(relatedOption) ? Font.BOLD : Font.PLAIN);
            jComponent.setFont(font);
            jComponent.revalidate();
        }
    }
}
