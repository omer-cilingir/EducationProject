package com.education.service.interfaces;

import com.education.model.LoginRequest;
import com.education.model.LoginResponse;
import com.education.model.entity.UserModel;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {

    LoginResponse createUser(UserModel model);

    List<UserModel> getAllUsers();

    UserModel updateUser(Long id, Map<String, Object> updateValues);

    Optional<UserModel> findByUsername(String username);

    UserModel getCurrentUser();

    UserModel activateRegistration(String key);

    Optional<UserModel> findByEmail(String email);

    LoginResponse authorizeAndLogin(LoginRequest request);
    
    UserModel findUserById(Long id);
}
