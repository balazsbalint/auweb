package com.legar.auweb.entity;

import jakarta.persistence.Cacheable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.Version;
import java.io.Serializable;
import java.util.List;
import java.util.StringJoiner;

/**
 *
 * @author Bálint Balázs
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "IO_TYPE", 
                     discriminatorType = DiscriminatorType.STRING, 
                     length = 8)
@Table(name = "INPUT_OUTPUT", 
       uniqueConstraints = @UniqueConstraint(columnNames = {"LIBRARY", "NAME"}))
@Cacheable(true)
public abstract class InputOutput implements Serializable {

  private Long id;
  private Type record;
  private String name;
  private String uri;
  private List<InputOutput> physicals;
  private AbstractLibrary library;
  private Measure measure;
  private int version;

  @Id
  @GeneratedValue
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
  @JoinColumn(name = "RECORD")
  public Type getRecord() {
    return record;
  }

  public void setRecord(Type record) {
    this.record = record;
  }

  @Column(name = "NAME", nullable = false)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Column(name = "URI", length = 4000)
  public String getUri() {
    return uri;
  }

  public void setUri(String uri) {
    this.uri = uri;
  }

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "LOGICAL_PHYSICAL",
             joinColumns = @JoinColumn(name = "LOGICAL"),
             inverseJoinColumns = @JoinColumn(name = "PHYSICAL"),
             indexes = @Index(columnList = "LOGICAL"))
  public List<InputOutput> getPhysicals() {
    return physicals;
  }

  public void setPhysicals(List<InputOutput> physicals) {
    this.physicals = physicals;
  }

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "LIBRARY", nullable = false)
  public AbstractLibrary getLibrary() {
    return library;
  }

  public void setLibrary(AbstractLibrary library) {
    this.library = library;
  }

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  @JoinColumn(name = "MEASURE")
  public Measure getMeasure() {
    return measure;
  }

  public void setMeasure(Measure measure) {
    this.measure = measure;
  }

  @Version
  public int getVersion() {
    return version;
  }

  public void setVersion(int version) {
    this.version = version;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", InputOutput.class.getSimpleName() + "[", "]")
            .add("name='" + name + "'")
            .add("id=" + id)
            .toString();
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
    if (!(obj instanceof InputOutput)) {
      return false;
    }
    return id.equals(((InputOutput) obj).getId());
  }
}
