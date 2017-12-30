package com.frsummit.hr2.controller.user;

import com.frsummit.hr2.configuration.LeaveConfig;
import com.frsummit.hr2.jpa_repository.LeaveRepository;
import com.frsummit.hr2.model.Leaves;
import com.frsummit.hr2.model.User;
import com.frsummit.hr2.service.LeaveService;
import com.frsummit.hr2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.sql.Date;

@Controller
public class LeaveApplyController {

    @Autowired
    private UserService userService;

    @Autowired
    private LeaveService leaveService;

    @Autowired
    private LeaveRepository repository;


//////////////////////Leave Application/////////////////////////////////////////////


    @RequestMapping(value = "/user/apply-for-leave", method = RequestMethod.GET)
    public String getLeaveApplicationForm(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        model.addAttribute("titleLast", user.getLastName());
        model.addAttribute("title", user.getName());
        model.addAttribute("department", user.getDepartment());
        System.out.println(user);

        return "leave_application";
    }

    @RequestMapping(value = "/user/apply-for-leave", method = RequestMethod.POST)
    public String getApplyLeave(
            //@RequestParam(value = "leaveUserId") String leaveUserId,
            //@RequestParam(value = "name") String name,
            //@RequestParam(value = "department") String department,
            //@RequestParam(value = "applyTo") String applyTo,
            @RequestParam(value = "leaveType") String leaveType,
            @RequestParam(value = "leaveFrom") Date leaveFrom,
            @RequestParam(value = "leaveTo") Date leaveTo,
            @RequestParam(value = "leaveReason") String leaveReason){


        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        String applyTo;

        LeaveConfig leaveConfig = new LeaveConfig();
        applyTo = leaveConfig.returnNextRoleForLeaveApply(user.getMyRole());

        Leaves l = new Leaves(user.getId(), user.getName(), user.getDepartment(), applyTo, leaveType, leaveFrom, leaveTo, leaveReason, "Pending");
        leaveService.saveLeave(l);

//        if(user.getMyRole().equalsIgnoreCase("USER")){
//            applyTo="FACULTY";
//            Leaves l = new Leaves(user.getId(), user.getName(), user.getDepartment(), applyTo, leaveType, leaveFrom, leaveTo, leaveReason, "Pending");
//            leaveService.saveLeave(l);
//        }
//        else if(user.getMyRole().equalsIgnoreCase("FACULTY")){
//            applyTo="CO-ORDINATOR";
//            Leaves l = new Leaves(user.getId(), user.getName(), user.getDepartment(), applyTo, leaveType, leaveFrom, leaveTo, leaveReason, "Pending");
//            leaveService.saveLeave(l);
//        }
//        else if(user.getMyRole().equalsIgnoreCase("CO-ORDINATOR")){
//            applyTo="CHAIRMAN";
//            Leaves l = new Leaves(user.getId(), user.getName(), user.getDepartment(), applyTo, leaveType, leaveFrom, leaveTo, leaveReason, "Pending");
//            leaveService.saveLeave(l);
//        }
//        else if(user.getMyRole().equalsIgnoreCase("CHAIRMAN")){
//            applyTo="DIN";
//            Leaves l = new Leaves(user.getId(), user.getName(), user.getDepartment(), applyTo, leaveType, leaveFrom, leaveTo, leaveReason, "Pending");
//            leaveService.saveLeave(l);
//        }
//        else if(user.getMyRole().equalsIgnoreCase("DIN")){
//            applyTo="ADMIN";
//            Leaves l = new Leaves(user.getId(), user.getName(), user.getDepartment(), applyTo, leaveType, leaveFrom, leaveTo, leaveReason, "Pending");
//            leaveService.saveLeave(l);
//        }
//        else{
//            applyTo="ADMIN";
//            Leaves l = new Leaves(user.getId(), user.getName(), user.getDepartment(), applyTo, leaveType, leaveFrom, leaveTo, leaveReason, "Pending");
//            leaveService.saveLeave(l);
//        }

        //String leaveStatus = "Pending";

        //Leaves l = new Leaves(user.getId(), name, user.getDepartment(), applyTo, leaveType, leaveFrom, leaveTo, leaveReason, "Pending");
        //leaveService.saveLeave(l);
        //System.out.println(l);

        return "home";
    }


//
//    @RequestMapping(value = "/user/apply-leave", method = RequestMethod.POST)
//    public String getApplyForLeave(@Valid Leaves leaves) {
//
//        leaveService.saveLeave(leaves);
//
//        return "home";
//    }



    @RequestMapping(value = "/user/cancel-leave", method = RequestMethod.GET)
    public String getCancelLeave(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        System.out.println(user);

        return "home";
    }


}
