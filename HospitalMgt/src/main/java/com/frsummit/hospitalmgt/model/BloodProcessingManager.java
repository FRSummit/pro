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
public class BloodProcessingManager {
    @Id
    private String bm_id;
    private String bm_name;
    private Sex sex;

    public BloodProcessingManager() {
    }

    public BloodProcessingManager(String bm_id, String bm_name, Sex sex) {
        this.bm_id = bm_id;
        this.bm_name = bm_name;
        this.sex = sex;
    }

    public String getBm_id() {
        return bm_id;
    }

    public String getBm_name() {
        return bm_name;
    }

    public Sex getSex() {
        return sex;
    }

    @Override
    public String toString() {
        return bm_id + " " + bm_name;
    }
    
}
