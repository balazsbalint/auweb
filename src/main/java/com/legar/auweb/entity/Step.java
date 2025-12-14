package com.legar.auweb.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderColumn;
import jakarta.persistence.Version;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Bálint Balázs
 */
@Entity
public class Step implements Serializable {
  private Long id;
  private boolean transform;
  private String name;
  private Integer line;
  private String operationType;
  private Type result;
  private List<Step> substeps;
  private List<Type> arguments;
  private List<Token> conditions;
  private Step parentStep;
  private Flow flow;
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
  public boolean isTransform() {
    return transform;
  }

  public void setTransform(boolean transform) {
    this.transform = transform;
  }

  @Column(name = "NAME", nullable = false)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Basic
  public Integer getLine() {
    return line;
  }

  public void setLine(Integer line) {
    this.line = line;
  }

  @Column(name = "OPERATION_TYPE")
  public String getOperationType() {
    return operationType;
  }

  public void setOperationType(String operationType) {
    this.operationType = operationType;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "RESULT")
  public Type getResult() {
    return result;
  }

  public void setResult(Type result) {
    this.result = result;
  }

  @OneToMany(cascade = CascadeType.ALL, 
             fetch = FetchType.EAGER, 
             orphanRemoval = true)
  @JoinColumn(name = "PARENT_STEP")
  @OrderColumn(name = "SUCCESSION")
  public List<Step> getSubsteps() {
    return substeps;
  }

  public void setSubsteps(List<Step> substeps) {
    this.substeps = substeps;
  }

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "STEP_ARGUMENT", 
             joinColumns = @JoinColumn(name = "STEP"), 
             inverseJoinColumns = @JoinColumn(name = "ARGUMENT"),
             indexes = @Index(columnList = "STEP"))
  @OrderColumn(name = "SUCCESSION")
  public List<Type> getArguments() {
    return arguments;
  }

  public void setArguments(List<Type> arguments) {
    this.arguments = arguments;
  }

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  @JoinTable(name = "STEP_CONDITION")
  @OrderColumn(name = "DEPTH")
  public List<Token> getConditions() {
    return conditions;
  }

  public void setConditions(List<Token> conditions) {
    this.conditions = conditions;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "PARENT_STEP", insertable = false, updatable = false)
  public Step getParentStep() {
    return parentStep;
  }

  public void setParentStep(Step parentStep) {
    this.parentStep = parentStep;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "FLOW", insertable = false, updatable = false)
  public Flow getFlow() {
    return flow;
  }

  public void setFlow(Flow flow) {
    this.flow = flow;
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
    return (name == null) ? super.toString() : name;
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
    if (!(obj instanceof Step)) {
      return false;
    }
    return id.equals(((Step) obj).getId());
  }
}
