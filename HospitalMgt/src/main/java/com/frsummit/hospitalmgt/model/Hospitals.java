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
public class Hospitals {
    @Id
    private String hId;
    private String hNmae;
    private String hGroup;
    private String hQuantity;

    public Hospitals() {
    }

    public Hospitals(String hId, String hNmae, String hGroup, String hQuantity) {
        this.hId = hId;
        this.hNmae = hNmae;
        this.hGroup = hGroup;
        this.hQuantity = hQuantity;
    }

    public String gethId() {
        return hId;
    }

    public String gethNmae() {
        return hNmae;
    }

    public String gethGroup() {
        return hGroup;
    }

    public String gethQuantity() {
        return hQuantity;
    }

    @Override
    public String toString() {
        return hId + " " + hNmae;
    }
    
}
