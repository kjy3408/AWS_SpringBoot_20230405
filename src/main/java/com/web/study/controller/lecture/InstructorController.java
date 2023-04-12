package com.web.study.controller.lecture;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.web.study.dto.DataResponseDto;
import com.web.study.dto.ResponseDto;
import com.web.study.dto.request.InstructorReqDto;
import com.web.study.service.InstructorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class InstructorController {

	private final InstructorService InstructorService;
	
	@PostMapping("/Instructor")
	public ResponseEntity<? extends ResponseDto> registeInstructor(@RequestBody InstructorReqDto InstructorReqDto ) {
		
//		System.out.println(InstructorReqDto);		
		InstructorService.registeInstructor(InstructorReqDto);
		return ResponseEntity.ok().body(ResponseDto.ofDefault());
	}
	
	
	@GetMapping("/Instructors")
	public ResponseEntity<? extends ResponseDto> getInstructors() {
		
		return ResponseEntity.ok().body(DataResponseDto.of(InstructorService.getInstructorAll()));
	}

	
	@GetMapping("/Instructor/{id}")
	public ResponseEntity<? extends ResponseDto> getInstructorById(@PathVariable @Valid int id) {
		
		System.out.println("dddddddd");
		return ResponseEntity.ok().body(DataResponseDto.of(InstructorService.findInstructorById(id)));
//		return ResponseEntity.ok().body(DataResponseDto.of(InstructorService.getInstructorAll().get(id-1))); list에서 index조회를 하게되면 정확하지가 않음
	}

}
