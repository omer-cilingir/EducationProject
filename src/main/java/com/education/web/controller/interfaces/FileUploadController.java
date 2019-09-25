package com.education.web.controller.interfaces;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileUploadController {

    ResponseEntity<List<String>> listUploadedFiles() throws IOException;

    ResponseEntity<Resource> serveCakeOrderImage(String filename);

    ResponseEntity<String> handleImageUploadForQuestion(Long id, MultipartFile file);

    ResponseEntity<String> handleImageUploadForComment(Long id, MultipartFile file);

    ResponseEntity<String> handleImageUploadForUser(Long id, MultipartFile file);
}
