package org.indepth.intellibuild

import groovy.util.logging.Slf4j
import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.indepth.intellibuild.task.IntelliBuildTask
import spock.lang.Specification

/**
 * Created by sud on 13/4/19.
 */
@Slf4j
class IntelliPluginSpec extends Specification {
  def "Validate that plugin is applied to project" () {
    given:
    Project project = ProjectBuilder.builder().build()
    when:
    project.getPlugins().apply "org.indepth.intellibuild"
    then:
    assert project.tasks[IntelliBuildPlugin.TASK] instanceof IntelliBuildTask
  }

  def "Validate that plugin task is added to project" () {
    when:
    Project project = ProjectBuilder.builder().build()
    then:
    assert project.task(IntelliBuildPlugin.TASK, type: IntelliBuildTask) instanceof IntelliBuildTask
  }

//  def "Validate project hierarchy" () {
//    given:
//    Project parentProject = ProjectBuilder.builder().build()
//    Project childProject1 = ProjectBuilder.builder().withParent(parentProject).build()
//    Project childProject11 = ProjectBuilder.builder().withParent(childProject1).build()
//    Project childProject2 = ProjectBuilder.builder().withParent(parentProject).build()
//    when:
//    parentProject.getPlugins().apply 'org.indepth.intellibuild.plugin'
//    DefaultTask task = parentProject.tasks[IntelliBuildPlugin.TASK] as IntelliBuildTask
//    task.execute()
//    then:
//    1
////    assert project.task(IntelliBuildPlugin.TASK, type: IntelliBuildTask) instanceof IntelliBuildTask
//  }
}
