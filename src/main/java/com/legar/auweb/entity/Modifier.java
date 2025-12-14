package com.legar.auweb.entity;

/**
 *
 * @author Bálint Balázs
 */
public enum Modifier {

  PROGRAM("Program"),
  SUBPROGRAM("Subprogram"),
  SUBROUTINE("Subroutine"),
  LOCAL_DATA_AREA("Local Data Area"),
  GLOBAL_DATA_AREA("Global Data Area"),
  PARAMETER_DATA_AREA("Parameter Data Area"),
  HELPROUTINE("Helproutine"),
  MAP("Map"),
  EXTERNAL("External"),
  COPYCODE("Copycode");

  private final String displayName;

  private Modifier(String displayName) {
    this.displayName = displayName;
  }
  
  public static Modifier fromSourceType(SourceType sourceType) {
    Modifier modifier = null;

    if (sourceType != null) {
      switch (sourceType) {
        case COPYCODE:
        case DDM:
        case TEXT:
          break;

        case GLOBAL_DATA_AREA:
          modifier = GLOBAL_DATA_AREA;
          break;
          
        case HELPROUTINE:
          modifier = HELPROUTINE;
          break;
          
        case LOCAL_DATA_AREA:
          modifier = LOCAL_DATA_AREA;
          break;
          
        case MAP:
          modifier = MAP;
          break;
          
        case PARAMETER_DATA_AREA:
          modifier = PARAMETER_DATA_AREA;
          break;
          
        case PROGRAM:
          modifier = PROGRAM;
          break;
          
        case SUBPROGRAM:
          modifier = SUBPROGRAM;
          break;
          
        case SUBROUTINE:
          modifier = SUBROUTINE;
          break;
      }
    }
    return modifier;
  }

  @Override
  public String toString() {
    return displayName;
  }
}
