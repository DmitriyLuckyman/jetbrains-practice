package com.intellij.puzzlers;

import com.intellij.ide.plugins.IdeaPluginDescriptor;
import com.intellij.ide.plugins.PluginManager;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.extensions.PluginId;
import com.intellij.openapi.project.Project;
import com.intellij.puzzlers.ui.JavaPuzzlersGame;
import com.intellij.puzzlers.ui.Login;
import org.tmatesoft.sqljet.core.SqlJetException;
import org.tmatesoft.sqljet.core.SqlJetTransactionMode;
import org.tmatesoft.sqljet.core.table.ISqlJetCursor;
import org.tmatesoft.sqljet.core.table.ISqlJetTable;
import org.tmatesoft.sqljet.core.table.SqlJetDb;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class GameController implements Disposable {
    private JPanel mainPanel;

    private final String DB_NAME = "puzzlers.sql";

    private JavaPuzzlersGame javaPuzzlersGame;
    private Login login;

    private SqlJetDb db;

    public GameController(Project project) {
        if (project == null) {
            throw new RuntimeException("Project is null!");
        }
        javaPuzzlersGame.setProject(project);
        javaPuzzlersGame.addPuzzler(javaPuzzlersGame.getPuzzlerNumber());

        javaPuzzlersGame.getMainPanel().setVisible(false);
        login.addOKActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (!tryLogin(login.getLogin(), login.getPasswordHash())) {
                        System.err.print(":(");
                    } else {
                        mainPanel.remove(login.getMainPanel());
                        javaPuzzlersGame.getMainPanel().setVisible(true);
                    }
                } catch (SqlJetException e1) {
                    e1.printStackTrace(System.err);
                }
            }
        });
        login.addRegistrationActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    register(login.getLogin(), login.getPasswordHash());
                } catch (SqlJetException e1) {
                    e1.printStackTrace(System.err);
                }
            }
        });
        javaPuzzlersGame.getAnswerButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int correct = 0;
                if (javaPuzzlersGame.checkAnswer()) {
                    correct = 1;
                }
                try {
                    answer(javaPuzzlersGame.getPuzzlerNumber(), correct);
                } catch (SqlJetException e1) {
                    e1.printStackTrace(System.err);
                }
            }
        });
        try {
            setupDB();
        } catch (SqlJetException e) {
            e.printStackTrace(System.err);
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void answer(int question, int ansNum) throws SqlJetException {
        db.beginTransaction(SqlJetTransactionMode.WRITE);
        try {
            ISqlJetTable table = db.getTable("answers");
            table.insert(login.getLogin(), question, ansNum);
        } finally {
            db.commit();
        }
    }

    private void register(String login, String password) throws SqlJetException {
        db.beginTransaction(SqlJetTransactionMode.WRITE);
        try {
            ISqlJetTable table = db.getTable("users");
            table.insert(login, password);
        } finally {
            db.commit();
        }
    }

    private boolean tryLogin(String login, String password) throws SqlJetException {
        db.beginTransaction(SqlJetTransactionMode.WRITE);
        try {
            ISqlJetTable table = db.getTable("users");
            ISqlJetCursor cursor = table.lookup("login_password", login, password);
            return !cursor.eof();
        } finally {
            db.commit();
        }
    }

    private File getDBFile() {
        IdeaPluginDescriptor descriptor = PluginManager.getPlugin(PluginId.getId("com.jetbrains.puzzlers"));
        String path = descriptor.getPath().getAbsolutePath() + File.separator;
        return new File(path + "classes" + File.separator + DB_NAME);
    }

    private void setupDB() throws SqlJetException, IOException {
        File dbFile = getDBFile();
        boolean needCreateTables = !dbFile.exists();

        db = SqlJetDb.open(dbFile, true);

        if (needCreateTables) {
            db.getOptions().setAutovacuum(true);
            db.beginTransaction(SqlJetTransactionMode.WRITE);
            try {
                db.getOptions().setUserVersion(1);
            } finally {
                db.commit();
            }
            createTables();
        }
    }

    private void createTables() throws SqlJetException {
        final String createUserTableQuery = "CREATE TABLE users (login TEXT NOT NULL PRIMARY KEY , password TEXT NOT NULL)";
        final String loginPasswordIndexQuery = "CREATE INDEX login_password ON users(login,password)";
        final String createAnswersTableQuery = "CREATE TABLE answers (login TEXT NOT NULL, question INTEGER, answer INTEGER, PRIMARY KEY(login, question), FOREIGN KEY(login) REFERENCES users(login))";
        db.beginTransaction(SqlJetTransactionMode.WRITE);
        try {
            db.createTable(createUserTableQuery);
            db.createIndex(loginPasswordIndexQuery);
            db.createTable(createAnswersTableQuery);
        } finally {
            db.commit();
        }
    }

    public void dispose() {
        try {
            db.close();
        } catch (SqlJetException e) {
            e.printStackTrace(System.err);
        }
    }
}
