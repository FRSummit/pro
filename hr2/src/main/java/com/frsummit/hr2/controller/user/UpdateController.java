package com.frsummit.hr2.controller.user;

import com.frsummit.hr2.jpa_repository.UserRepository;
import com.frsummit.hr2.model.User;
import com.frsummit.hr2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class UpdateController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    ///////////////////////User Update////////////////////////////////////////////////


//    @RequestMapping(value = "/user/update", method = RequestMethod.GET)
//    public String getUserUpdateForm(Model model) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        User user = userService.findUserByEmail(auth.getName());
//        System.out.println(user);
//
//        return "user_edit";
////        return "user_update";
//    }



    @RequestMapping(value="/user/update-form", method = RequestMethod.GET)
    public ModelAndView getUserUpdateForm(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("user_edit");
        return modelAndView;
    }





//    @RequestMapping(value = "/user/user_update", method = RequestMethod.POST)
//    public String updateUser(
//            @RequestParam int id,
//            @RequestParam String name,
//            @RequestParam String lastName) {
//        User user = new User(id, name, lastName);
//        System.out.println(user);
//        //userService.saveUser(user);
//        return "home";
//    }

//    @RequestMapping(value = "/user/update", method = RequestMethod.POST)
//    public String updateUser(
//            @RequestParam int id,
//            @RequestParam String name,
//            @RequestParam String lastName,
//            @RequestBody User users) {
//        User user = new User(id, name, lastName);
//        System.out.println();
//        //userService.saveUser(user);
//        //user.setName(users.getName());
//
//        return "home";
//    }
//
//



// @RequestMapping(value = "/user/update", method = RequestMethod.POST)
//    public ModelAndView updateUser(@Valid User user, BindingResult bindingResult) {
//     ModelAndView modelAndView = new ModelAndView();
//     User userExists = userService.findUserByEmail(user.getEmail());
//     if (userExists != null) {
//         bindingResult
//                 .rejectValue("email", "error.user",
//                         "There is already a user registered with the email provided");
//     }
//     if (bindingResult.hasErrors()) {
//         modelAndView.setViewName("user_edit");
//     } else {
//         userService.update(user);
//         modelAndView.addObject("successMessage", "User has been registered successfully");
//         modelAndView.addObject("user", new User());
//         modelAndView.setViewName("home");
//
//     }
//     return modelAndView;
//    }

 @RequestMapping(value = "/user/update", method = RequestMethod.POST)
    public String updateUser(
            @RequestParam String name,
            @RequestParam String lastName) {
//     ModelAndView modelAndView = new ModelAndView();
//     User userExists = userService.findUserByEmail(user.getEmail());
//     User user2 = new User(id, name);


     Authentication auth = SecurityContextHolder.getContext().getAuthentication();
     User user = userService.findUserByEmail(auth.getName());

     String n, ln;
     n = user.getName();
     ln = user.getLastName();

     System.out.println(n + " " + ln);

     if(name == "") name = n;
     if(lastName == "") lastName = ln;

     System.out.println(name + " " + lastName);

     User u = new User(name, lastName);
     userService.updateName(name, lastName);

     return "home";
    }

}
