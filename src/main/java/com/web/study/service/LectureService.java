package com.web.study.service;

import java.util.List;

import com.web.study.domain.entity.Lecturer;
import com.web.study.dto.request.lecture.LectureReqDto;

public interface LectureService {

	public void registeLecture(LectureReqDto lectureReqDto);
}
