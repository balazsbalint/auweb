package com.legar.auweb.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderColumn;
import jakarta.persistence.Transient;

import java.util.List;

/**
 *
 * @author Bálint Balázs
 */
@Entity
@DiscriminatorValue("DDM")
public class Ddm extends InputOutput {

  private int databaseId;
  private int fileId;
  private List<Type> superdescriptors;

  @Column(name = "DATABASE_ID", nullable = false)
  public int getDatabaseId() {
    return databaseId;
  }

  public void setDatabaseId(int databaseId) {
    this.databaseId = databaseId;
  }

  @Column(name = "FILE_ID", nullable = false)
  public int getFileId() {
    return fileId;
  }

  public void setFileId(int fileId) {
    this.fileId = fileId;
  }

  @OneToMany(cascade = CascadeType.ALL,
             fetch = FetchType.LAZY,
             orphanRemoval = true)
  @JoinTable(name = "SUPERDESCRIPTOR",
             joinColumns = @JoinColumn(name = "DDM"),
             inverseJoinColumns = @JoinColumn(name = "STRUCTURE"),
             indexes = @Index(columnList = "DDM"))
  @OrderColumn(name = "SUCCESSION")
  public List<Type> getSuperdescriptors() {
    return superdescriptors;
  }

  public void setSuperdescriptors(List<Type> superdescriptors) {
    this.superdescriptors = superdescriptors;
  }

  @Transient
  @Override
  public IoType getIoType() {
    return IoType.DDM;
  }
}
