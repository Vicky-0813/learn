package edu.hubu.learn.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.hubu.learn.entity.User;
import edu.hubu.learn.service.UserService;
import edu.hubu.learn.entity.Hospital;
import edu.hubu.learn.service.HospitalService;

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private UserService userService;
    @Autowired
    private HospitalService hospitalService;

    @RequestMapping("/")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        return mav;
    }

    @RequestMapping("/user")
    public ModelAndView user() {
        ModelAndView mav = new ModelAndView();
        User user = userService.getUser(1l);
        mav.addObject("user", user);
        mav.setViewName("user");
        return mav;
    }
    
    @RequestMapping("/hospital")
    public ModelAndView hospital() {
        ModelAndView mav = new ModelAndView();
        Hospital hospital = hospitalService.getHospital(1l);
        mav.addObject("hospital", hospital);
        mav.setViewName("user");
        return mav;
    }
       
}

