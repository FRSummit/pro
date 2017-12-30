package com.frsummit.hr2.controller.admin;

import com.frsummit.hr2.model.User;
import com.frsummit.hr2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;



    @RequestMapping(value="/admin/registration", method = RequestMethod.GET)
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/registration", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid User user, @RequestParam(value = "rl") String rl, BindingResult bindingResult, Model model) {

        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
        } else {
            if(rl == "") rl = "USER";
            String userRole = rl.toUpperCase();
            userService.saveUser(user, userRole);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("users");
        }
        model.addAttribute(userService.findAllUsers());
        return modelAndView;
    }



    ///////Not Applied/////////////


    @RequestMapping(value = "/admin/users/create", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }
}
