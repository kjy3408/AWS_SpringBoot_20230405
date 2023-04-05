package com.web.study.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.study.dto.DataResponseDto;
import com.web.study.dto.ErrorResponseDto;
import com.web.study.dto.ResponseDto;

@RestController //-> 데이터를 응답하는 용도(Controller일때 항상 @ResponseBody를 붙인상태와 같음.)
//@Controller // -> view를 응답하는 용도(view일땐 무조건 @Controller 써야함.) -> return은 html파일명이 됨.
public class BasicController {

//	@RequestMapping(value = "/view/test", method = RequestMethod.GET) -> getMapping 옛날버전
	
	
//	@ResponseBody //@Controller 일때 데이터를 날리는 용도
	@GetMapping("/view/test")
	public ResponseEntity<? extends ResponseDto> view() { //return(응답)은 String일때만 text로 , 그 외엔 JSON임
		
		List<String> strList = new ArrayList<>();
		strList.add("a");
		strList.add("b");
		strList.add("c");
		strList.add("d");
		strList.add("e");
		
		if(strList.contains("e")) {
			try {
				throw new RuntimeException("오류남");
			} catch (Exception e) {
				return ResponseEntity.internalServerError().body(ErrorResponseDto.of(HttpStatus.INTERNAL_SERVER_ERROR, e));
			}
		}
		
//		return ResponseEntity.ok(DataResponseDto.of(strList));
		return ResponseEntity.ok().body(DataResponseDto.of(strList));
	}
	
}
