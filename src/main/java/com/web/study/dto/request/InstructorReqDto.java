package com.web.study.dto.request;

import java.time.LocalDate;

import com.web.study.domain.entity.Instructor;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class InstructorReqDto {

	private int itmId;
	private String itmName;
	private LocalDate itmBirth;
	
	public Instructor toEntity() {
		return Instructor.builder()
				.itm_id(itmId)
				.itm_name(itmName)
				.itm_birth(itmBirth)
				.build();
	}			
	
}
