package com.legar.auweb.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 *
 * @author Bálint Balázs
 */
@Entity
@DiscriminatorValue("ADA")
public class AdabasDatabase extends AbstractLibrary {
  private int databaseId;

  @Column(name = "DATABASE_ID")
  public int getDatabaseId() {
    return databaseId;
  }

  public void setDatabaseId(int databaseId) {
    this.databaseId = databaseId;
  }
}
