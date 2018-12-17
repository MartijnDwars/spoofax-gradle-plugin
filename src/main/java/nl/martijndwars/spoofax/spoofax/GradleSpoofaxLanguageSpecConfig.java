package nl.martijndwars.spoofax.spoofax;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.List;

import mb.nabl2.config.NaBL2Config;
import org.metaborg.core.config.IExportConfig;
import org.metaborg.core.config.IGenerateConfig;
import org.metaborg.core.config.ISourceConfig;
import org.metaborg.core.config.JSGLRVersion;
import org.metaborg.core.config.Sdf2tableVersion;
import org.metaborg.core.language.LanguageContributionIdentifier;
import org.metaborg.core.language.LanguageIdentifier;
import org.metaborg.spoofax.meta.core.config.IBuildStepConfig;
import org.metaborg.spoofax.meta.core.config.ISpoofaxLanguageSpecConfig;
import org.metaborg.spoofax.meta.core.config.PlaceholderCharacters;
import org.metaborg.spoofax.meta.core.config.SdfVersion;
import org.metaborg.spoofax.meta.core.config.StrategoFormat;
import org.metaborg.util.cmd.Arguments;

public class GradleSpoofaxLanguageSpecConfig implements ISpoofaxLanguageSpecConfig {
  protected final ISpoofaxLanguageSpecConfig config;
  protected final StrategoFormat strategoFormat;

  public GradleSpoofaxLanguageSpecConfig(ISpoofaxLanguageSpecConfig config, StrategoFormat strategoFormat) {
    this.config = config;
    this.strategoFormat = strategoFormat;
  }

  @Override
  public SdfVersion sdfVersion() {
    return config.sdfVersion();
  }

  @Override
  public String sdfMainFile() {
    return config.sdfMainFile();
  }

  @Override
  public List<String> sdfMetaFiles() {
    return config.sdfMetaFiles();
  }

  @Override
  public PlaceholderCharacters placeholderChars() {
    return config.placeholderChars();
  }

  @Override
  public String prettyPrintLanguage() {
    return config.prettyPrintLanguage();
  }

  @Nullable
  @Override
  public String sdfExternalDef() {
    return config.sdfExternalDef();
  }

  @Override
  public Arguments sdfArgs() {
    return config.sdfArgs();
  }

  @Override
  public StrategoFormat strFormat() {
    return strategoFormat;
  }

  @Nullable
  @Override
  public String strExternalJar() {
    return config.strExternalJar();
  }

  @Nullable
  @Override
  public String strExternalJarFlags() {
    return config.strExternalJarFlags();
  }

  @Override
  public Arguments strArgs() {
    return config.strArgs();
  }

  @Override
  public Collection<IBuildStepConfig> buildSteps() {
    return config.buildSteps();
  }

  @Override
  public String esvName() {
    return config.esvName();
  }

  @Override
  public String sdfName() {
    return config.sdfName();
  }

  @Override
  public String metaSdfName() {
    return config.metaSdfName();
  }

  @Override
  public String strategoName() {
    return config.strategoName();
  }

  @Override
  public String packageName() {
    return config.packageName();
  }

  @Override
  public String javaName() {
    return config.javaName();
  }

  @Override
  public Collection<String> pardonedLanguages() {
    return config.pardonedLanguages();
  }

  @Override
  public boolean useBuildSystemSpec() {
    return config.useBuildSystemSpec();
  }

  @Override
  public LanguageIdentifier identifier() {
    return config.identifier();
  }

  @Override
  public String name() {
    return config.name();
  }

  @Override
  public Collection<LanguageContributionIdentifier> langContribs() {
    return config.langContribs();
  }

  @Override
  public Collection<IGenerateConfig> generates() {
    return config.generates();
  }

  @Override
  public Boolean sdfEnabled() {
    return config.sdfEnabled();
  }

  @Override
  public String parseTable() {
    return config.parseTable();
  }

  @Override
  public String completionsParseTable() {
    return config.completionsParseTable();
  }

  @Override
  public Sdf2tableVersion sdf2tableVersion() {
    return config.sdf2tableVersion();
  }

  @Override
  public JSGLRVersion jsglrVersion() {
    return config.jsglrVersion();
  }

  @Override
  public Collection<IExportConfig> exports() {
    return config.exports();
  }

  @Override
  public String metaborgVersion() {
    return config.metaborgVersion();
  }

  @Override
  public Collection<ISourceConfig> sources() {
    return config.sources();
  }

  @Override
  public Collection<LanguageIdentifier> compileDeps() {
    return config.compileDeps();
  }

  @Override
  public Collection<LanguageIdentifier> sourceDeps() {
    return config.sourceDeps();
  }

  @Override
  public Collection<LanguageIdentifier> javaDeps() {
    return config.javaDeps();
  }

  @Override
  public boolean typesmart() {
    return config.typesmart();
  }

  @Override
  public NaBL2Config nabl2Config() {
    return config.nabl2Config();
  }
}