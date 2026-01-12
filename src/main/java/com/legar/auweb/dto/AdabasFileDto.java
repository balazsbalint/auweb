package com.legar.auweb.dto;

import java.util.List;

public class AdabasFileDto {

    private String name;
    private String uri;
    private int database;
    private List<AdabasFieldDto> fields;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public int getDatabase() {
        return database;
    }

    public void setDatabase(int database) {
        this.database = database;
    }

    public List<AdabasFieldDto> getFields() {
        return fields;
    }

    public void setFields(List<AdabasFieldDto> fields) {
        this.fields = fields;
    }

    @Override
    public String toString() {
        return "AdabasFileDto{" +
                "name='" + name + '\'' +
                ", uri='" + uri + '\'' +
                ", database=" + database +
                '}';
    }
}
