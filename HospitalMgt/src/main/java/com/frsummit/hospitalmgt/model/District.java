/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frsummit.hospitalmgt.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author F R SUMMIT
 */
@Entity
public class District {
    @Id
    private String disId;
    private String disName;

    public District() {
    }

    public District(String disId, String disName) {
        this.disId = disId;
        this.disName = disName;
    }

    public String getDisId() {
        return disId;
    }

    public String getDisName() {
        return disName;
    }

    @Override
    public String toString() {
        return disId + " " + disName;
    }
    
}
