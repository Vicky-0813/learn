package edu.hubu.learn.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.hubu.learn.entity.Hospital;

public interface HospitalDao extends JpaRepository<Hospital, Long> {

}