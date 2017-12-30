package com.frsummit.hr2.controller.user;

import com.frsummit.hr2.model.Attendance;
import com.frsummit.hr2.model.User;
import com.frsummit.hr2.service.AttendanceService;
import com.frsummit.hr2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AttendSignInOutController {

    @Autowired
    private UserService userService;

    @Autowired
    private AttendanceService attendanceService;

//////////////////////Atend In Out Sign/////////////////////////////////////////////


    @RequestMapping(value = "/attendInOutSign", method = RequestMethod.GET)
    public String getAttendInOutPage() {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        User user = userService.findUserByEmail(auth.getName());
//        System.out.println(user);

        System.out.println("This is it");

        return "attend_in_out_sign";
    }

    @RequestMapping(value = "/attendance-sign", method = RequestMethod.POST)
    public String getAttendInOutForm(
            @RequestParam String email) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(email);
        System.out.println(user);

        if(user == null){
            System.out.println("You're not an user, GET OUT");
            return "attend_in_out_sign";
        }else{
            Attendance attendance = new Attendance(email);
            System.out.println(email);
            attendanceService.saveUserAttendanceByPunch(attendance);
            System.out.println("That is it");
            return "login";
        }
    }




}
