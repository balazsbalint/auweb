package com.legar.auweb.dto;

import com.legar.auweb.entity.AdabasField;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AdabasFieldDto {
    private Long id;
    private String name;
    private String alias;
    private String shortName; // mapped from adabasId
    private int level;
    private String type;
    private Integer length; // mapped from precision
    private Integer decimals; // mapped from scale
    private List<AdabasFieldDto> fields;

    public static AdabasFieldDto fromEntity(AdabasField entity) {
        if (entity == null) {
            return null;
        }
        AdabasFieldDto dto = new AdabasFieldDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setAlias(entity.getAlias());
        dto.setShortName(entity.getAdabasId());
        dto.setLevel(entity.getLevel());
        dto.setType(entity.getType());
        dto.setLength(entity.getPrecision());
        dto.setDecimals(entity.getScale());

        if (entity.getFields() != null) {
            dto.setFields(entity.getFields().stream()
                    .filter(f -> f instanceof AdabasField)
                    .map(f -> AdabasFieldDto.fromEntity((AdabasField) f))
                    .collect(Collectors.toList()));
        }

        return dto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getDecimals() {
        return decimals;
    }

    public void setDecimals(Integer decimals) {
        this.decimals = decimals;
    }

    public List<AdabasFieldDto> getFields() {
        return fields;
    }

    public void setFields(List<AdabasFieldDto> fields) {
        this.fields = fields;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof AdabasFieldDto that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
