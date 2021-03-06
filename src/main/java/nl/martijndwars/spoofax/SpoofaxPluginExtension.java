package nl.martijndwars.spoofax;

import com.google.inject.Inject;
import org.gradle.api.Project;
import org.gradle.api.provider.ListProperty;
import org.gradle.api.provider.Property;
import org.gradle.api.publish.maven.MavenPublication;
import org.gradle.api.tasks.TaskContainer;
import org.metaborg.core.language.LanguageIdentifier;
import org.metaborg.meta.core.project.ILanguageSpec;

import java.util.Collections;

import static nl.martijndwars.spoofax.SpoofaxPluginConstants.*;

public class SpoofaxPluginExtension {
  protected final Project project;
  protected final Property<Boolean> skipCompile;
  protected final Property<String> strategoFormat;
  protected final Property<String> languageVersion;
  protected final ListProperty<String> overrides;

  @Inject
  public SpoofaxPluginExtension(Project project) {
    this.project = project;

    this.skipCompile = project.getObjects().property(Boolean.class);
    this.skipCompile.set(false);

    this.strategoFormat = project.getObjects().property(String.class);
    this.strategoFormat.set(EMPTY_VALUE);

    this.languageVersion = project.getObjects().property(String.class);
    this.languageVersion.set(EMPTY_VALUE);

    this.overrides = project.getObjects().listProperty(String.class);
    this.overrides.set(Collections.emptyList());
  }

  public Property<Boolean> getSkipCompile() {
    return skipCompile;
  }

  public Property<String> getStrategoFormat() {
    return strategoFormat;
  }

  public Property<String> getLanguageVersion() {
    return languageVersion;
  }

  public ListProperty<String> getOverrides() {
    return overrides;
  }

  public void component(MavenPublication publication) {
    TaskContainer tasks = project.getTasks();

    ILanguageSpec languageSpec = SpoofaxInit.overridenLanguageSpec(project, strategoFormat, languageVersion, overrides);
    LanguageIdentifier identifier = languageSpec.config().identifier();

    publication.artifact(tasks.getByName(SPX_LANGUAGE_TASK_NAME));
    publication.setGroupId(identifier.groupId);
    publication.setArtifactId(identifier.id);
    publication.setVersion(identifier.version.toString());
  }
}
