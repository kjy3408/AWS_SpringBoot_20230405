package com.web.study.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class TimerAop {

//	private final Logger logger = LogManager.getLogger(TimerAop.class);
	
	
	// 접근지정자 public은 생략이 가능
	@Pointcut("execution(* com.web.study..*.*(..))")
	private void pointCut() {}
	
	@Pointcut("@annotation(com.web.study.aop.annotation.TimerAspect)")
	private void annotationPointCut() {}
	
	@Around("annotationPointCut()&&pointCut()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		
		
		//전처리
		StopWatch stopWatch = new StopWatch(); //메소드 실행시간 확인 
		stopWatch.start();	

		Object logic = joinPoint.proceed(); //proceed = 메소드 호출
//		System.out.println(logic);
		
		//후처리 
		stopWatch.stop();
		System.out.println("log");
		log.info("Time >>> {}.{}: {}초",
				joinPoint.getSignature().getDeclaringTypeName(), 
				joinPoint.getSignature().getName(), 
				stopWatch.getTotalTimeSeconds());
//		System.out.println(joinPoint.getSignature().getName()); //Class class 의 getSimpleName, getName과 같은 용도 / controller, service, respository의 메소드명이 다 같으면 알수없음..
//		System.out.println(joinPoint.getSignature().getDeclaringTypeName()); //그래서 클래스를 찾음
//		System.out.println("메소드 실행 시간: " + stopWatch.getTotalTimeSeconds() + "s");
		return logic;
	}
	
}
