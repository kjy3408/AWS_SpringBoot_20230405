package com.web.study.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController  //(RestController)요청과 응답을 받는 controller가 됨.
public class HelloController {
	
	@GetMapping("/hello") //(GetMapping) GET요청을 날리면 메소드 실행 / return에다 응답!
	public Map<String, String> hello(String name) { 
		
		Map<String, String> testMap = new HashMap<>();
		testMap.put("name", name);
		testMap.put("age", "30");
		testMap.put("address", "부산 동래구 사직동");

		return testMap;
	}

}
