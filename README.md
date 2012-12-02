idea.rb
=======

idea.rb is an IntelliJ plugin which exposes the IntelliJ Plugin API (OpenAPI) into user space so you can define customizations to the IDE from within your project.

Usage
=====

1) Package the plugin with:

```bash
$ gradle -Pidea.libraries="<idea.home>/lib" distribution
```

2) Install the plugin from IntelliJ. "Plugins -> "Install From Disk"

3) Create a idea.rb file in the top level directory in you project with the following content:

```ruby
require 'java'

module UI
  include_package 'com.intellij.openapi.ui'
end

class MyAwesomePlugin
  def initialize project
    @project = project
  end

  def project_opened
    text = UI::Messages.show_input_dialog(@project, "What is your name?", "Input your name", UI::Messages.question_icon);
    UI::Messages.show_message_dialog(@project, "Hello, " + text + "!\n I am glad to see you.", "Information", UI::Messages.information_icon);
  end

  def project_closed
  end

  def init_component
  end

  def destroy_component
  end

  def component_name
    "My Awesome Plugin"
  end
end

proc { |project| MyAwesomePlugin.new(project) }
```

4) Reload your project.
