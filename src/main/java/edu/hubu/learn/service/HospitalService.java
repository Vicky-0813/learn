package edu.hubu.learn.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
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
        return hospitalDao.findAll(new Sort(Direction.DESC, "id"));
    }
    public List<Hospital> searchHospitals(String keyword) {
        Hospital hospital = new Hospital();
        hospital.setName(keyword);
        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("name", match->match.contains());
        Example<Hospital> example = Example.of(hospital, matcher);
        Sort sort = new Sort(Direction.DESC, "id");
        return hospitalDao.findAll(example, sort);
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