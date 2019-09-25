package com.education.service;

import com.education.service.interfaces.RoleService;
import com.education.model.entity.RoleModel;
import com.education.repository.RoleRepository;
import com.education.util.DateUtil;
import com.education.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public RoleModel addRole(String name) {
        String username = SecurityUtils.getCurrentUsername();
        RoleModel role = new RoleModel();
        role.setActive(true);
        role.setCreatedDate(DateUtil.now());
        role.setCreatedBy(username);
        role.setModifiedDate(DateUtil.now());
        role.setLastModifiedBy(username);
        role.setName(name);
        roleRepository.save(role);
        return role;
    }

    @Override
    public List<RoleModel> roleList() {
        return roleRepository.findAll();
    }

    @Override
    public RoleModel findByRole(String name) {
        return roleRepository.findByName(name);
    }
}
