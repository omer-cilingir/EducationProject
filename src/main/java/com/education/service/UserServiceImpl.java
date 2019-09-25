package com.education.service;

import com.education.constants.SystemConstants;
import com.education.exception.EducationException;
import com.education.mail.NotificationService;
import com.education.model.LoginRequest;
import com.education.model.LoginResponse;
import com.education.model.entity.RoleModel;
import com.education.model.entity.UserModel;
import com.education.repository.RoleRepository;
import com.education.repository.UserRepository;
import com.education.security.jwt.TokenProvider;
import com.education.service.interfaces.UserService;
import com.education.util.DateUtil;
import com.education.util.RandomUtil;
import com.education.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final NotificationService notificationService;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;

    private ModelMapper MAPPER = new ModelMapper();

    @Override
    public LoginResponse createUser(UserModel user) {

        findByUsername(user.getUsername()).ifPresent(usr -> {
            throw new EducationException(usr.getUsername() + " already exist", HttpStatus.BAD_REQUEST.value());
        });

        final RoleModel role = roleRepository.findById(1L)
                .orElseThrow(() -> new EducationException("Role bulunamadı", HttpStatus.BAD_REQUEST.value()));
        final HashSet<RoleModel> roles = new HashSet<>();
        roles.add(role);

        LoginRequest loginReq = LoginRequest.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .build();

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(false);
        user.setActivationKey(RandomUtil.generateActivationKey());
        user.setCreatedBy(SystemConstants.SYSTEM.toString());
        user.setLastModifiedBy(SystemConstants.SYSTEM.toString());
        user.setRoles(roles);

        userRepository.save(user);

        try {
            notificationService.sendEmail(user);
        } catch (Exception e) {
            log.error("Mail send error");
        }


        return authorizeAndLogin(loginReq);
    }

    @Override
    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<UserModel> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserModel activateRegistration(String key) {
        UserModel user = userRepository.findByActivationKey(key);
        user.setActivationKey(null);
        user.setActive(true);
        userRepository.save(user);
        return user;
    }

    @Override
    public Optional<UserModel> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserModel getCurrentUser() {
        return userRepository.findByUsername(SecurityUtils.getCurrentUsername()).orElse(null);
    }

    @Override
    public UserModel updateUser(Long id, Map<String, Object> updateValues) {
        String username = SecurityUtils.getCurrentUsername();
        UserModel user = userRepository.getOne(id);
        MAPPER.map(updateValues, user);
        user.setLastModifiedBy(username);
        user.setModifiedDate(DateUtil.now());
        this.userRepository.save(user);
        return user;
    }

    @Override
    public LoginResponse authorizeAndLogin(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        final UserModel details = ((UserModel) authentication.getPrincipal());
        String jwt = tokenProvider.generateToken(authentication);
        return LoginResponse.builder()
                .username(details.getUsername())
                .email(details.getEmail())
                .profileImageUrl(details.getProfileImageUrl())
                .token(jwt)
                .build();

    }
    
    public UserModel findUserById(Long id) {
    	return userRepository.getOne(id);
    }
    

}
