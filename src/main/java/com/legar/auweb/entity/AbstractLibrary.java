package com.legar.auweb.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.Version;
import java.io.Serializable;

/**
 *
 * @author Bálint Balázs
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "LIBRARY_TYPE", 
                     discriminatorType = DiscriminatorType.STRING, 
                     length = 6)
@Table(name = "abstractlibrary", uniqueConstraints = @UniqueConstraint(columnNames = {"PRIME_LIBRARY", "NAME"}))
public class AbstractLibrary implements Serializable {
  protected Long id;
  protected String name;
  protected String uri;
  protected int version;
  private AbstractLibrary primeLibrary;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "PRIME_LIBRARY")
  public AbstractLibrary getPrimeLibrary() {
    return primeLibrary;
  }

  public void setPrimeLibrary(AbstractLibrary primeLibrary) {
    this.primeLibrary = primeLibrary;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (id == null) {
      return this == obj;
    }
    if (!(obj instanceof AbstractLibrary)) {
      return false;
    }
    return id.equals(((AbstractLibrary) obj).getId());
  }

  @Id
  @GeneratedValue
  public Long getId() {
    return id;
  }

  @Column(name = "NAME", nullable = false)
  public String getName() {
    return name;
  }

  @Basic
  public String getUri() {
    return uri;
  }

  @Version
  public int getVersion() {
    return version;
  }

  @Override
  public int hashCode() {
    return id == null ? System.identityHashCode(this) : id.hashCode();
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setUri(String uri) {
    this.uri = uri;
  }

  public void setVersion(int version) {
    this.version = version;
  }

  @Override
  public String toString() {
    return getClass().getSimpleName() + "{" + "id=" + id + ", name=" + name + '}';
  }
  
}
