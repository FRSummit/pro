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
public class BloodSample {
    @Id
    private String sampleNo;
    private String bloodGroup;

    public BloodSample() {
    }

    public BloodSample(String sampleNo, String bloodGroup) {
        this.sampleNo = sampleNo;
        this.bloodGroup = bloodGroup;
    }

    public String getSampleNo() {
        return sampleNo;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    @Override
    public String toString() {
        return sampleNo + " " + bloodGroup;
    }
    
}
