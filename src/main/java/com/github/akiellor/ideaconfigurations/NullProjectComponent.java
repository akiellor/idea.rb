package com.github.akiellor.ideaconfigurations;

import org.jetbrains.annotations.NotNull;

class NullProjectComponent implements com.intellij.openapi.components.ProjectComponent {
    @Override public void projectOpened() {
    }

    @Override public void projectClosed() {
    }

    @Override public void initComponent() {
    }

    @Override public void disposeComponent() {
    }

    @NotNull @Override public String getComponentName() {
        return getClass().getSimpleName();
    }
}
