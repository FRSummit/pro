package com.frsummit.hr2.service;

import com.frsummit.hr2.model.Leaves;

import java.util.List;

public interface LeaveService {
    public void saveLeave(Leaves leaves);
    public List<Leaves> findAllLeaves();
    public List<Leaves> findMyAllLeaves();

////////////////////////////////////////////////////////////////////
///////// Not yet applied //////////////////////////////////////////
////////////////////////////////////////////////////////////////////
    public List<Leaves> findAllLeavesForDin();
    public List<Leaves> findAllLeavesForChairman();
    public List<Leaves> findAllLeavesForCoordinate();
    public List<Leaves> findAllLeavesForFaculty();
    public Leaves findSelectedLeaves(String usId);
    //public String userSeenRole(String roleEmail);
/////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////

    public void updateLeaveStatus(String leaveId, String selectStatus, String leaveActionBy, String modifyTo);
    public void rollCall();
    public List<Leaves> findAllLeavesForRole();
}
