package com.fullstack.springboot.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fullstack.springboot.dto.BoardDTO;
import com.fullstack.springboot.entity.Board;
import com.fullstack.springboot.entity.Reply;

public interface BoardRepository extends JpaRepository<Board, Long> {

	//JPQL : JAVA Persistent Query Language
	/*
	 * 객체지향 쿼리
	 * 
	 * 객체지향 쿼리(Hibernate) 은 테이블이 아닌 엔티티를 대상으로 쿼리를 날리는건 같음.
	 * 그러나 쿼리를 구성할때 형식은 아래와 같이 일반 쿼리와 약간 다름.
	 * 
	 * 1. Inner Join : Join 의 형태를 유사하나 조인을 맺는 방법이 field 라는 점이 좀 다름
	 * 즉, 기존의 조인 방식(ANSI) 은 From 절에 Join 될 테이블과 컬럼을 나열해서 조인을 하는데
	 * 이 방식은 조일을 걸 테이블(Many To One)에서 Many 쪽에서 참조하고 있는 컬럼을 조인 시 사용함.
	 * 이때 Inner 키워드는 생략 가능함.
	 * 
	 * ex) select b.title, b.content, m.name, m.email from Board b [Inner] Join b.member m where b.bno = :bno
	 * --> 기본 리턴타입은 Object 형. 그런데 이 내부에 Object[] 이 값으로 들어가 있음.
	 * 
	 * 2. Left(Right) Join [ON] : JPA 2.3 이후부터는 연관관게가 없는 테이블에서도 조인을 걸 수 있음.
	 * 예를 들면 Member 와 Reply 는 객체간의 연관관계가 없음.
	 * 이럴 때 서로 연관이 없는 테이블 에도 join 을 수행할 수 있는데, 이때 반드시 On 이라는 키워드를 사용해야 함.
	 * On 은 서로가 참조가 없는 (클래스 상태에서) 테이블반의 공통 컬럼을 Join 하는데 사용함. 
	 */
	
	//특정 글번호에 해당하는 글 정보와 사용자 정보를 가져오는 조인쿼리 작성
	@Query("select b,m from Board b join b.member m where b.bno= :bno")
	Object getBoardWithMember(@Param("bno") Long bno);//Object[] 로 리턴됨
	
	//집계함수를 수행하는 쿼리
	@Query("select count(u) from Board u")
	Object getCountOfBoard();
	
	//특정 게시물에 할당된 댓글의 정보를 가져옴
	@Query("select b,r from Board b right join Reply r On r.board = b where b.bno = :bno")
	Object[] getBoardWithReply(@Param("bno") Long bno);
	
	//group by 절을 사용해서 조건절 추가
	@Query("select b.bno, count(r.board) from Board b left join Reply r on b = r.board group by r.board")
	Object[] doSomething();
	
	//특정 글에 대한 모든 정보를 가져오도록 함.
	//이 중에는 특정 글에 대한 댓글수를 count 로 가져올건데, 이때 사용되는 Query 의 속성중에 count query 를 사용.
	@Query(value = "select b,m,count(r) from Board b left join b.member m left Join Reply r on r.board = b group by b"
			,countQuery = "select count(b) from Board b")
	Page<Object[]> getBoardWithReplyCount(Pageable pageable);
	
	//글 상세에서 필요한 특정 글번호에 해당하는 모든 정보를 가져오는 쿼리를 작성함.
	@Query("select b,m, count(r) from Board b left join b.member m left join Reply r on r.board = b where b.bno = :bno")
	Object getBoardByBno(@Param("bno") Long bno);

}