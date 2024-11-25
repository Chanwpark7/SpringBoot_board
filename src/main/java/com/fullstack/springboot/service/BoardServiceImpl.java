package com.fullstack.springboot.service;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fullstack.springboot.dto.BoardDTO;
import com.fullstack.springboot.dto.PageRequestDTO;
import com.fullstack.springboot.dto.PageResultDTO;
import com.fullstack.springboot.entity.Board;
import com.fullstack.springboot.entity.Member;
import com.fullstack.springboot.repository.BoardRepository;
import com.fullstack.springboot.repository.ReplyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

	private final BoardRepository boardRepository;
	private final ReplyRepository replyRepository;
	
	@Override
	public Long register(BoardDTO dto) {
		System.out.println("게시글 등록 메소드");
		Board board = dtoToEntity(dto);
		
		boardRepository.save(board);
		return board.getBno();
	}

	//페이지에 따른 글 항목 리턴받는 메소드 정의
	@Override
	public PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {

		System.out.println("게시판 목록 요청(page 및 검색 정보) : " + pageRequestDTO);
		
		//pageResultDTO 를 생성할때 Entity 와 변환 매퍼 함수를 같이 줘야 하는데,
		//이 변환 매퍼는 service 에 정의한 entityToDTO()임.
		//이때, 이 매퍼에는 Query 로 조회된 Object[] 내의 값을 분해해서 Board, Member, 댓글 수를 파라미터로 전달해야 하고
		//이 값을 받아서 하나의 DTO 로 만드는 작업을 하도록 정의되어 있음. 따라서 위 파라미터 정보를 넘겨야 함.
		
		Function<Object[], BoardDTO> fn = (en -> entityToDTO((Board)en[0], (Member)en[1], (Long)en[2]));
		
		Page<Object[]> result = boardRepository.getBoardWithReplyCount(pageRequestDTO.getPageable(Sort.by("bno").descending()));
		
		return new PageResultDTO<BoardDTO, Object[]>(result, fn);
	}
	
	@Override
	public BoardDTO getRead(Long bno) {
		
		Object obj = boardRepository.getBoardByBno(bno);
		Object[] arr = (Object[])obj;
		
		return entityToDTO((Board)arr[0], (Member)arr[1], (Long)arr[2]);
	}
	
	//댓글과 함께 계시글 삭제 처리
	@Transactional
	@Override
	public void removeWithReplies(Long bno) {

		//댓글 삭제
		replyRepository.deleteByBno(bno);
		boardRepository.deleteById(bno);
	}
	
	@Transactional
	@Override
	public void modify(BoardDTO boardDTO) {

		//getref... 는 지연 로딩 처리메소드. 이 메소드에는 connection 이 유지돼야하므로 transactional 선언
		Board board = boardRepository.getReferenceById(boardDTO.getBno());
		
		if(board != null) {
			board.chageContent(boardDTO.getContent());
			board.chageTitle(boardDTO.getTitle());
			
			boardRepository.save(board);
		}
	}
}
