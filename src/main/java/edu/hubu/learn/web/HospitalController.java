package edu.hubu.learn.web;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.hubu.learn.entity.User;
import edu.hubu.learn.service.UserService;
import edu.hubu.learn.entity.Hospital;
import edu.hubu.learn.service.HospitalService;

@Controller
@RequestMapping("/hospital")
public class HospitalController {

    @Autowired
    private UserService userService;
    @Autowired
    private HospitalService hospitalService;

   
    
    @RequestMapping("/{id}")
    public ModelAndView hospital(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView();
        Hospital hospital = hospitalService.getHospital(id);
        mav.addObject("hospital", hospital);
        mav.setViewName("user");
        return mav;
    }
    @RequestMapping("/delete/{id}")   
    public ModelAndView delete(@PathVariable Long id){
        hospitalService.deleteHospital(id);
        ModelAndView mav = new ModelAndView("redirect:/hospital/list"); 
        return mav;
    }

    @RequestMapping("/modify/{id}")
     public ModelAndView modifyHospital(@PathVariable Long id) {
         ModelAndView mav = new ModelAndView();
         mav.addObject("hospital", hospitalService.getHospital(id));
         mav.setViewName("hospital_modify");
         return mav;
     }
    @RequestMapping("/list")   
    public ModelAndView hospitals(){
        ModelAndView mav = new ModelAndView();
        List<Hospital> hospitals = hospitalService.getHospitals(); 
        mav.addObject("hospitals",hospitals);
        mav.setViewName("hospitals");
        return mav;
    }

    @RequestMapping("/add")
    public ModelAndView addHospital() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("hospital_add");
        return mav;
    }

    @RequestMapping("/do_add")
    public ModelAndView doAddHospital(Hospital hospital) {
        hospitalService.addHospital(hospital);
        ModelAndView mav = new ModelAndView("redirect:/hospital/list");
        return mav;
    }
    @RequestMapping("/do_modify")
     public ModelAndView doModifyHospital(Hospital hospital) {
         hospitalService.addHospital(hospital);
         ModelAndView mav = new ModelAndView("redirect:/hospital/list");
         return mav;
     }
}