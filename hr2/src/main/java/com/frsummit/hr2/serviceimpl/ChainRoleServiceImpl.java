package com.frsummit.hr2.serviceimpl;

import com.frsummit.hr2.model.ChainRole;
import com.frsummit.hr2.service.ChainRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service("chainRoleService")
@Transactional
public class ChainRoleServiceImpl implements ChainRoleService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ChainRole> findChainByRole(String myRole) {
        return entityManager.createQuery("SELECT cr FROM ChainRole AS cr WHERE cr.roleName = '" + myRole + "'", ChainRole.class).getResultList();
    }
}
