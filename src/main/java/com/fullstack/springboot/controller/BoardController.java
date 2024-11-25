package com.fullstack.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fullstack.springboot.dto.BoardDTO;
import com.fullstack.springboot.dto.PageRequestDTO;
import com.fullstack.springboot.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequestMapping("/board")
@Log4j2
@RequiredArgsConstructor
public class BoardController {

	private final BoardService boardService;
	
	//게시글 목록 요청 및 UI 에 모델 연결
	@GetMapping("/list")
	public void list(PageRequestDTO pageRequestDTO, Model model) {
		log.error("리스트 요청됨 " + pageRequestDTO);
		
		model.addAttribute("result", boardService.getList(pageRequestDTO));
	}
	
	//등록용 연결
	@GetMapping("/register")
	public void register() {
		log.error("등록 요청됨 ");
	}
	
	//등록 처리 후 list로 리다이렉트
	@PostMapping("/register")
	public String postMethodName(BoardDTO boardDTO, RedirectAttributes redirectAttributes) {
		
		log.error("등록처리 수행됨.");
		Long bno = boardService.register(boardDTO);
		log.error("신규 등록된 글번호 : " + bno);
		
		redirectAttributes.addFlashAttribute("msg","글 번호 "+bno+"가 등록되었습니다.");
		
		return "redirect:/board/list";
	}
	
	//게시물 조회 연결
	@GetMapping({"/read","/modify"})
	public void read(@ModelAttribute("pageRequestDTO") PageRequestDTO pageRequestDTO, @RequestParam("bno") Long bno, Model model) {
		log.error("상세 페이지 요청됨." + bno);
		BoardDTO boardDTO = boardService.getRead(bno);
		model.addAttribute("dto", boardDTO);
	}
	
	@PostMapping("/modify")
	public String modify(BoardDTO boardDTO, @ModelAttribute("pageRequestDTO") PageRequestDTO pageRequestDTO,
			RedirectAttributes redirectAttributes) {
		
		boardService.modify(boardDTO);
		
		redirectAttributes.addAttribute("page", pageRequestDTO.getPage());
		redirectAttributes.addFlashAttribute("msg", boardDTO.getBno() + "번 글이 수정되었습니다.");
		
		return "redirect:/board/list";
	}
	
	@GetMapping("/delete")
	public String getMethodName(BoardDTO boardDTO, @ModelAttribute("pageRequestDTO") PageRequestDTO pageRequestDTO,
			RedirectAttributes redirectAttributes) {
		
		boardService.removeWithReplies(boardDTO.getBno());
		
		redirectAttributes.addAttribute("page", pageRequestDTO.getPage());
		redirectAttributes.addFlashAttribute("msg", boardDTO.getBno() + "번 글이 삭제되었습니다.");
		
		return "redirect:/board/list";
	}
	
}
