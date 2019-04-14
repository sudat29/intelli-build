package org.indepth.intellibuild.task;

import org.gradle.api.DefaultTask;
import org.gradle.api.Project;
import org.gradle.api.tasks.TaskAction;
import org.indepth.intellibuild.extension.IntelliBuildExtension;
import org.indepth.intellibuild.util.Printer;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static org.indepth.intellibuild.util.CommandExecutor.executeCommand;
import static org.indepth.intellibuild.util.Printer.println;

/**
 * Created by sud on 13/4/19.
 */
public class IntelliBuildTask extends DefaultTask {
  private Project project;

  @TaskAction
  public void perform() {
    project = getProject();
    IntelliBuildExtension intelliBuildExtension =
        project.getExtensions().getByType(IntelliBuildExtension.class);
    println("=======================================================");
    project.getAllprojects().forEach(Printer::println);
//    Map<String, String> projectNameMap = project
//        .getAllprojects()
//        .stream()
//        .map(Project::getChildProjects)
//        .collect(Collectors.toMap(name -> name.replaceAll(":", File.separator),
//            name -> name));
//    projectNameMap.entrySet().forEach(Printer::println);
    String master = intelliBuildExtension.getMaster();
    println("Setting: " + intelliBuildExtension);
    optimizeBuild(master);
    println("=======================================================");
  }

  private void optimizeBuild(String master) {
    List<String> diffOutput = new ArrayList<>();
    try{
      String command = "git rev-parse --abbrev-ref HEAD";
      String currentBranch = executeCommand(command).get(0);
      println(currentBranch);
      String gitDiffCommand = "git diff " + currentBranch + " " + master;
      diffOutput = executeCommand(gitDiffCommand);
      println("");
    } catch (IOException | InterruptedException e) {
      println(e.getCause());
    }
    List<String> modifiedFiles = getModifiedFiles(diffOutput);
    Set<String> affectedModules = modifiedFiles
        .stream()
        .peek(Printer::println)
        .map(this::getAffectedModule)
        .collect(Collectors.toSet());
    println(affectedModules);
  }

  private String getAffectedModule(String filePath) {
    String[] folders = filePath.split("/");
    assert folders.length > 0;

    if(folders.length == 1) {
      return project.getName();
    }

    Project currentProject = project;
    List<String> modules = new LinkedList<>();
    for (String folder: folders) {
      if(currentProject.getChildProjects().containsKey(folder)) {
        modules.add(folder);
        currentProject = currentProject.getChildProjects().get(folder);
      } else {
        break;
      }
    }

    return String.join(":", modules);
  }

  private List<String> getModifiedFiles(List<String> diffOutput) {
    return diffOutput
        .stream()
        .filter(line -> line.startsWith("diff --git"))
        .map(this::getModifiedFile)
        .collect(Collectors.toList());
  }

  private String getModifiedFile(String line) {
    return line.split(" ")[2].replace("a/", "");
  }

}
