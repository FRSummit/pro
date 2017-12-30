package com.frsummit.hr2.model;

import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "leaves")
public class Leaves {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "leave_id")
    private int leaveId;

    @Column(name = "user_id")
    private String leaveUserId;

    @Column(name = "user_name")
    private String leaveUserName;

    @Column(name = "user_dept")
    private String userLeaveDepartment;

    @Column(name = "modify_to")
    private String modifyTo;

    @Column(name = "apply_to")
    private String applyTo;

    @Column(name = "action_by")
    private String actiondBy;

    @UpdateTimestamp
    @Column(name = "apply_date")
    private Date leaveApplyDate;

    @Column(name = "type")
    private String leaveType;

    @Column(name = "leave_from")
    private Date leaveFrom;

    @Column(name = "leave_to")
    private Date leaveTo;

    @Column(name = "leave_reason")
    private String leaveReason;

    @Column(name = "status")
    private String leaveStatus;

    @Column(name = "total")
    private int totalLeaves;

    @Column(name = "taken")
    private int takenLeaves;

    @Column(name = "balance")
    private int balanceLeaves;

    public Leaves() {
    }

    public Leaves(String leaveUserId, String leaveUserName, String userLeaveDepartment, String applyTo, String leaveType, Date leaveFrom, Date leaveTo, String leaveReason, String leaveStatus) {
        this.leaveUserId = leaveUserId;
        this.leaveUserName = leaveUserName;
        this.userLeaveDepartment = userLeaveDepartment;
        this.applyTo = applyTo;
        this.leaveType = leaveType;
        this.leaveFrom = leaveFrom;
        this.leaveTo = leaveTo;
        this.leaveReason = leaveReason;
        this.leaveStatus = leaveStatus;
        this.takenLeaves = 26;
        this.takenLeaves = 0;
        this.balanceLeaves = 26;
    }

    public int getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(int leaveId) {
        this.leaveId = leaveId;
    }

    public String getLeaveUserId() {
        return leaveUserId;
    }

    public void setLeaveUserId(String userId) {
        this.leaveUserId = leaveUserId;
    }

    public String getLeaveUserName() {
        return leaveUserName;
    }

    public void setLeaveUserName(String leaveUserName) {
        this.leaveUserName = leaveUserName;
    }

    public String getUserLeaveDepartment() {
        return userLeaveDepartment;
    }

    public void setUserLeaveDepartment(String userLeaveDepartment) {
        this.userLeaveDepartment = userLeaveDepartment;
    }

    public String getModifyTo() {
        return modifyTo;
    }

    public void setModifyTo(String modifyTo) {
        this.modifyTo = modifyTo;
    }

    public String getApplyTo() {
        return applyTo;
    }

    public void setApplyTo(String applyTo) {
        this.applyTo = applyTo;
    }

    public String getActiondBy() {
        return actiondBy;
    }

    public void setActiondBy(String actiondBy) {
        this.actiondBy = actiondBy;
    }

    public Date getLeaveApplyDate() {
        return leaveApplyDate;
    }

    public void setLeaveApplyDate(Date leaveApplyDate) {
        this.leaveApplyDate = leaveApplyDate;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public Date getLeaveFrom() {
        return leaveFrom;
    }

    public void setLeaveFrom(Date leaveFrom) {
        this.leaveFrom = leaveFrom;
    }

    public Date getLeaveTo() {
        return leaveTo;
    }

    public void setLeaveTo(Date leaveTo) {
        this.leaveTo = leaveTo;
    }

    public String getLeaveReason() {
        return leaveReason;
    }

    public void setLeaveReason(String leaveReason) {
        this.leaveReason = leaveReason;
    }

    public String getLeaveStatus() {
        return leaveStatus;
    }

    public void setLeaveStatus(String leaveStatus) {
        this.leaveStatus = leaveStatus;
    }

    public int getTotalLeaves() {
        return totalLeaves;
    }

    public void setTotalLeaves(int totalLeaves) {
        this.totalLeaves = totalLeaves;
    }

    public int getTakenLeaves() {
        return takenLeaves;
    }

    public void setTakenLeaves(int takenLeaves) {
        this.takenLeaves = takenLeaves;
    }

    public int getBalanceLeaves() {
        return balanceLeaves;
    }

    public void setBalanceLeaves(int balanceLeaves) {
        this.balanceLeaves = balanceLeaves;
    }
}
