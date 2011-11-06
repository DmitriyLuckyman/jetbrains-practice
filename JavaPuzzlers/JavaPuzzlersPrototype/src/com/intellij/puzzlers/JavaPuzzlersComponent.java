package com.intellij.puzzlers;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.AbstractProjectComponent;
import com.intellij.openapi.project.DumbAwareRunnable;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.StartupManager;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowAnchor;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;

public class JavaPuzzlersComponent extends AbstractProjectComponent {
    public static final String TOOLWINDOW_ID = PuzzlersBundle.message("window.name");
    private ToolWindow myToolWindow;

    private final GameController gameController;

    protected JavaPuzzlersComponent(Project project) {
        super(project);
        gameController = new GameController(project);
    }

    @NotNull
    @Override
    public String getComponentName() {
        return "JavaPuzzlersComponent";
    }

    @Override
    public void projectOpened() {
        if (ApplicationManager.getApplication().isHeadlessEnvironment()) return;
        StartupManager.getInstance(myProject).registerPostStartupActivity(new DumbAwareRunnable() {
            public void run() {
                final ToolWindowManager toolWindowManager = ToolWindowManager.getInstance(myProject);
                if (toolWindowManager != null) {
                    myToolWindow = toolWindowManager.registerToolWindow(TOOLWINDOW_ID, true, ToolWindowAnchor.BOTTOM, myProject, true);
                    final Content content = ContentFactory.SERVICE.getInstance().createContent(gameController.getMainPanel(), PuzzlersBundle.message("window.content.name"), false);
                    addContent(content);
                }
            }
        });
    }

    public void addContent(Content content) {
        myToolWindow.getContentManager().addContent(content);
    }

    @Override
    public void disposeComponent() {
        super.disposeComponent();
        gameController.dispose();
    }
}
