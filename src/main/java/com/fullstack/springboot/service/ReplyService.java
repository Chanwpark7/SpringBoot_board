package com.fullstack.springboot.service;

import java.util.List;

import com.fullstack.springboot.dto.ReplyDTO;
import com.fullstack.springboot.entity.Board;
import com.fullstack.springboot.entity.Reply;

public interface ReplyService {

	//댓글 등록 메소드
	Long register(ReplyDTO replyDTO);
	
	//리스트 get(게시글의 글번호를 기준으로 하나 이상 리턴)
	List<ReplyDTO> getList(Board board);
	
	void modify(ReplyDTO replyDTO);
	
	void remove(Long rno);
	
	//DTO --> Entity 변환 메소드 정의
	default Reply dtoToEntity(ReplyDTO replyDTO) {
		Board board = Board.builder()
				.bno(replyDTO.getBno())
				.build();
		
		Reply reply = Reply.builder()
				.rno(replyDTO.getRno())
				.text(replyDTO.getText())
				.replyer(replyDTO.getReplyer())
				.board(board)
				.build();
		
		return reply;
	}
	
	//Entity --> DTO 변환 메소드 정의
	default ReplyDTO entityToDTO(Reply reply) {
		ReplyDTO replyDTO = ReplyDTO.builder()
				.rno(reply.getRno())
				.text(reply.getText())
				.replyer(reply.getReplyer())
				.regdate(reply.getRegDate())
				.moddate(reply.getModDate())
				.bno(reply.getBoard().getBno())
				.build();
		
		return replyDTO;
	}
}
