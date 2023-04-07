package com.web.study.repository;

import org.apache.ibatis.annotations.Mapper;
import com.web.study.domain.entitiy.Lecture;

@Mapper
public interface LectureRepository {

	public int registe(Lecture lecture);
}
