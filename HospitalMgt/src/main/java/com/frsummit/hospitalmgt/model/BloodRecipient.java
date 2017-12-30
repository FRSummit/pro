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
public class BloodRecipient {
    @Id
    private String id;
    private String name;
    private String age;
    private String quantity;
    private String bloodGroup;
    @UpdateTimestamp
    private Date regDate;

    public BloodRecipient() {
    }

    public BloodRecipient(String id, String name, String age, String quantity, String bloodGroup) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.quantity = quantity;
        this.bloodGroup = bloodGroup;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public Date getRegDate() {
        return regDate;
    }

    @Override
    public String toString() {
        return id + " " + name;
    }
    
}
