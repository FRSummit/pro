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
public class RegStaf {
    @Id
    private String rs_id;
    private String rs_name;
    private Sex sex;

    public RegStaf() {
    }

    public RegStaf(String rs_id, String rs_name, Sex sex) {
        this.rs_id = rs_id;
        this.rs_name = rs_name;
        this.sex = sex;
    }

    public String getRs_id() {
        return rs_id;
    }

    public String getRs_name() {
        return rs_name;
    }

    public Sex getSex() {
        return sex;
    }

    @Override
    public String toString() {
        return rs_id + " " + rs_name;
    }
    
}
