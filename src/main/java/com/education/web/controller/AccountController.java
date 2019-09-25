package com.education.web.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.education.converter.UserConverter;
import com.education.model.LoginRequest;
import com.education.model.LoginResponse;
import com.education.model.UserRegisterRequest;
import com.education.model.dto.UserDto;
import com.education.model.entity.UserModel;
import com.education.service.interfaces.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class AccountController {

    private final UserService userService;
    private final UserConverter userConverter;

    @PostMapping(path = "/register", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    public LoginResponse register(@Valid @RequestBody UserRegisterRequest userRequest) {
        UserModel user = userConverter.requestToEntity(userRequest);
        return userService.createUser(user);
    }

    @CrossOrigin
    @PostMapping({"/login"})
    public LoginResponse authorize(@Valid @RequestBody LoginRequest request) {
        return userService.authorizeAndLogin(request);
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @RequestBody Map<String, Object> updateValues) {
        UserModel updatedUser = this.userService.updateUser(id, updateValues);
        return ResponseEntity.ok(userConverter.toDto(updatedUser));
    }

    @GetMapping(path = "/allUsers")
    public List<UserDto> getAllUserList() {

        return userConverter.toListDto(userService.getAllUsers());
    }

    @GetMapping(path = "/user/{username}")
    public ResponseEntity<?> getByUserName(@PathVariable String username) {
        final Optional<UserModel> userModel = userService.findByUsername(username);
        if (!userModel.isPresent()) {
            return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
        }
        //TODO bişeyler yapılabilir optional dönmek yerine,optional kontrolu içerde yapılabilir
        return ResponseEntity.ok(userConverter.toDto(userModel.get()));
    }

    @GetMapping(path = "/activate")
    public UserDto activateUser(@RequestParam(value = "key") String key) {
        UserModel userModel = userService.activateRegistration(key);
        return userConverter.toDto(userModel);
    }
}
