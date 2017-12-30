package com.frsummit.hr2.controller.user;

import com.frsummit.hr2.jpa_repository.UserRepository;
import com.frsummit.hr2.model.User;
import com.frsummit.hr2.service.PayrollService;
import com.frsummit.hr2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MyPayrollController {

    @Autowired
    private UserService userService;

    @Autowired
    private PayrollService payrollService;

    @Autowired
    private UserRepository userRepository;



///////////////////////My Payment/////////////////////////////////////////////////




    @RequestMapping(value = "/user/my-payment", method = RequestMethod.GET)
    public String viewMyPayment(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        model.addAttribute(payrollService.findMyPayroll());

//
//        System.out.println(user);
//        userService.myMail();
//        Iterable<User> uList = userRepository.findAll();
//        System.out.println("------------------------------------------------------------------------");
//        for(User uu : uList)
//            System.out.println(uu.getId() + " " + uu.getName() + " " + uu.getLastName() + " " + uu.getEmail() + " " + uu.getActive()
//                    + " " + uu.getRoles() + " " + uu.getPassword());
//
//        //System.out.println(userService.findAllUsersId());
//
//        ////model.addAttribute(payrollService.findMyPayroll());
//        System.out.println("------------------------------------------------------------------------");

        return "my_payment";
    }


}
