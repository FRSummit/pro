/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frsummit.hospitalmgt.model;

import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import org.hibernate.annotations.UpdateTimestamp;

/**
 *
 * @author F R SUMMIT
 */
@Entity
public class Donor {
    @Id
    private String dId;
    private String dName;
    private String sex;
    private String age;
    private String bloodGrp;
    @UpdateTimestamp
    private Date date;

    public Donor() {
    }

    public Donor(String dId, String dName, String sex, String age, String bloodGrp) {
        this.dId = dId;
        this.dName = dName;
        this.sex = sex;
        this.age = age;
        this.bloodGrp = bloodGrp;
        this.date = date;
    }

    public String getdId() {
        return dId;
    }

    public String getdName() {
        return dName;
    }

    public String getSex() {
        return sex;
    }

    public String getAge() {
        return age;
    }

    public String getBloodGrp() {
        return bloodGrp;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return dId + " " + dName;
    }
    
}
