package com.web.study.domain.entitiy;

import java.time.LocalDate;

import org.apache.tomcat.jni.Local;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
@Builder
@Getter
@ToString
public class LectureRegist {

	private int id;
	private int lecture_id;
	private int student_id;
	private LocalDate registe_date;
}
