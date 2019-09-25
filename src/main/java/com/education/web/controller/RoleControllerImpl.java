package com.education.web.controller;

import com.education.model.entity.RoleModel;
import com.education.service.interfaces.RoleService;
import com.education.web.controller.interfaces.RoleController;
import com.education.converter.RoleConverter;
import com.education.model.dto.RoleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/role")
@RequiredArgsConstructor
public class RoleControllerImpl implements RoleController {

    private final RoleService roleService;
    private final RoleConverter converter;

    @PostMapping(path = "/add")
    public ResponseEntity<?> addRole(@Valid @RequestBody RoleDto model) {
        RoleModel existRole = roleService.findByRole(model.getName());
        if (existRole != null) {
            return new ResponseEntity<>("ROLE ALREADY EXISTS", HttpStatus.BAD_REQUEST);
        } else {
            final RoleModel roleModel = roleService.addRole(model.getName());
            return ResponseEntity.ok(converter.toDto(roleModel));
        }
    }

    @GetMapping(path = "/all")
    public List<RoleDto> roleList() {
        return converter.toListDto(roleService.roleList());
    }
}
