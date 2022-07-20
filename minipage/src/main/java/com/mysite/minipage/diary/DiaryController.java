package com.mysite.minipage.diary;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@RequestMapping("/diary")
@RequiredArgsConstructor
@Controller
public class DiaryController {
	
	private final DiaryService diaryService;
	
	@RequestMapping("/list")
	public String list(Model model) {
		List<Diary> diaryList = this.diaryService.getList();
		
		model.addAttribute("diaryList", diaryList);
		
		return "diary";
	}

}
