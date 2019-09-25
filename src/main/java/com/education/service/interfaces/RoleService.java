package com.education.service.interfaces;

import com.education.model.entity.RoleModel;

import java.util.List;

public interface RoleService {

    RoleModel addRole(String name);

    List<RoleModel> roleList();

    RoleModel findByRole(String name);

}
