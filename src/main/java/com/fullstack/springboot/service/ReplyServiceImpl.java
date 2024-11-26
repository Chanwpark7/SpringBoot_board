package com.fullstack.springboot.service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fullstack.springboot.dto.ReplyDTO;
import com.fullstack.springboot.entity.Board;
import com.fullstack.springboot.entity.Reply;
import com.fullstack.springboot.repository.ReplyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {

	//레포지토리 주입
	private final ReplyRepository replyRepository;
	
	@Override
	public Long register(ReplyDTO replyDTO) {
		Reply reply = dtoToEntity(replyDTO);
		
		replyRepository.save(reply);
		
		return reply.getRno();
	}

	@Override
	public List<ReplyDTO> getList(Board board) {

		List<Reply> result = replyRepository.getListRepliesByBoardOrderByRno(board);
		
		return result.stream().map(reply -> entityToDTO(reply)).collect(Collectors.toList());
	}

	@Override
	public void modify(ReplyDTO replyDTO) {
		Reply reply = dtoToEntity(replyDTO);
		
		replyRepository.save(reply);
	}

	@Override
	public void remove(Long rno) {
		
		replyRepository.deleteById(rno);
	}

}
