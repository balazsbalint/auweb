package com.legar.auweb.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Cacheable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OrderColumn;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.Version;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Bálint Balázs
 */
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"LIBRARY", "NAME"}))
@Cacheable
public class Program implements Serializable {

  public static final int ERROR_MESSAGE_LENGTH = 255;
  
  private Measure measure;
  private Long id;
  private String dialect;
  private String name;
  private String aliasName;
  private String uri;
  private SourceLanguage language;
  private Modifier modifier;
  private AbstractLibrary library;
  private List<Flow> flows;
  private List<InputOutput> inputs;
  private List<InputOutput> outputs;
  private List<Type> works;
  private List<ProgramError> programErrors;
  private int version;

  @Id
  @GeneratedValue
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Basic
  public String getDialect() {
    return dialect;
  }

  public void setDialect(String dialect) {
    this.dialect = dialect;
  }

  @Column(name = "NAME", nullable = false)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Column(name = "ALIAS_NAME")
  public String getAliasName() {
    return aliasName;
  }

  public void setAliasName(String aliasName) {
    this.aliasName = aliasName;
  }

  @Column(name = "URI", length = 4000)
  public String getUri() {
    return uri;
  }

  public void setUri(String uri) {
    this.uri = uri;
  }

  @Enumerated(EnumType.ORDINAL)
  @Column(name = "SOURCE_LANGUAGE")
  public SourceLanguage getLanguage() {
    return language;
  }

  public void setLanguage(SourceLanguage language) {
    this.language = language;
  }

  @Enumerated(EnumType.STRING)
  @Column(name = "MODIFIER", nullable = false)
  public Modifier getModifier() {
    return modifier;
  }

  public void setModifier(Modifier modifier) {
    this.modifier = modifier;
  }

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "LIBRARY", nullable = false)
  public AbstractLibrary getLibrary() {
    return library;
  }

  public void setLibrary(AbstractLibrary library) {
    this.library = library;
  }

  @ManyToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
  @JoinTable(name = "PROGRAM_FLOW",
             joinColumns = @JoinColumn(name = "PROGRAM"),
             inverseJoinColumns = @JoinColumn(name = "FLOW"), 
             indexes = @Index(columnList = "PROGRAM"))
  @OrderColumn(name = "SUCCESSION")
  public List<Flow> getFlows() {
    return flows;
  }

  public void setFlows(List<Flow> flows) {
    this.flows = flows;
  }

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "PROGRAM_INPUT",
             joinColumns = @JoinColumn(name = "PROGRAM"),
             inverseJoinColumns = @JoinColumn(name = "INPUT"),
             indexes = @Index(columnList = "PROGRAM"))
  public List<InputOutput> getInputs() {
    return inputs;
  }

  public void setInputs(List<InputOutput> inputs) {
    this.inputs = inputs;
  }

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "PROGRAM_OUTPUT",
             joinColumns = @JoinColumn(name = "PROGRAM"),
             inverseJoinColumns = @JoinColumn(name = "OUTPUT"),
             indexes = @Index(columnList = "PROGRAM"))
  public List<InputOutput> getOutputs() {
    return outputs;
  }

  public void setOutputs(List<InputOutput> outputs) {
    this.outputs = outputs;
  }

  @ManyToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
  @JoinTable(name = "WORKING_STORAGE",
             joinColumns = @JoinColumn(name = "PROGRAM"),
             inverseJoinColumns = @JoinColumn(name = "STRUCTURE"),
             indexes = @Index(columnList = "PROGRAM"))
  @OrderColumn(name = "SUCCESSION")
  public List<Type> getWorks() {
    return works;
  }

  public void setWorks(List<Type> works) {
    this.works = works;
  }

  @Version
  public int getVersion() {
    return version;
  }

  public void setVersion(int version) {
    this.version = version;
  }

  @Override
  public int hashCode() {
    return id == null ? System.identityHashCode(this) : id.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (id == null) {
      return this == obj;
    }
    if (!(obj instanceof Program)) {
      return false;
    }
    return id.equals(((Program) obj).getId());
  }

  @Override
  public String toString() {
    return ((name == null) || (modifier == null)) 
            ? ("no name:" + super.toString())
            : (name + ' ' + modifier);
  }

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  @JoinColumn(name = "MEASURE")
  public Measure getMeasure() {
    return measure;
  }

  public void setMeasure(Measure measure) {
    this.measure = measure;
  }

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "PROGRAM")
  public List<ProgramError> getProgramErrors() {
    return programErrors;
  }

  public void setProgramErrors(List<ProgramError> programErrors) {
    this.programErrors = programErrors;
  }

  @Transient
  public String getErrorMessage() {
    return ((programErrors == null) || programErrors.isEmpty())
            ? null
            : programErrors.get(0).getMessage();
  }
}
