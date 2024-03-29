package com.intellij.puzzlers.ui;

import com.intellij.ide.plugins.IdeaPluginDescriptor;
import com.intellij.ide.plugins.PluginManager;
import com.intellij.lang.ASTNode;
import com.intellij.lang.Language;
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
import com.intellij.psi.JavaTokenType;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;
import com.intellij.psi.impl.source.tree.java.ClassElement;
import com.intellij.psi.tree.TokenSet;
import com.intellij.ui.EditorTextField;
import com.intellij.util.LocalTimeCounter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JavaPuzzlersGame {
    List<JRadioButton> answers = new ArrayList<JRadioButton>();
    public final int maxQuestion = 10;
    private Project project;
    private JButton runButton;
    private JPanel mainPanel;
    private EditorTextField codeContent;

    private JButton nextPuzzlerButton;
    private JLabel javaCodeLabel;
    private JRadioButton answer1;
    private JRadioButton answer2;
    private JRadioButton answer3;
    private JLabel isRight;
    private JButton answerButton;
    private JPanel codeWrapPanel;

    private JButton previousPuzzlerButton;
    private JTextArea questionField;
    private JButton resultButton;
    private int puzzlerNumber;
    private int rightAnswer;

    private Language currentLanguage;

    public void setCurrentLanguage(Language currentLanguage) {
        this.currentLanguage = currentLanguage;
    }

    public Project getProject() {
        return project;
    }

    public JButton getRunButton() {
        return runButton;
    }

    public int getPuzzlerNumber() {
        return puzzlerNumber;
    }

    public JButton getResultButton() {
        return resultButton;
    }

    public void setPuzzlerNumber(int number) {
        puzzlerNumber = number;
    }

    public JButton getAnswerButton() {
        return answerButton;
    }


    public JButton getPreviousPuzzlerButton() {
        return previousPuzzlerButton;
    }

    public JButton getNextPuzzlerButton() {
        return nextPuzzlerButton;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    private void parseAnswers(int number) {
        try {
            InputStream answerTemplateFile = getAnswersFile(number);
            Scanner sc = new Scanner(answerTemplateFile);
            answer1.setText(sc.nextLine());
            answer2.setText(sc.nextLine());
            answer3.setText(sc.nextLine());
            rightAnswer = sc.nextInt();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void parseQuestion(int number) {
        try {
            InputStream questionTemplateFile = getQuestionFile(number);
            Scanner sc = new Scanner(questionTemplateFile);
            questionField.setText("");
            while (sc.hasNext()) {
                questionField.append(sc.nextLine());
            }
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
                    InputStream puzzlerTemplateFile = getPuzzleFile(number);
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
                    codeWrapPanel.removeAll();
                    codeWrapPanel.add(codeContent);
                    mainPanel.repaint();
                    mainPanel.revalidate();
                    javaCodeLabel.setText("Puzzler number #" + number);
                    parseAnswers(number);
                    parseQuestion(number);
                } catch (FileNotFoundException e) {
                    runButton.setEnabled(false);
                    e.printStackTrace();
                }

            }
        });
    }

    private InputStream getAnswersFile(int number) throws FileNotFoundException {
        IdeaPluginDescriptor descriptor = PluginManager.getPlugin(PluginId.getId("com.jetbrains.puzzlers"));
        String path = descriptor.getPath().getAbsolutePath() + File.separator;
        return JavaPuzzlersGame.class.getClassLoader().getResourceAsStream("Questions" + File.separator + number + File.separator + "Answers.in");
        //return new FileInputStream(new File(path + "classes" + File.separator + "Questions" + File.separator + number + File.separator + "Answers.in"));
    }

    private InputStream getPuzzleFile(int number) throws FileNotFoundException {
        IdeaPluginDescriptor descriptor = PluginManager.getPlugin(PluginId.getId("com.jetbrains.puzzlers"));
        String path = descriptor.getPath().getAbsolutePath() + File.separator;
        return JavaPuzzlersGame.class.getClassLoader().getResourceAsStream("Questions" + File.separator + number + File.separator + "Puzzler.in");
        //return new FileInputStream(new File(path + "classes" + File.separator + "Questions" + File.separator + number + File.separator + "Puzzler.in"));
    }

    private InputStream getQuestionFile(int number) throws FileNotFoundException {
        IdeaPluginDescriptor descriptor = PluginManager.getPlugin(PluginId.getId("com.jetbrains.puzzlers"));
        String path = descriptor.getPath().getAbsolutePath() + File.separator;
        return JavaPuzzlersGame.class.getClassLoader().getResourceAsStream("Questions" + File.separator + number + File.separator + "Question.in");
        //return new FileInputStream(new File(path + "classes" + File.separator + "Questions" + File.separator + number + File.separator + "Question.in"));
    }

    private void initAnswers() {
        answers.clear();
        answers.add(answer1);
        answers.add(answer2);
        answers.add(answer3);
    }

    public JavaPuzzlersGame() {
        initAnswers();
        answerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkAnswer();
                runButton.setEnabled(true);
                answerButton.setEnabled(false);
            }
        });
        runButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                final FileSaverDescriptor descriptor = new FileSaverDescriptor("Save Main class to", "");
                final FileSaverDialog dialog = FileChooserFactory.getInstance().createSaveFileDialog(
                        descriptor, project);
                VirtualFile base = project.getBaseDir();
                try {
                    VirtualFile vf = base.findFileByRelativePath("src" + File.separator + "puzzlers");
                    if (vf == null || !vf.isDirectory()) {
                        VirtualFile sourceFolder = base.findChild("src");
                        if (sourceFolder == null) {
                            sourceFolder = vf.createChildDirectory(this, "src");
                        }
                        VirtualFile puzzlersFolder = sourceFolder.findChild("puzzlers");
                        if (puzzlersFolder == null) {
                            puzzlersFolder = sourceFolder.createChildDirectory(this, "puzzlers");
                        }
                        vf = puzzlersFolder;
                    }
                    base = vf;
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                if (currentLanguage == null) {
                    return;
                }
                final VirtualFileWrapper fileWrapper = dialog.save(base, resolveClassName() + '.' + currentLanguage.getAssociatedFileType().getDefaultExtension());
                if (fileWrapper != null) {
                    ApplicationManager.getApplication().invokeLater(new Runnable() {
                        public void run() {
                            onFileToSaveChosen(fileWrapper);
                        }
                    });
                }

            }
        });
    }


    private void onFileToSaveChosen(VirtualFileWrapper fileWrapper) {
        final VirtualFile fileToSave = fileWrapper.getVirtualFile(true);
        assert fileToSave != null;
        try {
            fileToSave.setBinaryContent(("package puzzlers;\n" + codeContent.getText()).getBytes());
            FileEditorManager.getInstance(project).openFile(fileToSave, true);
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
    }

    public String resolveClassName() {
        final PsiFile psiFile = PsiFileFactory.getInstance(project).createFileFromText(
                "Main." + currentLanguage.getAssociatedFileType().getDefaultExtension(),
                currentLanguage.getAssociatedFileType(),
                codeContent.getText(),
                LocalTimeCounter.currentTime(),
                true);
        ASTNode node = psiFile.getNode();
        if (node == null) {
            return "Main";
        }
        for (ASTNode classNode : node.getChildren(TokenSet.create(ClassElement.CLASS))) {
            final ASTNode modifierList = classNode.findChildByType(ClassElement.MODIFIER_LIST);

            if (modifierList != null && modifierList.getText().contains("public"))
                return classNode.findChildByType(JavaTokenType.IDENTIFIER).getText();
        }
        while (node != null) ;
        return "Main";
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
