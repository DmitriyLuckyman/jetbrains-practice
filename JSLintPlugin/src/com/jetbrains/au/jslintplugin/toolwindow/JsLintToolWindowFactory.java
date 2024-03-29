package com.jetbrains.au.jslintplugin.toolwindow;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;

/**
 * User: Dmitriy Bandurin
 */
public class JsLintToolWindowFactory implements ToolWindowFactory{

    public void createToolWindowContent(@NotNull final Project project,@NotNull final ToolWindow toolWindow) {
        JsLinkToolWindow window = new JsLinkToolWindow();
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(window.getRootComponent(), "JsLintvalidator", false);
        toolWindow.getContentManager().addContent(content);
    }
}
