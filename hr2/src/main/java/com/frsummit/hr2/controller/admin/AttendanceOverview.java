package com.frsummit.hr2.controller.admin;

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

@Controller
public class AttendanceOverview {

    @Autowired
    private UserService userService;

    @Autowired
    private AttendanceService attendanceService;

    //////////////////////Admin Atendance/////////////////////////////////////////////


    @RequestMapping(value = "/admin/attendance", method = RequestMethod.GET)
    public String getAttendanceForm(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        model.addAttribute(attendanceService.findAllAttendance());
        System.out.println(user);

        return "attendance_overview";
    }


}
