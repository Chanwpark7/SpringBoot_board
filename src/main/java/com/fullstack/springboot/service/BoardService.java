package com.fullstack.springboot.service;

import org.springframework.data.domain.PageRequest;

import com.fullstack.springboot.dto.BoardDTO;
import com.fullstack.springboot.dto.PageRequestDTO;
import com.fullstack.springboot.dto.PageResultDTO;
import com.fullstack.springboot.entity.Board;
import com.fullstack.springboot.entity.Member;

public interface BoardService {

	//글 등록 명세 정의
	Long register(BoardDTO dto);
	
	//리스트 명세 정의
	PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO);
	
	//글 상세에서 필요한 기능 선언
	BoardDTO getRead(Long bno);
	
	//글삭제 기능 선언. 댓글과 같이 삭제시킴.
	void removeWithReplies(Long bno);

	//수정 기능 선언
	void modify(BoardDTO boardDTO);
	
	//default 메소드로 매퍼 정의, dto -> entity
	default Board dtoToEntity(BoardDTO dto) {
		Member member = Member.builder()
				.email(dto.getWriterEmail()).build();
		
		Board board = Board.builder()
				.bno(dto.getBno())
				.title(dto.getTitle())
				.content(dto.getContent())
				.member(member)
				.build();
		return board;
	}
	
	default BoardDTO entityToDTO(Board board, Member member, Long replyCount) {
		BoardDTO boardDTO = BoardDTO.builder()
				.bno(board.getBno())
				.title(board.getTitle())
				.content(board.getContent())
				.regdate(board.getRegDate())
				.moddate(board.getModDate())
				.writerEmail(member.getEmail())
				.writerName(member.getName())
				.replyCount(replyCount.intValue())
				.build();
		
		return boardDTO;
	}
}
