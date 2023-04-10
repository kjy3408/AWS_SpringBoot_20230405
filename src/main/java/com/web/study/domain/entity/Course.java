package com.web.study.domain.entity;

import java.time.LocalDate;

import org.apache.tomcat.jni.Local;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
@Builder
@Getter
@ToString
public class Course {

	private int id;
	private int lecture_id;
	private int student_id;
	private LocalDate registe_date;

	private Lecture lecture;
	private Student student;
}
