package com.frsummit.hr2.service;

import com.frsummit.hr2.model.ChainRole;

import java.util.List;

public interface ChainRoleService {
    public List<ChainRole> findChainByRole(String myRole);
}
