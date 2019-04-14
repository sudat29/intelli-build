package org.indepth.intellibuild;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.indepth.intellibuild.extension.IntelliBuildExtension;
import org.indepth.intellibuild.task.IntelliBuildTask;

/**
 * Created by sud on 13/4/19.
 */
public class IntelliBuildPlugin implements Plugin<Project> {
  public static final String TASK = "intelliBuild";

  @Override
  public void apply(Project project) {
    project.getExtensions().add(TASK + "Setting", IntelliBuildExtension.class);
    project.getTasks().create(TASK, IntelliBuildTask.class);
  }
}
