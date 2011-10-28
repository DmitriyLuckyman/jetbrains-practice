package com.jetbrains.au.jslintplugin;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.util.*;

/**
 * User: luckyman
 */
public class ConfigurationForm {
    private JPanel rootComponent;
    private Map<String, JComponent> propertyComponent;

    public JComponent getRootComponent() {
        return rootComponent;
    }

    public boolean isModified(JsLintState jsLintState) {
        if(jsLintState == null || jsLintState.options == null){
            return true;
        }
        for (String optionName : jsLintState.options.keySet()) {
            JComponent component = propertyComponent.get(optionName);
            OptionType optionType = JsLintOption.getOptions().get(optionName).getType();
            String optionValue = jsLintState.options.get(optionName);
            if ( OptionType.BOOLEAN.equals(optionType)){
                JCheckBox checkBox = (JCheckBox) component;
                if(!Boolean.valueOf(optionValue).equals(checkBox.isSelected())){
                    return true;
                }
            } else if(OptionType.NUMBER.equals(optionType)){
                JTextComponent text = (JTextComponent) component;
                Integer integer = null;
                Integer value = null;
                try {
                    integer = Integer.valueOf(text.getText());
                    value = Integer.valueOf(optionValue);
                } catch (NumberFormatException e) {
                    // ignore
                }
                if (integer != null ? !integer.equals(value) : value != null) return true;
            } else if(OptionType.STRING_ARRAY.equals(optionType)){
                JTextComponent text = (JTextComponent) component;
                String[] splitedValue = text.getText().split(",");
                String[] value = optionValue.split(",");
                if(splitedValue.length != value.length){
                    return true;
                }
                Arrays.sort(value);
                Arrays.sort(splitedValue);
                for(int i=0; i < splitedValue.length; i++){
                    if(!splitedValue[i].trim().equals(value[i])){
                        return true;
                    }
                }
            }
        }
        return false;
    }


    public JsLintState getJsLintState() {
        JsLintState jsLintState = new JsLintState();
        jsLintState.options = new HashMap<String, String>();

        for (String optionName : propertyComponent.keySet()) {
           JComponent component = propertyComponent.get(optionName);
            JsLintOption jsLintOption = JsLintOption.getOptions().get(optionName);
            if ( OptionType.BOOLEAN.equals(jsLintOption.getType())){
                JCheckBox checkBox = (JCheckBox) component;
                jsLintState.options.put(optionName, String.valueOf(checkBox.isSelected()));
            } else if(OptionType.NUMBER.equals(jsLintOption.getType())){
                JTextComponent text = (JTextComponent) component;
                Integer integer = null;
                try {
                    integer = Integer.valueOf(text.getText());
                } catch (NumberFormatException e) {
                    // ignore
                }
                jsLintState.options.put(optionName, String.valueOf(integer));
            } else if(OptionType.STRING_ARRAY.equals(jsLintOption.getType())){
                JTextComponent text = (JTextComponent) component;
                String[] splitedValue = text.getText().split(",");
                Arrays.sort(splitedValue);
                StringBuilder result = new StringBuilder();
                for (String aSplitedValue : splitedValue) {
                    result.append(aSplitedValue.trim()).append(",");
                }
                jsLintState.options.put(optionName, result.toString());
            }
        }

        return jsLintState;
    }

    public void setJsLintState(JsLintState jsLintState) {

    }

    private void createUIComponents() {
         propertyComponent = new HashMap<String, JComponent>();
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
        int i=0;
        for (JsLintOption jsLintOption : JsLintOption.getOptions().values()) {
            if(OptionType.BOOLEAN.equals(jsLintOption.getType())){
                JCheckBox jCheckBox = new JCheckBox(jsLintOption.getName());
                jCheckBox.setToolTipText(jsLintOption.getDescription());
                if(i % 2 == 0){
                    leftPanel.add(jCheckBox);
                } else {
                    rightPanel.add(jCheckBox);
                }
                propertyComponent.put(jsLintOption.getName(), jCheckBox);
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
        numberPanel.add(Box.createRigidArea(new Dimension(20 ,10)));
        numberPanel.add(createNumberPropertyField(JsLintOption.MAXLEN));
        numberPanel.add(Box.createRigidArea(new Dimension(20 ,10)));
        numberPanel.add(createNumberPropertyField(JsLintOption.INDENT));
        numberPanel.add(Box.createVerticalGlue());
        numberPanel.setAlignmentX(0.0f);
        return numberPanel;
    }

    private JPanel createNumberPropertyField(JsLintOption<Integer> jsLintOption) {
        JPanel component = new JPanel();
        component.setLayout(new BoxLayout(component,BoxLayout.X_AXIS));
        component.add(new JLabel(jsLintOption.getName() + " : "));
        JFormattedTextField field = new JFormattedTextField(new Integer("100"));
        field.setHorizontalAlignment(SwingConstants.RIGHT);
        field.setMaximumSize(new Dimension(50, 20));
        field.setName(jsLintOption.getName());
        field.setToolTipText(jsLintOption.getDescription());
        component.add(field);
        propertyComponent.put(jsLintOption.getName(), field);
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
        textArea.invalidate();
        textArea.setName(jsLintOption.getName());
        component.setToolTipText(jsLintOption.getDescription());
        component.add(textArea);
        propertyComponent.put(jsLintOption.getName(), textArea);
        return component;
    }
}
