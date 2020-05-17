package com.cms.controllers;

import com.cms.dto.conference.UploadFileResponse;
import com.cms.model.DBFile;
import com.cms.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;

@RestController
@RequestMapping(value = "/files")
public class FileController {

    @Autowired
    private FileService dbFileStorageService;

    @PostMapping("/upload")
    public ResponseEntity<UploadFileResponse> uploadFile(@RequestParam(value = "file") MultipartFile file) {
        DBFile dbFile = dbFileStorageService.storeFile(file);

        return ResponseEntity.ok(new UploadFileResponse(dbFile.getFileName(), dbFile.getFileId(),
                file.getContentType(), file.getSize()));
    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) {
        DBFile dbFile = dbFileStorageService.getFile(fileId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(dbFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getFileName() + "\"")
                .body(new ByteArrayResource(dbFile.getData()));
    }

}
