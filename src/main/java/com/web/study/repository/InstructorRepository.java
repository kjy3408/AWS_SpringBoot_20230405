package com.web.study.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.web.study.domain.entity.Instructor;
import com.web.study.dto.response.InstructorRespDto;

@Mapper
public interface InstructorRepository {

	public int saveInstructor(Instructor Instructor);
	
	public List<Instructor> getInstructorAll();
	
	public Instructor findInstructorById(int id);
	
}

