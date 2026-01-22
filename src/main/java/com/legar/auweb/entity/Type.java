package com.legar.auweb.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderColumn;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import java.io.Serializable;
import java.util.List;

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "RECORD_TYPE", discriminatorType = DiscriminatorType.STRING, length = 3)
@DiscriminatorValue("GEN")
@Entity
@Table(name = "TYPE", indexes = {@Index(name = "index_parent", columnList = "PARENT")})
public class Type implements Serializable {

  private Long id;
  private String name;
  private String alias;
  private Integer level;
  private String type;
  private Integer precision;
  private Integer scale;
  private Integer occurs;
  private Type parent;
  private Type redefine;
  private List<Type> fields;
  private Type physical;
  private UsingOfParameter usingOfParameter;
  private int version;

  @Id
  @GeneratedValue
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  /**
   * Gets the value of the name property.
   *
   * @return possible object is {@link String }
   *
   */
  @Basic
  public String getName() {
    return name;
  }

  /**
   * Sets the value of the name property.
   *
   * @param value allowed object is {@link String }
   *
   */
  public void setName(String value) {
    this.name = value;
  }

  /**
   * Retrieves the alias associated with this object.
   *
   * @return the alias as a {@link String}, or {@code null} if no alias is set
   */
  @Nullable
  public String getAlias() {
    return alias;
  }

  public void setAlias(@Nullable String alias) {
    this.alias = alias;
  }

  /**
   * Gets the value of the level property.
   *
   * @return possible object is {@link Integer }
   *
   */
  @Column(name = "DECLARED_LEVEL")
  public Integer getLevel() {
    return level;
  }

  /**
   * Sets the value of the level property.
   *
   * @param value allowed object is {@link Integer }
   *
   */
  public void setLevel(Integer value) {
    this.level = value;
  }

  /**
   * Gets the value of the type property.
   *
   * @return possible object is {@link String }
   *
   */
  @Basic
  public String getType() {
    return type;
  }

  /**
   * Sets the value of the type property.
   *
   * @param value allowed object is {@link String }
   *
   */
  public void setType(String value) {
    this.type = value;
  }

  public Integer getPrecision() {
    return precision;
  }

  public void setPrecision(Integer precision) {
    this.precision = precision;
  }

  public Integer getScale() {
    return scale;
  }

  public void setScale(Integer scale) {
    this.scale = scale;
  }

  /**
   * Gets the value of the occurs property.
   *
   * @return possible object is {@link Integer }
   *
   */
  @Basic
  public Integer getOccurs() {
    return occurs;
  }

  /**
   * Sets the value of the occurs property.
   *
   * @param value allowed object is {@link Integer }
   *
   */
  public void setOccurs(Integer value) {
    this.occurs = value;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "PARENT")
  public Type getParent() {
    return parent;
  }

  public void setParent(Type parent) {
    this.parent = parent;
  }

  /**
   * Gets the value of the redefine property.
   *
   * @return possible object is {@link Type }
   *
   */
  @ManyToOne(fetch = FetchType.LAZY, optional = true)
  @JoinColumn(name = "REDEFINE")
  public Type getRedefine() {
    return redefine;
  }

  /**
   * Sets the value of the redefine property.
   *
   * @param value allowed object is {@link String }
   *
   */
  public void setRedefine(Type value) {
    this.redefine = value;
  }

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "PARENT")
  @OrderColumn(name = "SUCCESSION")
  public List<Type> getFields() {
    return fields;
  }

  public void setFields(List<Type> fields) {
    this.fields = fields;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "PHYSICAL")
  public Type getPhysical() {
    return physical;
  }

  public void setPhysical(Type physical) {
    this.physical = physical;
  }

  @Enumerated(EnumType.ORDINAL)
  @Column(name = "USING_OF_PARAMETER", nullable = true)
  public UsingOfParameter getUsingOfParameter() {
    return usingOfParameter;
  }

  public void setUsingOfParameter(UsingOfParameter usingOfParameter) {
    this.usingOfParameter = usingOfParameter;
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
    if (!(obj instanceof Type)) {
      return false;
    }
    return id.equals(((Type) obj).getId());
  }
}
