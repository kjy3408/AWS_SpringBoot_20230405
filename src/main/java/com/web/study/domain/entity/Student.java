package com.web.study.domain.entity;

import java.time.LocalDate;

import com.web.study.dto.response.StudentRespDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

//@AllArgsCoInstructor
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
//@Alias("S1") repository(xml)을 parameterType="S1"으로 사용할 수 있다. yml파일에 경로를 잡아줬을 경우!
public class Student {

	private int sdm_id;
	private String sdm_name;
	private LocalDate sdm_birth;
	
	public StudentRespDto toDto() {
		return StudentRespDto.builder()
				.id(sdm_id)
				.name(sdm_name)
				.birthDate(sdm_birth)
				.build();
	}
}
