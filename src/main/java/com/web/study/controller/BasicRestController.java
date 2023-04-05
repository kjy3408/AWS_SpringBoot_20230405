package com.web.study.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.web.study.dto.DataResponseDto;
import com.web.study.dto.ResponseDto;
import com.web.study.dto.request.BasicTestDto;


@RestController
public class BasicRestController {

	
	//GET 요청의 param를 처리하는 방법
//	@GetMapping("/read")
//	public ResponseEntity<? extends ResponseDto> read(
//			@RequestParam("age") int userAge,
//			@RequestParam("name") String userName) {
//		
//		String userInfo = userName + "(" + userAge + ")";
//		return ResponseEntity.ok().body(DataResponseDto.of(userAge));
//	}
	
	
	@GetMapping("/read")
	public ResponseEntity<? extends ResponseDto> read(BasicTestDto basicTestDto) { //@RequestParam을 생략하려면 key값이 변수명이 되어 사용하면된다.
		
		String userInfo = basicTestDto.getName() + "(" + basicTestDto.getAge() + ")";
		return ResponseEntity.ok().body(DataResponseDto.of(userInfo));
	}
	
	@GetMapping("/read2/{id}") //주소에 key값이 들어감. 
	public ResponseEntity<? extends ResponseDto> read2(@PathVariable/*("id")*/ int id) { //@PathVariable을 생략하진 못하지만 변수명을 주소의 key와 맞춰서 사용가능.
		Map<Integer, String> userMap = new HashMap<>();
		
		userMap.put(1, "김준일");
		userMap.put(2, "김준이");
		userMap.put(3, "김준삼");
		userMap.put(4, "김준사");
		userMap.put(5, "김준오");
		
		
		return ResponseEntity.ok().body(DataResponseDto.of(userMap.get(asdfasdf)));		
	}

	
	// Post요청의 데이터 처리
	@PostMapping("/create") //raw로 보낼때 데이터가 있다면 @RequestBody 꼭 붙여야함
	public ResponseEntity<? extends ResponseDto> create(@RequestBody BasicTestDto basicTestDto) { //Json을 받을땐 @RequestBody를 작성해야 받을 수 있다.
		System.out.println("데이터 추가");
		
		return ResponseEntity.created(null).body(DataResponseDto.of(basicTestDto));
	}

}





































