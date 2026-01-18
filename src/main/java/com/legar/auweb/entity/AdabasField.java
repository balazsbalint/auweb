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
public class AdabasField extends Type {

  private String adabasId;

  @Column(name = "ADABAS_ID", length = 2)
  public String getAdabasId() {
    return adabasId;
  }

  public void setAdabasId(String adabasId) {
    this.adabasId = adabasId;
  }
}
