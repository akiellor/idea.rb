package com.github.akiellor.ideaconfigurations;

import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.project.Project;

public interface ProjectComponentFactory {
    ProjectComponent call(Project project);
}
