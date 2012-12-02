package com.github.akiellor.ideaconfigurations;

import com.google.common.io.CharStreams;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import org.jruby.Ruby;
import org.jruby.RubyInstanceConfig;
import org.jruby.runtime.builtin.IRubyObject;

import java.io.IOException;
import java.io.InputStreamReader;

public class IdeaRuby implements com.intellij.openapi.components.ProjectComponent {
    private com.intellij.openapi.components.ProjectComponent component;

    public IdeaRuby(Project project) throws IOException {
        RubyInstanceConfig config = new RubyInstanceConfig();
        config.setLoader(IdeaRuby.class.getClassLoader());
        Ruby runtime = Ruby.newInstance(config);
        VirtualFile ideaRuby = project.getProjectFile().getParent().findChild("idea.rb");

        if(ideaRuby == null){
            component = new NullProjectComponent();
        }else{
            IRubyObject iRubyObject = runtime.evalScriptlet(CharStreams.toString(new InputStreamReader(ideaRuby.getInputStream())));
            component = ((ProjectComponentFactory) iRubyObject.toJava(ProjectComponentFactory.class)).call(project);
        }
    }

    @Override public void projectOpened() {
        component.projectOpened();
    }

    @Override public void projectClosed() {
        component.projectClosed();
    }

    @Override public void initComponent() {
        component.initComponent();
    }

    @Override public void disposeComponent() {
        component.disposeComponent();
    }

    @NotNull @Override public String getComponentName() {
        return component.getComponentName();
    }
}
