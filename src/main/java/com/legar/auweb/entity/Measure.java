package com.legar.auweb.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import java.io.Serializable;

/**
 *
 * @author Bálint Balázs
 */
@Entity
public class Measure implements Serializable {

  private Long id;
  private int loc;
  private int nonemptyLoc;
  private int functionPoints;
  private int version;

  @Id
  @GeneratedValue
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Column(name = "LOC", nullable = false)
  public int getLoc() {
    return loc;
  }

  public void setLoc(int loc) {
    this.loc = loc;
  }

  @Column(name = "NONEMPTY_LOC", nullable = false)
  public int getNonemptyLoc() {
    return nonemptyLoc;
  }

  public void setNonemptyLoc(int nonemptyLoc) {
    this.nonemptyLoc = nonemptyLoc;
  }

  @Column(name = "FUNCTION_POINTS", nullable = false)
  public int getFunctionPoints() {
    return functionPoints;
  }

  public void setFunctionPoints(int functionPoints) {
    this.functionPoints = functionPoints;
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
    if (!(obj instanceof Measure)) {
      return false;
    }
    return id.equals(((Measure) obj).getId());
  }
}
