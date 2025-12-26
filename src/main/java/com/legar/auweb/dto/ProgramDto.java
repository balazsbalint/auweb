package com.legar.auweb.dto;

import com.legar.auweb.entity.Modifier;
import com.legar.auweb.entity.Program;
import com.legar.auweb.entity.SourceLanguage;

public class ProgramDto {
    private Long id;
    private String dialect;
    private String name;
    private String aliasName;
    private String uri;
    private SourceLanguage language;
    private Modifier modifier;
    private String library;

    public ProgramDto(Long id, String dialect, String name, String aliasName, String uri, SourceLanguage language, Modifier modifier, String library) {
        this.id = id;
        this.dialect = dialect;
        this.name = name;
        this.aliasName = aliasName;
        this.uri = uri;
        this.language = language;
        this.modifier = modifier;
        this.library = library;
    }

    /**
     * Converts a {@link Program} entity into a {@link ProgramDto} object.
     *
     * @param program the {@link Program} entity to be converted; must not be null
     * @return a {@link ProgramDto} object containing data from the given {@link Program} entity
     */
    public static ProgramDto fromEntity(Program program) {
        return new ProgramDto(
                program.getId(),
                program.getDialect(),
                program.getName(),
                program.getAliasName(),
                program.getUri(),
                program.getLanguage(),
                program.getModifier(),
                program.getLibrary().getName()
        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDialect() {
        return dialect;
    }

    public void setDialect(String dialect) {
        this.dialect = dialect;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public SourceLanguage getLanguage() {
        return language;
    }

    public void setLanguage(SourceLanguage language) {
        this.language = language;
    }

    public Modifier getModifier() {
        return modifier;
    }

    public void setModifier(Modifier modifier) {
        this.modifier = modifier;
    }

    public String getLibrary() {
        return library;
    }

    public void setLibrary(String library) {
        this.library = library;
    }
}