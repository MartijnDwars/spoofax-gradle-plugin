package nl.martijndwars.spoofax;

import org.gradle.testkit.runner.BuildResult;
import org.gradle.testkit.runner.GradleRunner;
import org.gradle.testkit.runner.TaskOutcome;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.gradle.testkit.runner.TaskOutcome.SUCCESS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SpoofaxPluginFunctionalTest {
  public static String BASE_DIR = "src/test/resources/examples";

  @Test
  void testSingleProject() {
    File projectDir = new File(BASE_DIR + "/single");
    File archiveFile = new File(projectDir, "target/single-1.2.3.spoofax-language");

    TaskOutcome taskOutcome = runGradleTask(projectDir, ":publishToMavenLocal");

    assertEquals(SUCCESS, taskOutcome);
    assertTrue(archiveFile.exists());
  }

  @Test
  void testTesterProjectTestItself() {
    File projectDir = new File(BASE_DIR + "/tester");

    TaskOutcome taskOutcome = runGradleTask(projectDir, ":foo.lang:check");

    assertEquals(SUCCESS, taskOutcome);
  }

  @Test
  void testTesterProjectTestAnother() {
    File projectDir = new File(BASE_DIR + "/tester");

    TaskOutcome taskOutcome = runGradleTask(projectDir, ":foo.tests:check");

    assertEquals(SUCCESS, taskOutcome);
  }

  @Test
  void testBuildAndPublishSingleProject() {
    File projectDir = new File(BASE_DIR + "/multi");
    File archiveFile = new File(projectDir, "projectB/target/projectB-1.2.3-SNAPSHOT.spoofax-language");

    TaskOutcome taskOutcome = runGradleTask(projectDir, ":projectB:publishToMavenLocal");

    assertEquals(SUCCESS, taskOutcome);
    assertTrue(archiveFile.exists());
  }

  @Test
  void testBuildAndPublishMultiProject() {
    File projectDir = new File(BASE_DIR + "/multi");
    File archiveFile = new File(projectDir, "projectA/target/projectA-1.0.1-SNAPSHOT.spoofax-language");

    TaskOutcome taskOutcome = runGradleTask(projectDir, ":projectA:publishToMavenLocal");

    assertEquals(SUCCESS, taskOutcome);
    assertTrue(archiveFile.exists());
  }

  protected TaskOutcome runGradleTask(File projectDir, String task) {
    BuildResult buildResult = runGradle(projectDir, "clean", task);

    return buildResult.task(task).getOutcome();
  }

  protected BuildResult runGradle(File projectDir, String... args) {
    return GradleRunner.create()
      .withPluginClasspath()
      .withProjectDir(projectDir)
      .withArguments(args)
      .build();
  }
}
