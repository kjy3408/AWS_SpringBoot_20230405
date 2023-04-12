package com.web.study.dto.request.instructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class SearchInstructorReqDto {

	@Min(value = 1)
	@Max(value = 4)
	private int id;
}
