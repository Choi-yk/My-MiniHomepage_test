package com.mysite.minipage.diary;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	/* 글 등록 */
	@GetMapping("/create")
	public String diaryCreate(DiaryForm diaryForm) {
		return "diary_form";
	}
	
	@PostMapping("/create")
	public String diaryCreate(@Valid DiaryForm diaryForm, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "diary_form";
		}
		this.diaryService.create(diaryForm.getContent());
		
		return "redirect:/diary/list";
	}

}
