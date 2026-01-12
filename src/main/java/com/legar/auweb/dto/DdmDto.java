package com.legar.auweb.dto;

import com.legar.auweb.entity.Ddm;

public class DdmDto extends InputOutputDto {
    private int databaseId;
    private int fileId;

    public DdmDto(Long id, String name, String uri, int databaseId, int fileId) {
        super(id, name, uri);
        this.databaseId = databaseId;
        this.fileId = fileId;
    }

    public static DdmDto fromEntity(Ddm entity) {
        if (entity == null) return null;
        return new DdmDto(
                entity.getId(),
                entity.getName(),
                entity.getUri(),
                entity.getDatabaseId(),
                entity.getFileId()
        );
    }

    public int getDatabaseId() {
        return databaseId;
    }

    public void setDatabaseId(int databaseId) {
        this.databaseId = databaseId;
    }

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }
}
