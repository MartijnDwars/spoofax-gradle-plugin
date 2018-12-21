package nl.martijndwars.spoofax.tasks;

import nl.martijndwars.spoofax.SpoofaxInit;
import nl.martijndwars.spoofax.SpoofaxPlugin;
import nl.martijndwars.spoofax.spoofax.GradleSpoofaxLanguageSpec;
import org.gradle.api.file.RegularFileProperty;
import org.gradle.api.internal.AbstractTask;
import org.gradle.api.provider.ListProperty;
import org.gradle.api.provider.Property;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.OutputFile;
import org.gradle.api.tasks.TaskAction;
import org.metaborg.core.MetaborgException;
import org.metaborg.spoofax.meta.core.build.LanguageSpecBuildInput;
import org.metaborg.spoofax.meta.core.project.ISpoofaxLanguageSpec;

import static nl.martijndwars.spoofax.SpoofaxInit.spoofaxMeta;

public class LanguageArchive extends AbstractTask {
  protected final RegularFileProperty outputFile;
  protected final Property<String> strategoFormat;
  protected final Property<String> version;
  protected final ListProperty<String> overrides;

  public LanguageArchive() {
    outputFile = getProject().getObjects().fileProperty();
    strategoFormat = getProject().getObjects().property(String.class);
    version = getProject().getObjects().property(String.class);
    overrides = getProject().getObjects().listProperty(String.class);
  }

  @Input
  public Property<String> getStrategoFormat() {
    return strategoFormat;
  }

  @Input
  public Property<String> getVersion() {
    return version;
  }

  @Input
  public ListProperty<String> getOverrides() {
    return overrides;
  }

  @OutputFile
  public RegularFileProperty getOutputFile() {
    return outputFile;
  }

  @TaskAction
  public void run() throws MetaborgException {
    SpoofaxPlugin.loadLanguageDependencies(getProject());

    LanguageSpecBuildInput input = buildInput();

    spoofaxMeta.metaBuilder.pkg(input);
    spoofaxMeta.metaBuilder.archive(input);
  }

  protected LanguageSpecBuildInput buildInput() throws MetaborgException {
    ISpoofaxLanguageSpec languageSpecification = languageSpec();

    return new LanguageSpecBuildInput(languageSpecification);
  }

  protected ISpoofaxLanguageSpec languageSpec() throws MetaborgException {
    ISpoofaxLanguageSpec languageSpec = SpoofaxInit.languageSpec(getProject());

    return new GradleSpoofaxLanguageSpec(languageSpec, strategoFormat, version, overrides);
  }
}
