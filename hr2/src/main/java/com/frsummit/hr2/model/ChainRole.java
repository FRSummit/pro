package com.frsummit.hr2.model;

import javax.persistence.*;

@Entity
@Table(name = "chain_role")
public class ChainRole {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "chain_id")
    private int chainId;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "chain")
    private String roleChain;

    public ChainRole() {
    }

    public int getChainId() {
        return chainId;
    }

    public void setChainId(int chainId) {
        this.chainId = chainId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleChain() {
        return roleChain;
    }

    public void setRoleChain(String roleChain) {
        this.roleChain = roleChain;
    }
}
