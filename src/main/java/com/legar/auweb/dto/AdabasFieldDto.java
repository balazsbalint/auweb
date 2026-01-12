package com.legar.auweb.dto;

import java.util.List;

public class AdabasFieldDto {

    private int file;
    private String name;
    private String shortName;
    private String type;
    private int length;
    private int decimals;
    private boolean periodic;
    private List<AdabasFieldDto> fields;

    public String getName() {
        return name;
    }

    public int getFile() {
        return file;
    }

    public void setFile(int file) {
        this.file = file;
    }

    public int getDecimals() {
        return decimals;
    }

    public void setDecimals(int decimals) {
        this.decimals = decimals;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
