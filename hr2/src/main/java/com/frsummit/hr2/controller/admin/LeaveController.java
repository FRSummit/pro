package com.frsummit.hr2.controller.admin;

import com.frsummit.hr2.configuration.LeaveConfig;
import com.frsummit.hr2.model.Leaves;
import com.frsummit.hr2.model.Message;
import com.frsummit.hr2.model.User;
import com.frsummit.hr2.service.LeaveService;
import com.frsummit.hr2.service.MessageService;
import com.frsummit.hr2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class LeaveController {

    @Autowired
    private LeaveService leaveService;

    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;



///////////////////////Employee Leaves/////////////////////////////////////////////////




    @RequestMapping(value = "/admin/leave/overview", method = RequestMethod.GET)
    public String viewUsersLeaves(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

//        if (user.getMyRole().equalsIgnoreCase("CHAIRMAN")){
//            model.addAttribute(leaveService.findAllLeavesForChairman());
//        }else if (user.getMyRole().equalsIgnoreCase("CO-ORDINATOR")){
//            model.addAttribute(leaveService.findAllLeavesForCoordinate());
//        }else if(user.getMyRole().equalsIgnoreCase("DIN")){
//            model.addAttribute(leaveService.findAllLeavesForDin());
//        }else {
//            model.addAttribute(leaveService.findAllLeaves());
//        }

        if(user.getMyRole().equalsIgnoreCase("ADMIN"))
            model.addAttribute(leaveService.findAllLeaves());
        model.addAttribute(leaveService.findAllLeavesForRole());

        return "leave_overview";
    }

    @RequestMapping(value = "/user/my-leaves", method = RequestMethod.GET)
    public String viewMyLeavesStatusAndHistory(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        List<Message> myMessageList = messageService.findMyALLMessage();
        Message myLastMessage;
//        if(myMessageList.size() == 0){
//            myLastMessage = null;
//            System.out.println("Null");
//        }
//        else {
//            myLastMessage = myMessageList.get(myMessageList.size()-1);
//            System.out.println("Not Null");
//        }
//        model.addAttribute("messageList", myLastMessage);

        model.addAttribute("myLeaveStatus", "Something");

//        if(user.getMyRole().equalsIgnoreCase("ADMIN"))
//            model.addAttribute(leaveService.findAllLeaves());
        model.addAttribute("leavesList", leaveService.findMyAllLeaves());

        return "my_leaves";
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////// Update Leave /////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @RequestMapping(value = "/admin/leave-status-update", method = RequestMethod.GET)
    public String updateUsersLeaveStatus(
            @RequestParam String leaveId,
            @RequestParam String selectStatus,
            @RequestParam String message,
            Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        String leaveActionBy = user.getMyRole();
        String modifyTo;

//        if(leaveActionBy.equalsIgnoreCase("FACULTY")) modifyTo = "CO-ORDINATOR";
//        else if(leaveActionBy.equalsIgnoreCase("CO-ORDINATOR")) modifyTo = "CHAIRMAN";
//        else if(leaveActionBy.equalsIgnoreCase("CHAIRMAN")) modifyTo = "DIN";
//        else if(leaveActionBy.equalsIgnoreCase("DIN")) modifyTo = "ADMIN";
//        else modifyTo = "ADMIN";

        LeaveConfig leaveConfig = new LeaveConfig();
        modifyTo = leaveConfig.returnNextRole(leaveActionBy);

        if(!selectStatus.equalsIgnoreCase("Granted") || user.getMyRole().equalsIgnoreCase("ADMIN")) modifyTo = "Modified";
        System.out.println("This is leave id : " + leaveId);

        if(selectStatus.equalsIgnoreCase("Checking")){
            Message m = new Message(leaveId, message, "UnCheck");
            System.out.println("This is message : " + m);
            messageService.saveMessage(m);
            leaveService.updateLeaveStatus(leaveId, selectStatus, leaveActionBy, user.getMyRole());
        }
        else{
            leaveService.updateLeaveStatus(leaveId, selectStatus, leaveActionBy, modifyTo);
        }

        if(user.getMyRole().equalsIgnoreCase("ADMIN"))
            model.addAttribute(leaveService.findAllLeaves());
        model.addAttribute(leaveService.findAllLeavesForRole());

//
//        if (user.getMyRole().equalsIgnoreCase("CHAIRMAN")){
//            model.addAttribute(leaveService.findAllLeavesForChairman());
//        }else if (user.getMyRole().equalsIgnoreCase("CO-ORDINATOR")){
//            model.addAttribute(leaveService.findAllLeavesForCoordinate());
//        }else if(user.getMyRole().equalsIgnoreCase("DIN")){
//            model.addAttribute(leaveService.findAllLeavesForDin());
//        }else if(user.getMyRole().equalsIgnoreCase("FACULTY")){
//            model.addAttribute(leaveService.findAllLeavesForFaculty());
//        }else {
//            model.addAttribute(leaveService.findAllLeaves());
//        }


        return "leave_overview";
    }

    public void leaveListCase(){

    }

}
