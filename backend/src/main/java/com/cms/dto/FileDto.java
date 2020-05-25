package com.cms.dto;

import com.cms.model.DBFile;

public class FileDto {
    private String id;

    private String fileName;

    private String fileType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public static FileDto getFileDto(DBFile file) {
        FileDto fileDto = new FileDto();
        fileDto.setId(file.getFileId());
        fileDto.setFileType(file.getFileType());
        fileDto.setFileName(file.getFileName());
        return fileDto;
    }
}
