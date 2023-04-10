package com.web.study.controller.lecture;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.study.dto.DataResponseDto;
import com.web.study.dto.ErrorResponseDto;
import com.web.study.dto.ResponseDto;
import com.web.study.dto.request.student.StudentReqDto;
import com.web.study.service.StudentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class StudentController {

	private final StudentService studentService;
	
	@PostMapping("/student")		//post를 JSON으로 받고싶으면 @Requestbody 필수
	public ResponseEntity<? extends ResponseDto> registeStudent(@RequestBody StudentReqDto studentRequDto) {
		
//		System.out.println(studentRequDto);
		studentService.registeStudent(studentRequDto);
		
		return ResponseEntity.ok().body(ResponseDto.ofDefault());
	}
	
	
	@GetMapping("/students")
	public ResponseEntity<? extends ResponseDto> getStudents() {
		
		return ResponseEntity.ok().body(DataResponseDto.of(studentService.getStudentAll()));
	}
	
	@GetMapping("/student/{id}")
	public ResponseEntity<? extends ResponseDto> getStudentById(@PathVariable int id) {
			
		
		
		return ResponseEntity.ok().body(DataResponseDto.of(studentService.getStudentById(id)));
	}
}
