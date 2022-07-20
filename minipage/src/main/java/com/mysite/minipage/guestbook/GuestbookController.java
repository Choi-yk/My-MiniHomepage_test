package com.mysite.minipage.guestbook;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@RequestMapping("/guestbook")	//mapping주소 중 공통 부분이니까 클래스 자체 mapping주소로 만들어둠
@RequiredArgsConstructor	//생성자 주입을 위한.
@Controller
public class GuestbookController {
	
	//Service와 Controller 연결(Controller - Service - Repository)(Service - Repository부분은 Service.java에 있겠쥬?)
	private final GuestbookService guestbookService;
	private final GuestbookRepository guestbookRepository;
	
	//전체 글 조회하기
	@RequestMapping("/list")
	public String list(Model model) {
		List<Guestbook> guestbookList = this.guestbookRepository.findAll();
		model.addAttribute("guestbookList",guestbookList);
		return "guestbook_list";
	}
	
	//방명록 등록 버튼
	@PostMapping("/create/{guestId}")
	public String createGuestbook(Model model,@PathVariable("guestId") Integer guestId, @RequestParam String guestContent) {
		// model은 리턴값 만들기 위해
		// @PathVariable("guestId") Integer guestId는 Mapping에 있는 {guestId}를 그대로 받아오기 위해 설정한 것
		// @RequestParam String guestContent는 답변을 단 내용(guestContent)부분 가져오기
		
		Guestbook guestbook = this.guestbookService.getGuestbook(guestId);
		
		this.guestbookService.create(guestbook,guestContent);		
		
		return "guestbook_list";
	}
	
}
