package nl.martijndwars.spoofax.tasks;

import org.gradle.api.NonNullApi;
import org.gradle.api.file.RegularFileProperty;
import org.gradle.api.internal.file.copy.CopyAction;
import org.gradle.api.provider.ListProperty;
import org.gradle.api.provider.Property;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.WorkResults;
import org.gradle.api.tasks.bundling.AbstractArchiveTask;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

/**
 * LanguageSpx is an AbstractArchiveTask that takes the output of LanguageArchive and copies it to
 * a different location.
 *
 * Being an AbstractArchiveTask, instances of this class can be passed to MavenPublication.artifact.
 * This avoids turning the LanguageArchive task into an AbstractArchiveTask.
 */
@NonNullApi
public class LanguageSpx extends AbstractArchiveTask {
  public static final String DEFAULT_EXTENSION = "spoofax-language";

  protected final RegularFileProperty inputFile;
  protected final Property<String> strategoFormat;
  protected final Property<String> version;
  protected final ListProperty<String> overrides;

  public LanguageSpx() {
    setExtension(DEFAULT_EXTENSION);
    //setBaseName("");

    inputFile = getProject().getObjects().fileProperty();
    strategoFormat = getProject().getObjects().property(String.class);
    version = getProject().getObjects().property(String.class);
    overrides = getProject().getObjects().listProperty(String.class);

    // TODO: This necessary, because without inputs, this task is skipped. But the input should be a lazy file, as we don't know the name during configuration.
    from(getInputFile());
  }

  @Input
  public Property<String> getStrategoFormat() {
    return strategoFormat;
  }

  @Input
  public Property<String> getLanguageVersion() {
    return version;
  }

  @Input
  public ListProperty<String> getOverrides() {
    return overrides;
  }

  // TODO: Should be the output of the LanguageArchive task
  public RegularFileProperty getInputFile() {
    return inputFile;
  }

  @Override
  protected CopyAction createCopyAction() {
    return stream -> {
      try {
        Path source = getInputFile().get().getAsFile().toPath();
        Path target = getArchivePath().toPath();

        getLogger().info("Copy " + source + " to " + target);
        Files.copy(source, target, REPLACE_EXISTING);

        return WorkResults.didWork(true);
      } catch (IOException e) {
        throw new RuntimeException("Unable to copy the .spoofax-language archive.", e);
      }
    };
  }
}
