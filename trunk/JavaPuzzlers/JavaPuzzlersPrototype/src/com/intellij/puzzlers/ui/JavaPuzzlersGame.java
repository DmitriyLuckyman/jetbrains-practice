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
import java.util.*;

public class JavaPuzzlersGame {
    List<JRadioButton> answers = new ArrayList<JRadioButton>();
    private final Project project;
    private JButton runButton;
    private JPanel mainPanel;
    private JTextArea codeContent;
    private JButton nextPuzzlerButton;
    private JLabel javaCodeLabel;
    private JRadioButton answer1;
    private JRadioButton answer2;
    private JRadioButton answer3;
    private JLabel isRight;
    private JButton answerButton;
    private int puzzlerNumber;
    private int rightAnswer;

    private void parseAnswers(int number) {
        try {
            File answerTemplateFile = getAnswersFile(number);
            Scanner sc = new Scanner(answerTemplateFile);
            answer1.setText(sc.nextLine());
            answer2.setText(sc.nextLine());
            answer3.setText(sc.nextLine());
            rightAnswer = sc.nextInt();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private boolean checkAnswer() {
        if (answers.get(rightAnswer-1).isSelected()) {
            isRight.setText("Your answer is right!");
            return true;
        } else {
            isRight.setText("Right answer is #"+rightAnswer);
            return false;
        }
    }

    private void addPuzzler(int number) {
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
            parseAnswers(number);
        } catch (FileNotFoundException e) {
            runButton.setEnabled(false);
            e.printStackTrace();
        }
    }

    private File getAnswersFile(int number) {
        IdeaPluginDescriptor descriptor = PluginManager.getPlugin(PluginId.getId("com.jetbrains.puzzlers"));
        String path = descriptor.getPath().getAbsolutePath() + File.separator;
        return new File(path + "classes" + File.separator + "Answers" + number + ".in");
    }

    private File getPuzzleFile(int number) {
        IdeaPluginDescriptor descriptor = PluginManager.getPlugin(PluginId.getId("com.jetbrains.puzzlers"));
        String path = descriptor.getPath().getAbsolutePath() + File.separator;
        return new File(path + "classes" + File.separator + "Puzzler" + number + ".in");
    }

    private void initAnswers() {
        answers.clear();
        answers.add(answer1);
        answers.add(answer2);
        answers.add(answer3);
    }

    public JavaPuzzlersGame(Project project) {
        this.project = project;
        puzzlerNumber = 1;
        initAnswers();
        answerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkAnswer();
                runButton.setEnabled(true);
                answerButton.setEnabled(false);
            }
        });
        nextPuzzlerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                answerButton.setEnabled(true);
                runButton.setEnabled(false);
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
