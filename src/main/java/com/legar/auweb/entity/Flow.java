package com.legar.auweb.entity;

import jakarta.persistence.CascadeType;
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
public class Flow implements Serializable {

  private Long id;
  private List<InputOutput> inputs;
  private InputOutput output;
  private List<Step> steps;
  private int version;

  @Id
  @GeneratedValue
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "FLOW_INPUT",
             joinColumns = @JoinColumn(name = "FLOW"),
             inverseJoinColumns = @JoinColumn(name = "INPUT"),
             indexes = @Index(columnList = "FLOW"))
  public List<InputOutput> getInputs() {
    return inputs;
  }

  public void setInputs(List<InputOutput> inputs) {
    this.inputs = inputs;
  }

  @ManyToOne(fetch = FetchType.LAZY, optional = true)
  @JoinColumn(name = "OUTPUT", nullable = true)
  public InputOutput getOutput() {
    return output;
  }

  public void setOutput(InputOutput output) {
    this.output = output;
  }

  @OneToMany(cascade = CascadeType.REMOVE,
             fetch = FetchType.EAGER,
             orphanRemoval = true)
  @JoinColumn(name = "FLOW")
  @OrderColumn(name = "STEP")
  public List<Step> getSteps() {
    return steps;
  }

  public void setSteps(List<Step> steps) {
    this.steps = steps;
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
    InputOutput o = getOutput();
    return (o == null) ? ("Flow{" + "id=" + id + '}') : ("Flow to " + o.toString());
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
    if (!(obj instanceof Flow)) {
      return false;
    }
    return id.equals(((Flow) obj).getId());
  }
}
