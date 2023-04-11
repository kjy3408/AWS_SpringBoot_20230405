package com.web.study.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.web.study.domain.entity.Lecture;
import com.web.study.domain.entity.Instructor;

@Mapper
public interface LectureRepository {

	public int registe(Lecture lecture);
	public List<Instructor> findLectureAll();
	public List<Lecture> searchLecture(Map<String, Object> parameterMap);
}
