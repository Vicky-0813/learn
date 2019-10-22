package edu.hubu.learn.web;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import edu.hubu.learn.entity.Hospital;
import edu.hubu.learn.service.HospitalService;
import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
@RequestMapping("/hospital")
public class HospitalController {

    
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
        hospital.setAvatar("");
        hospitalService.addHospital(hospital);
        ModelAndView mav = new ModelAndView("redirect:/hospital/list");
        return mav;
    }
    @RequestMapping("/do_modify")
     public ModelAndView doModifyHospital(Hospital hospital) {
         hospital.setAvatar("");
         hospitalService.addHospital(hospital);
         ModelAndView mav = new ModelAndView("redirect:/hospital/list");
         return mav;
     }
      @RequestMapping("/search")
    public ModelAndView searchHospital() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("hospital_search");
        return mav;
    }

    @RequestMapping("/do_search")
    public ModelAndView doSearchHospital(HttpServletRequest httpRequest) {
        ModelAndView mav = new ModelAndView();
        String keyword = httpRequest.getParameter("keyword");
        List<Hospital> hospitals = hospitalService.searchHospitals(keyword);
        mav.addObject("hospitals", hospitals);
        mav.setViewName("hospitals");
        return mav;
    }
    @RequestMapping("/add_avatar/{id}")
    public ModelAndView addHospitalAvatar(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("hospital", hospitalService.getHospital(id));
        mav.setViewName("hospital_add_avatar");
        return mav;
    }

    @RequestMapping("/do_add_avatar/{id}")
    public ModelAndView doAddHospitalAvatar(@RequestParam("avatar") MultipartFile file, @PathVariable Long id) {
        try {
            String fileName = file.getOriginalFilename();
            String filePath = ResourceUtils.getURL("classpath:").getPath() + "../../../resources/main/static/";
            File dest = new File(filePath + fileName);
            log.info(dest.getAbsolutePath());
            file.transferTo(dest);
            Hospital hospital = hospitalService.getHospital(id);
            hospital.setAvatar(fileName);
            hospitalService.modifyHospital(hospital);
        } catch (Exception e) {
            log.error("upload avatar error", e.getMessage());
        }
        return new ModelAndView("redirect:/hospital/list");
    }
}