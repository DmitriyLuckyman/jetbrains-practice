package com.intellij.puzzlers.ui;

import com.intellij.ide.highlighter.JavaFileType;
import com.intellij.ide.plugins.IdeaPluginDescriptor;
import com.intellij.ide.plugins.PluginManager;
import com.intellij.openapi.extensions.PluginId;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.testFramework.LightVirtualFile;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class JavaPuzzlersGame {
    private final Project project;
    private JButton runButton;
    private JPanel mainPanel;
    private JTextArea codeContent;
    private JRadioButton answerRadioButton;
    private JButton nextPuzzlerButton;
    private JLabel javaCodeLabel;
    private int puzzlerNumber;


    public void addPuzzler(int number) {
        try {
            File puzzlerTemplateFile = getPuzzleFile(number);
            Scanner sc = new Scanner(puzzlerTemplateFile);
            codeContent.setText("");
            while (sc.hasNext()) {
                codeContent.append(sc.nextLine());
                if (sc.hasNext()) {
                    codeContent.append("\n");
                }
            }
            javaCodeLabel.setText("Puzzler number #" + number);
        } catch (FileNotFoundException e) {
            runButton.setEnabled(false);
            e.printStackTrace();
        }
    }

    private File getPuzzleFile(int number) {
        IdeaPluginDescriptor descriptor = PluginManager.getPlugin(PluginId.getId("com.jetbrains.puzzlers"));
        String path = descriptor.getPath().getAbsolutePath() + File.separator;
        return new File(path + "classes" + File.separator + "Puzzler" + number + ".in");
    }

    public JavaPuzzlersGame(Project project) {
        this.project = project;
        puzzlerNumber = 1;
        nextPuzzlerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                puzzlerNumber++;
                addPuzzler(puzzlerNumber);
            }
        });
        runButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LightVirtualFile file = new LightVirtualFile("Main", JavaFileType.INSTANCE, codeContent.getText());
                FileEditorManager.getInstance(JavaPuzzlersGame.this.project).openFile(file, true);
            }
        });
        addPuzzler(puzzlerNumber);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
