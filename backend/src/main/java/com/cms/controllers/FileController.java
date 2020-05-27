package com.cms.controllers;

import com.cms.dto.FileDto;
import com.cms.model.DBFile;
import com.cms.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/files")
public class FileController {

    @Autowired
    private FileService dbFileStorageService;

    @PostMapping("/upload")
    public ResponseEntity<FileDto> uploadFile(@RequestParam(value = "file") MultipartFile file) {
        DBFile dbFile = dbFileStorageService.storeFile(file);
        return ResponseEntity.ok(FileDto.getFileDto(dbFile));
    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) {
        DBFile dbFile = dbFileStorageService.getFile(fileId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(dbFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getFileName() + "\"")
                .body(new ByteArrayResource(dbFile.getData()));
    }

    @GetMapping
    public ResponseEntity<List<FileDto>> getAllFiles() {
        return ResponseEntity.ok(dbFileStorageService.getAllFiles());
    }

    @GetMapping("/{fileId}")
    public ResponseEntity<FileDto> getFileInformation(@PathVariable String fileId) {
        return ResponseEntity.ok(dbFileStorageService.getFileInformation(fileId));
    }

}
