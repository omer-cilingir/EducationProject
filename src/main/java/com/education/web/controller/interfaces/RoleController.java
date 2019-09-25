package com.education.web.controller.interfaces;

import com.education.model.dto.RoleDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RoleController {

    ResponseEntity<?> addRole(RoleDto model);

    List<RoleDto> roleList();
}
