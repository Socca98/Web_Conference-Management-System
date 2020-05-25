package com.cms.services;

import com.cms.dto.FileDto;
import com.cms.exception.IssException;
import com.cms.model.DBFile;
import com.cms.repositories.FileJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class FileService {

    private Logger logger = LoggerFactory.getLogger(FileService.class);

    @Autowired
    private FileJpaRepository dbFileRepository;

    public DBFile storeFile(MultipartFile file) {

        String originalFilename = file.getOriginalFilename();

        if (Objects.isNull(originalFilename)) {
            throw new IssException("No filename!", HttpStatus.BAD_REQUEST);
        }

        String fileName = StringUtils.cleanPath(originalFilename);

        try {
            if (fileName.contains("..")) {
                throw new IssException("File name contains invalid path sequence " + fileName, HttpStatus.BAD_REQUEST);
            }

            DBFile dbFile = new DBFile(fileName, file.getContentType(), file.getBytes());

            return dbFileRepository.save(dbFile);
        } catch (IOException ex) {
            logger.error("Could not store file. ", ex);
            throw new IssException("Could not store file " + fileName + ". Please try again!", HttpStatus.BAD_REQUEST);
        }
    }

    public DBFile getFile(String fileId) {
        return dbFileRepository.findById(fileId)
                .orElseThrow(() -> new IssException("File not found with id " + fileId, HttpStatus.NOT_FOUND));
    }

    public List<FileDto> getAllFiles() {
        return dbFileRepository.findAll().stream().map(FileDto::getFileDto).collect(Collectors.toList());
    }

    public FileDto getFileInformation(String fileId) {
        return FileDto.getFileDto(dbFileRepository.getOne(fileId));
    }

}