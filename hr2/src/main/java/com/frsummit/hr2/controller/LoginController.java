package com.frsummit.hr2.controller;

import com.frsummit.hr2.configuration.LeaveConfig;
import com.frsummit.hr2.jpa_repository.MessageRepository;
import com.frsummit.hr2.model.Message;
import com.frsummit.hr2.service.ChainRoleService;
import com.frsummit.hr2.service.MessageService;
import com.frsummit.hr2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;

    @RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");

        System.out.println("This is Login page and it is working");

        System.out.println("My Map :");
        LeaveConfig leaveConfig = new LeaveConfig();
        leaveConfig.testMap();

        System.out.println("Message : ===========");
        List<Message> m = messageService.loadALLMessage();
        System.out.println(m);
        Message mm = m.get(0);
        System.out.println(mm.getMessageId());
        System.out.println(mm.getMessageUserId());
        System.out.println(mm.getLeaveId());
        System.out.println(mm.getMessage());
        System.out.println(mm.getMessageCheck());

        return modelAndView;
    }




}
