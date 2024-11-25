package com.fullstack.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fullstack.springboot.entity.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

	//글 삭제에 필요한 특정 글번호에 해당하는 댓글, 글 삭제 쿼리 작성.
	@Modifying//Select 가 아닌 DML은 이 어노테이션 선언해야 함.
	@Query("delete from Reply r where r.board.bno = :bno")
	void deleteByBno(@Param("bno") Long bno);
}
