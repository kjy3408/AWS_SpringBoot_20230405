package com.web.study.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.study.dto.DataResponseDto;
import com.web.study.dto.ResponseDto;
import com.web.study.dto.request.BasicTestDto2;

@RestController
public class BasicRestController2 {

	/* 1. get요청
	 * - param 1개
	 * - param 여러개 -> Dto만들어서 1개, Dto없이 1개
	 * 
	 * 
	 * 2. post오청
	 * - param 여러개 -> formData 1개, JsonData 1개
	 * */
	
	@GetMapping("/a") //1개
	public ResponseEntity<? extends ResponseDto> a(String name) {
//		String n = "이름: " + name;
		
		return ResponseEntity.ok().body(DataResponseDto.of(name));
	}
	
	@GetMapping("/b") //여러개 Dto없이
	public ResponseEntity<? extends ResponseDto> b(String username, String password, String address){
		String nn = username + "," +  password + "," + address;
		
		return ResponseEntity.ok().body(DataResponseDto.of(nn));
	}
	
	@GetMapping("/c") //여러개 Dto
	public ResponseEntity<? extends ResponseDto> c(BasicTestDto2 basicTestDto2){
		//알아서 키값 찾아감
		return ResponseEntity.ok().body(DataResponseDto.of(basicTestDto2));
	}
	
	
	@PostMapping("/p")
	public ResponseEntity<? extends ResponseDto> d (BasicTestDto2 basicTestDto2) {
		
		return ResponseEntity.created(null).body(DataResponseDto.of(basicTestDto2));				
	}
	
	
//	@PostMapping("/r") //raw로 보낼때 데이터가 있다면 @RequestBody 꼭 붙여야함
//	public ResponseEntity<? extends ResponseDto> d (@RequestBody BasicTestDto2 basicTestDto2) {
//		
//		
//		return ResponseEntity.created(null).body(DataResponseDto.of(basicTestDto2));				
//	}
	
	
	
	
	
	
}
