package com.fullstack.springboot;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import com.fullstack.springboot.dto.BoardDTO;
import com.fullstack.springboot.dto.PageRequestDTO;
import com.fullstack.springboot.dto.PageResultDTO;
import com.fullstack.springboot.dto.ReplyDTO;
import com.fullstack.springboot.entity.Board;
import com.fullstack.springboot.entity.Member;
import com.fullstack.springboot.entity.Reply;
import com.fullstack.springboot.repository.BoardRepository;
import com.fullstack.springboot.repository.MemberRepository;
import com.fullstack.springboot.repository.ReplyRepository;
import com.fullstack.springboot.service.BoardService;
import com.fullstack.springboot.service.ReplyService;


@SpringBootTest
class BoardApplicationTests {

	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private ReplyRepository replyRepository;
	
	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private ReplyService replyService;
	
	@Test
//	void insertMembers() {
//		IntStream.rangeClosed(1, 100).forEach(value -> {
//			Member member = Member.builder()
//					.email("user"+value+"@aaa.com")
//					.password("1111")
//					.name("user"+value)
//					.build();
//
//			memberRepository.save(member);
//		});
//	}

//	void insertBoard() {
//		IntStream.rangeClosed(1, 100).forEach(value -> {
//			Member member = Member.builder()
//					.email("user"+value+"@aaa.com").build();
//			Board board = Board.builder()
//					.title("title"+value)
//					.member(member)
//					.content("content"+value)
//					.build();
//
//			boardRepository.save(board);
//		});
//	}
	
//	void insertReply() {
//		//Replyer 는 반드시 member email 중 하나여야 하고, 랜덤하게 생성해서 하나의 게시글에 하나이상의 댓글을 구성하도록 함.
//		
//		IntStream.rangeClosed(1, 100).forEach(value -> {
//			long bno = (long)(Math.random() * 100) + 1;
//			
//			Board board = Board.builder()
//					.bno(bno).build();
//			
//			Reply reply = Reply.builder()
//					.board(board)
//					.text("reply"+value)
//					.replyer("guest")
//					.build();
//			replyRepository.save(reply);
//		});
//	}
	
//	@Transactional
//	void testRead1() {
//		Optional<Board> optional = boardRepository.findById(71L);
//		
//		Board board = optional.get();
//		
//		System.out.println(board);
//		System.out.println(board.getMember());
//	}
	
//	void readReply() {
//		Optional<Reply> optional = replyRepository.findById(71L);
//		
//		Reply reply = optional.get();
//		
//		System.out.println(reply);
//		System.out.println(reply.getBoard());
//	}
	
	//Query 어노예시
//	void testReadWithReply() {
//		List<Object[]> res = boardRepository.getReplyWithBoard(10L);
//		Object[] arr = new Object[res.size()];
//		
//		for(int i = 0; i<res.size();i++) {
//			arr[i] = res.get(i);
//		}
//		for(Object arr1 : arr) {
//			System.out.println(arr1);
//		}
//	}
	
//	void testReadWithWriter() {
//		Object[] res = boardRepository.getBoardWithMember(10L);
//		
//		for(Object arr : res) {
//			System.out.println(arr);
//		}
//	}
	
//	void testReadWithWriter() {
//		Object[] res = boardRepository.doSomething();
//		
//		System.out.println(Arrays.toString(res));
//	}
	
//	void testPage() {
//		Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
//		Page<Object[]> res = boardRepository.getBoardWithReplyCount(pageable);
//		
//		res.get().forEach(t -> {
//			Object[] arr = (Object[])t;
//			System.out.println(Arrays.toString(arr));
//		});
//	}
	
//	void testDetail() {
//		Object res = boardRepository.getBoardByBno(10L);
//		Object[] arr = (Object[])res;
//		
//		System.out.println(Arrays.toString(arr));
//	}
	
//	void testRegister() {
//		
//		BoardDTO dto = BoardDTO.builder()
//				.content("rehi")
//				.title("rehi")
//				.writerEmail("user1@aaa.com")
//				.build();
//		
//		System.out.println(boardService.register(dto));
//	}
	
	/*
	 * 게시글의 리스트에 출력되는 DTO 는 쿼리에서 Object[] 로 구성됨.
	 * 따라서 이를 get 해서 DTO 로 변환을 해야하는데, 위 내용은 Board 와 Member, 그리고 댓글 수 Long 으로 구성됨.
	 * 이를 파라미터로 전달해서 BoardDTO로 구성할 예정.
	 * 이를 위해서 entityToDTO 를 오버로딩할 예정.
	 */
	
//	void testList() {
//		Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
//		Page<Object[]> res = boardRepository.getBoardWithReplyCount(pageable);
//		
//		res.get().forEach(t -> {
//			Object[] arr = (Object[])t;
//			for(Object obj : arr) {
//				System.out.println(obj.getClass());
//			}
//		});
//	}
	
	//리스트 페이지별 요청에 따른 글 목록 리턴 테스트
//	void testList() {
//		PageRequestDTO pageRequestDTO = new PageRequestDTO();
//		PageResultDTO<BoardDTO, Object[]> result = boardService.getList(pageRequestDTO);
//		
//		for(BoardDTO dto : result.getDtoList()) {
//			System.out.println(dto);
//		}
//	}
	
	//글 상세 요청 테스트
//	void testView() {
//		BoardDTO dto = boardService.getRead(10L);
//		
//		System.out.println(dto);
//	}
	
	//글 삭제 요청 테스트
//	void testDelete() {
//		boardService.removeWithReplies(71L);
//	}
	
	//글 수정 요청 테스트
//	void testUpdate() {
//		BoardDTO dto = BoardDTO.builder()
//				.bno(1L)
//				.content("바뀌 내용")
//				.title("바뀐 제목")
//				.writerEmail("user1@aaa.com")
//				.build();
//		
//		boardService.modify(dto);
//	}
	
	//쿼리 DSL 초기화 테스트
//	@Transactional
//	void testSearch1() {
//		boardRepository.search1();
//	}
	
	//검색된 페이지 리턴 테스트
//	@Transactional
//	void searchKeyword() {
//		Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending().and(Sort.by("title").ascending()));
//		
//		Page<Object[]> result = boardRepository.searchPage("t", "l", pageable);
//		result.forEach(t -> System.out.println(t));
//	}
	
//	@Transactional
//	void replyTest() {
//		Board board = Board.builder().bno(1L).build();
//		
//		List<Reply> list = replyRepository.getListRepliesByBoardOrderByRno(board);
//		list.forEach(t -> {
//			System.out.println(t);
//		});
//	}
	
	void replyRegisterTest() {
		ReplyDTO dto = ReplyDTO.builder()
				.text("hi")
				.replyer("guest111")
				.bno(1L)
				.build();
		
		replyService.register(dto);
	}
}
