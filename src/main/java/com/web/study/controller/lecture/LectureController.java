package com.web.study.controller.lecture;

import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.web.study.dto.DataResponseDto;
import com.web.study.dto.ResponseDto;
import com.web.study.dto.request.lecture.LectureReqDto;
import com.web.study.service.LectureService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LectureController {

	private final LectureService lectureService;
	
	// Create - POST
	@PostMapping("/lecture")
	public ResponseEntity<? extends ResponseDto> register(@RequestBody LectureReqDto lectureReqDto) {
		
		lectureService.registeLecture(lectureReqDto);
	
		return ResponseEntity.ok().body(ResponseDto.ofDefault());
	}
	// Read - GET
	@GetMapping("/lectures")
	public ResponseEntity<? extends ResponseDto> getLectures() {
		
		return ResponseEntity.ok().body(DataResponseDto.of(lectureService.findLectureAll()));
	}
	
	@GetMapping("/search/lecture")
	public ResponseEntity<? extends ResponseDto> searchLecture(@Nullable int type, String searchValue) {
		
		return ResponseEntity.ok().body(DataResponseDto.of(lectureService.searchLecture(type, searchValue)));
	}
	
	
	
	
	
	
	
	
	
	
	// Update - PUT
	public ResponseEntity<? extends ResponseDto> modify() {
	
		return ResponseEntity.ok().body(ResponseDto.ofDefault());
	}
	// Delete - DELETE
	public ResponseEntity<? extends ResponseDto> remove() {
	
		return ResponseEntity.ok().body(ResponseDto.ofDefault());
	}


}

