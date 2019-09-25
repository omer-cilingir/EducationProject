package com.education.web.controller;

import com.education.service.interfaces.CommentRequestService;
import com.education.service.interfaces.QuestionRequestService;
import com.education.service.interfaces.UserService;
import com.education.storage.StorageService;
import com.education.web.controller.interfaces.FileUploadController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/files")
public class FileUploadControllerImpl implements FileUploadController {

    private final StorageService storageService;
    private final QuestionRequestService questionRequestService;
    private final CommentRequestService commentRequestService;
    private final UserService userService;

    @Autowired
    public FileUploadControllerImpl(StorageService storageService,
                                    QuestionRequestService questionRequestService, CommentRequestService commentRequestService, UserService userService) {
        this.storageService = storageService;
        this.questionRequestService = questionRequestService;
        this.commentRequestService = commentRequestService;
        this.userService = userService;
        this.storageService.init();
    }

    @CrossOrigin
    @GetMapping(path = "/")
    public ResponseEntity<List<String>> listUploadedFiles() throws IOException {
        return ResponseEntity.ok().body(
                this.storageService.loadAll().map(path -> path.getFileName().toString()).collect(Collectors.toList()));

    }

    @CrossOrigin
    @GetMapping("/download/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveCakeOrderImage(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }

    @CrossOrigin
    @PostMapping("/upload/question/{id}")
    public ResponseEntity<String> handleImageUploadForQuestion(@PathVariable("id") Long id,
                                                               @RequestParam("file") MultipartFile file) {

        Map<String, Object> updateValues = willUpdateValue(file, "fileUrl");
        this.questionRequestService.update(id, updateValues);

        return ResponseEntity.ok().body("SUCCESS");
    }

    @CrossOrigin
    @PostMapping("/upload/comment/{id}")
    public ResponseEntity<String> handleImageUploadForComment(@PathVariable("id") Long id,
                                                              @RequestParam("file") MultipartFile file) {

        Map<String, Object> updateValues = willUpdateValue(file, "fileUrl");
        this.commentRequestService.update(id, updateValues);

        return ResponseEntity.ok().body("SUCCESS");
    }

    @CrossOrigin
    @PostMapping("/upload/user/{id}")
    public ResponseEntity<String> handleImageUploadForUser(@PathVariable("id") Long id,
                                                           @RequestParam("file") MultipartFile file) {

        Map<String, Object> updateValues = willUpdateValue(file, "profileImageUrl");
        this.userService.updateUser(id, updateValues);

        return ResponseEntity.ok().body("SUCCESS");
    }

    private Map<String, Object> willUpdateValue(MultipartFile file, String parameterName) {
        String filename = new Date().getTime() + "";
        filename = this.storageService.store(filename, file);

        Map<String, Object> updateValues = new HashMap<>();
        updateValues.put(parameterName, filename);
        return updateValues;
    }

    @PostMapping("/test/omer")
    public String uploadJsonWithImage(@RequestPart("body") String jsonBody,
                                      @RequestPart(value = "file", required = false) MultipartFile file) {

//        try {
//            final LoginVM loginVM = JsonMapper.toObject(jsonBody, LoginVM.class);
//            //String json'ı bizim objemize dönüştürüyoruz
//        } catch (IOException e) {
//        }

        if (file != null) {
            String filename = Long.toString(Instant.now().toEpochMilli());
            this.storageService.store(filename, file);
        }
        return "test";
    }
}
