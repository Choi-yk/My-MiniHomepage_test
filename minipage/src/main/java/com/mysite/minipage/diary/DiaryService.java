package com.mysite.minipage.diary;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DiaryService {

	private final DiaryRepository diaryRepository;
	
	// 목록 조회
	public List<Diary> getList() {
		return this.diaryRepository.findAll();
	}
	
	// 글 등록
	public void create(String content) {
		Diary diary = new Diary();
		diary.setContent(content);
		diary.setWriteDate(LocalDateTime.now());
		
		this.diaryRepository.save(diary);
	}
	
}
