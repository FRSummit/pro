package com.frsummit.hr2.controller;

import com.frsummit.hr2.configuration.LeaveConfig;
import com.frsummit.hr2.jpa_repository.PayrollRepository;
import com.frsummit.hr2.jpa_repository.RoleRepository;
import com.frsummit.hr2.model.*;
import com.frsummit.hr2.service.ChainRoleService;
import com.frsummit.hr2.service.LeaveService;
import com.frsummit.hr2.service.RoleService;
import com.frsummit.hr2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Set;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private LeaveService leaveService;

    @Autowired
    private ChainRoleService chainRoleService;



    @RequestMapping(value="/home", method = RequestMethod.GET)
    public String home(){
//        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        Role r = roleRepository.findByRole(auth.getName());
        //String ur = roleRepository.findByRole(auth.getName()).getRole();
//        modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
//        modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
//        modelAndView.setViewName("home");
        System.out.println("Current user name :" + user.getName());
        System.out.println("Current user id : " + user.getId());
        System.out.println("Role : " + user.getMyRole());
//        System.out.println("Current User Role : " + user.getRoles().toString());
//        Set<Role> rr = user.getRoles();
//        System.out.println(rr);
//        //System.out.println(ur);
//        System.out.println("This test : " + auth.getPrincipal());
//        System.out.println("That test : " + auth);
//
//        String ss = auth.getAuthorities().toString();
//
//        if(ss == "[ADMIN]") System.out.println("Correct");
//        else System.out.println("FAIL");
//        System.out.println(ss);
//        System.out.println("Role" + roleService.findAllUsers(ss));
//        String spl = ss;
//        String[] parts = ss.split("\[");
//        System.out.println(parts[0]);
        //System.out.println("My Role : " + leaveService.findSelectedLeaves(user.getId()));

        LeaveConfig leaveConfig = new LeaveConfig();
        leaveConfig.testSplit();
        leaveConfig.setModifyRole(user.getMyRole());
        String ss = leaveConfig.returnNextRole(user.getMyRole());
        System.out.println("Return the next Role : " + ss);

        System.out.println(userService.findAllUsers().size());

        System.out.println("Return the next Role for leave apply : " + leaveConfig.returnNextRoleForLeaveApply(user.getMyRole()));

        System.out.println("Final Role for Modification : " + leaveConfig.getLastModifierRole(user.getMyRole()));

        List<Leaves> l = leaveService.findMyAllLeaves();
        System.out.println(l);
//        System.out.println(l.get(l.size()-l.size()));
//        System.out.println(l.get(l.size()-1));

        //System.out.println("My map : ");
        //leaveConfig.testMap();

        System.out.println("ChainRole ===================================");
        List<ChainRole> chList = chainRoleService.findChainByRole(user.getMyRole());
        ChainRole ch = chList.get(0);
        System.out.println(ch.getChainId());
        System.out.println(ch.getRoleName());
        System.out.println(ch.getRoleChain());

        leaveConfig.testMapForRole(ch.getRoleName(), ch.getRoleChain());

        return "home";
    }



//    @RequestMapping(value="/home", method = RequestMethod.GET)
//    public ModelAndView home(){
//        ModelAndView modelAndView = new ModelAndView();
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        User user = userService.findUserByEmail(auth.getName());
//        modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
//        modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
//        modelAndView.setViewName("home");
//        return modelAndView;
//    }







}
