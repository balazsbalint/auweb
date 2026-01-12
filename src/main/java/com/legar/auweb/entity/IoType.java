package com.legar.auweb.entity;

/**
 *
 * @author Bálint Balázs
 */
public enum IoType {
  SEQUENTIAL("Sequential"),
  INDEXED_SEQUENTIAL("Indexed Sequential"),
  DDM("Adabas file"),
  RDBMS_TABLE("RDBMS table"),
  SCREEN("Screen"),
  PRINTER("Printer"),
  DLI("DLI"),
  MESSAGE("Message");

  private final String displayName;
  
  private IoType(String displayName) {
    this.displayName = displayName;    
  }
  
  @Override
  public String toString() {
    return displayName;
  }  
}
