package com.frsummit.hr2.controller.admin;

import com.frsummit.hr2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserListController {

    @Autowired
    private UserService userService;

    @RequestMapping("/admin/users")
    public String getUserList(Model model) {
        model.addAttribute(userService.findAllUsers());

        return "users";
    }

    @RequestMapping(value = "/admin/list-user-edit", method = RequestMethod.GET)
    public String getUserListEditAction(@RequestParam String edit, @RequestParam String remove) {
        System.out.println("Edit / Delete Clicked" + edit + " " + remove);

        return "users";
    }
}
