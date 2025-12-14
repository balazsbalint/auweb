package com.legar.auweb.entity;

import javax.annotation.Nullable;

/**
 *
 * @author Bálint Balázs
 */
public enum SourceType {

  PROGRAM("NSP"),
  SUBPROGRAM("NSN"),
  SUBROUTINE("NSS"),
  LOCAL_DATA_AREA("NSL"),
  GLOBAL_DATA_AREA("NSG"),
  PARAMETER_DATA_AREA("NSA"),
  HELPROUTINE("NSH"),
  MAP("NSM"),
  DDM("NSD"),
  COPYCODE("NSC"),
  TEXT("NST");

  private final String extension;

  SourceType(String extension) {
    this.extension = extension;
  }

  public String getExtension() {
    return extension;
  }

  @Nullable
  public static SourceType fromExtension(String extension) {
    SourceType sourceType = null;

    if ((extension != null) && (extension.length() == 3)) {
      if ((((char) (extension.charAt(0) | ' ')) == 'n')
              && (((char) (extension.charAt(1) | ' ')) == 's')) {
          sourceType = switch ((char) (extension.charAt(2) | ' ')) {
              case 'p' -> PROGRAM;
              case 'n' -> SUBPROGRAM;
              case 's' -> SUBROUTINE;
              case 'l' -> LOCAL_DATA_AREA;
              case 'g' -> GLOBAL_DATA_AREA;
              case 'a' -> PARAMETER_DATA_AREA;
              case 'h' -> HELPROUTINE;
              case 'm' -> MAP;
              case 'd' -> DDM;
              case 'c' -> COPYCODE;
              case 't' -> TEXT;
              default -> sourceType;
          };
      }
    }

    return sourceType;
  }
}
