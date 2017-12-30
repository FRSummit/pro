package com.frsummit.hr2.controller.admin;

import com.frsummit.hr2.jpa_repository.PayrollRepository;
import com.frsummit.hr2.model.Payroll;
import com.frsummit.hr2.model.User;
import com.frsummit.hr2.service.PayrollService;
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

@Controller
public class PayrollController {

    @Autowired
    private UserService userService;

    @Autowired
    private PayrollService payrollService;






    /////////////Set Pay/////////////////////

    @RequestMapping(value = "/admin/set-payroll", method = RequestMethod.GET)
    public String getPayrollForm(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        System.out.println(user);

        return "set-payroll";
    }

    @RequestMapping(value = "/admin/set-payroll", method = RequestMethod.POST)
    //public ModelAndView createPayroll(@Valid Payroll p, BindingResult bindingResult) {
    public ModelAndView createPayroll(@RequestParam String userPayEmail,
                                      @RequestParam double basic,
//                                      @RequestParam double medical,
//                                      @RequestParam double hra,
//                                      @RequestParam double ta,
//                                      @RequestParam double da,
//                                      @RequestParam double incentive,
                                      Model model) {
        ModelAndView modelAndView = new ModelAndView();

//        Payroll payrollUser = payrollService.findPayrollUserByEmail(userPayEmail);
//        //if (payrollUser != null) //{
//            System.out.println("This is user" + payrollUser.getPayUserEmail());
//        }else{


        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(userPayEmail);

        double medical = basic * 0.25;
        double hra = basic * 0.10;
        double ta = basic * 0.10;
        double da = basic * 0.10;
        double incentive = basic * 0.10;

        if(user != null){
            System.out.println("Payroll Ok");
            Payroll p = new Payroll(userPayEmail, basic, medical, hra, ta, da, incentive);
            System.out.println(p);
            payrollService.savePayroll(p);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("pay", new Payroll());
            model.addAttribute(payrollService.findAllPayrolls());
            modelAndView.setViewName("payroll_overview");
            System.out.println("SAVE");

        }else{
            modelAndView.setViewName("set-payroll");
            System.out.println("Payroll Fail");
        }
        return modelAndView;
    }







    @RequestMapping(value = "/admin/payroll/overview", method = RequestMethod.GET)
    public String viewUsersPayrolls(Model model) {
        model.addAttribute(payrollService.findAllPayrolls());

        return "payroll_overview";
    }

}
