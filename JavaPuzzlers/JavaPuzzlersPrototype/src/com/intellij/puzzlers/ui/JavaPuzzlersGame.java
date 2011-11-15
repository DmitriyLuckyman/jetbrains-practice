package com.intellij.puzzlers.ui;

import com.intellij.ide.plugins.IdeaPluginDescriptor;
import com.intellij.ide.plugins.PluginManager;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.ex.EditorEx;
import com.intellij.openapi.extensions.PluginId;
import com.intellij.openapi.fileChooser.FileChooserFactory;
import com.intellij.openapi.fileChooser.FileSaverDescriptor;
import com.intellij.openapi.fileChooser.FileSaverDialog;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.fileTypes.StdFileTypes;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileWrapper;
import com.intellij.ui.EditorTextField;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JavaPuzzlersGame {
    List<JRadioButton> answers = new ArrayList<JRadioButton>();
    private Project project;
    private JButton runButton;
    private JPanel mainPanel;

    public int getPuzzlerNumber() {
        return puzzlerNumber;
    }

    private EditorTextField codeContent;
    private JButton nextPuzzlerButton;
    private JLabel javaCodeLabel;
    private JRadioButton answer1;
    private JRadioButton answer2;
    private JRadioButton answer3;
    private JLabel isRight;
    private JButton answerButton;
    private JPanel codeWrapPanel;
    private int puzzlerNumber;
    private int rightAnswer;

    public JButton getAnswerButton() {
        return answerButton;
    }

    public void setProject(Project project) {
        this.project = project;
    }

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

    public boolean checkAnswer() {
        if (answers.get(rightAnswer - 1).isSelected()) {
            isRight.setText("Your answer is right!");
            return true;
        } else {
            isRight.setText("Right answer is #" + rightAnswer);
            return false;
        }
    }

    public void addPuzzler(final int number) {
        ApplicationManager.getApplication().invokeLater(new Runnable() {
            public void run() {
                try {
                    File puzzlerTemplateFile = getPuzzleFile(number);
                    Scanner sc = new Scanner(puzzlerTemplateFile);
                    StringBuilder sb = new StringBuilder();
                    while (sc.hasNext()) {
                        sb.append(sc.nextLine());
                        if (sc.hasNext()) {
                            sb.append("\n");
                        }
                    }
                    if (project == null) {
                        throw new RuntimeException("project is null!");
                    }
                    codeContent = new EditorTextField(sb.toString(), project, StdFileTypes.JAVA) {
                        protected EditorEx createEditor() {
                            EditorEx editor = super.createEditor();
                            editor.setVerticalScrollbarVisible(true);
                            return editor;
                        }

                        @Override
                        protected boolean isOneLineMode() {
                            return false;
                        }
                    };
                    codeWrapPanel.add(codeContent);
                    mainPanel.repaint();
                    mainPanel.revalidate();
                    javaCodeLabel.setText("Puzzler number #" + number);
                    parseAnswers(number);
                } catch (FileNotFoundException e) {
                    runButton.setEnabled(false);
                    e.printStackTrace();
                }

            }
        });
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

    public JavaPuzzlersGame() {
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
                final FileSaverDescriptor descriptor = new FileSaverDescriptor("Save Main class to", "");
                final FileSaverDialog dialog = FileChooserFactory.getInstance().createSaveFileDialog(
                        descriptor, project);
                final VirtualFileWrapper fileWrapper = dialog.save(project.getBaseDir(), "Main.java");
                if (fileWrapper != null) {
                    onFileToSaveChosen(fileWrapper);
                }

            }
        });
    }


    private void onFileToSaveChosen(VirtualFileWrapper fileWrapper) {
        final VirtualFile fileToSave = fileWrapper.getVirtualFile(true);
        assert fileToSave != null;
        try {
            fileToSave.setBinaryContent(codeContent.getText().getBytes());
            FileEditorManager.getInstance(project).openFile(fileToSave, true);
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
