package com.mysite.minipage.guestbook;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.aspectj.weaver.patterns.TypePatternQuestions.Question;
import org.springframework.stereotype.Service;

import com.mysite.minipage.DataNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class GuestbookService {
	//Repository와 Controller 사이에 들어오는 Service
	
	//Repository(DAO)와 연결시키기
	private final GuestbookRepository guestbookRepository;
	
	public List<Guestbook> getList(){
		return this.guestbookRepository.findAll();
	}
	
	//방명록 등록 저장하기
	public void create(Guestbook guestbook, String guestContent) {
		Guestbook guestbooks = new Guestbook();
		
		guestbooks.setGuestDate(LocalDate.now());
		guestbooks.setGuestContent(guestContent);
		
		this.guestbookRepository.save(guestbooks);	//저장소에 save
	}
	

	
	public Guestbook getGuestbook(Integer guestId) {
		Optional<Guestbook> guestbook = this.guestbookRepository.findById(guestId);
		if(guestbook.isPresent()) {
			return guestbook.get();
		}
		else {
			throw new DataNotFoundException("guestbook not found");
		}
		
	}

	
}
