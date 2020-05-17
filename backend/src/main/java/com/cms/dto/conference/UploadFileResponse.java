package com.cms.dto.conference;

public class UploadFileResponse {

    private String fileName;
    private String url;
    private String contentType;
    private Long size;

    public UploadFileResponse(String fileName, String url, String contentType, long size) {
        this.fileName = fileName;
        this.url = url;
        this.contentType = contentType;
        this.size = size;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }
}
