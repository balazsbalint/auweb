package com.legar.auweb.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;

/**
 *
 * @author Bálint Balázs
 */
@Entity
@Table(name = "PROGRAM_ERROR")
public class ProgramError implements Serializable {

  private Long id;
  private String message;
  private Program program;
  private Integer line;
  private Integer position;

  @Id
  @GeneratedValue
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Column(name = "MESSAGE", length = 0x7fff, nullable = false)
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Basic
  public Integer getLine() {
    return line;
  }

  public void setLine(Integer line) {
    this.line = line;
  }

  @Basic
  public Integer getPosition() {
    return position;
  }

  public void setPosition(Integer position) {
    this.position = position;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "PROGRAM", insertable = false, updatable = false)
  public Program getProgram() {
    return program;
  }

  public void setProgram(Program program) {
    this.program = program;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof ProgramError)) {
      return false;
    }
    if (id == null) {
      return this == obj;
    }
    return id.equals(((ProgramError) obj).getId());
  }

  @Override
  public int hashCode() {
    return id == null ? System.identityHashCode(this) : id.hashCode();
  }
}
