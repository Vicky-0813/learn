package edu.hubu.learn.service;

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
}