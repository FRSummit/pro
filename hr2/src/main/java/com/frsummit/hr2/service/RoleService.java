package com.frsummit.hr2.service;

import com.frsummit.hr2.model.Role;

import java.util.List;

public interface RoleService {
    public List<Role> findAllUsers(String role);
}
