package com.web.study.domain.entity;

import java.time.LocalDate;

import com.web.study.dto.response.StudentRespDto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

//@AllArgsConstructor
@Builder
@Getter
@ToString
//@Alias("S1") repository(xml)을 parameterType="S1"으로 사용할 수 있다. yml파일에 경로를 잡아줬을 경우!
public class Student {

	private int id;
	private String name;
	private LocalDate birth_date;
	
	public StudentRespDto toDto() {
		return StudentRespDto.builder()
				.id(id)
				.name(name)
				.birthDate(birth_date)
				.build();
	}
}
