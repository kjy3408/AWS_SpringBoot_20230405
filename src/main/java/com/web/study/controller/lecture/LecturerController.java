package com.web.study.controller.lecture;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.web.study.dto.DataResponseDto;
import com.web.study.dto.ErrorResponseDto;
import com.web.study.dto.ResponseDto;
import com.web.study.dto.request.lecturer.LecturerReqDto;
import com.web.study.service.LecturerService;

import lombok.RequiredArgsConstructor;
import lombok.experimental.PackagePrivate;

@RestController
@RequiredArgsConstructor
public class LecturerController {

	private final LecturerService lecturerService;
	
	@PostMapping("/lecturer")
	public ResponseEntity<? extends ResponseDto> registeLecturer(@RequestBody LecturerReqDto lecturerReqDto ) {
		
//		System.out.println(lecturerReqDto);		
		lecturerService.registeLecturer(lecturerReqDto);
		return ResponseEntity.ok().body(ResponseDto.ofDefault());
	}
	
	
	@GetMapping("/lecturers")
	public ResponseEntity<? extends ResponseDto> getLecturers() {
		
		return ResponseEntity.ok().body(DataResponseDto.of(lecturerService.getLecturerAll()));
	}

	
	@GetMapping("/lecturer/{id}")
	public ResponseEntity<? extends ResponseDto> getLecturerById(@PathVariable int id) {
		
		
		return ResponseEntity.ok().body(DataResponseDto.of(lecturerService.findLecturerById(id)));
//		return ResponseEntity.ok().body(DataResponseDto.of(lecturerService.getLecturerAll().get(id-1))); list에서 index조회를 하게되면 정확하지가 않음
	}

}
