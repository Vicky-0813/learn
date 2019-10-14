package edu.hubu.learn.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.hubu.learn.dao.HospitalDao;
import edu.hubu.learn.entity.Hospital;

@Service
public class HospitalService {

    @Autowired
    private HospitalDao hospitalDao;

    public Hospital getHospital(Long id) {
        return hospitalDao.findById(id).get();

    }
    public List<Hospital> getHospitals(){
        return hospitalDao.findAll();
    }
    public Hospital addHospital(Hospital hospital) {
        return hospitalDao.save(hospital);
    }
    public void deleteHospital(Long id){
        hospitalDao.deleteById(id);
    }
    public void modifyHospital(Hospital hospital) {
        hospitalDao.save(hospital);
    }
}