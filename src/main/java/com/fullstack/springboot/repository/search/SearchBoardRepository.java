package com.fullstack.springboot.repository.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fullstack.springboot.entity.Board;

/*
 * Query DSL 을 Repository 에서 확장하는 방법
 * 
 * @() 에서 처리하기 어려운 쿼리는 QueryDSL을 확장해서 좀 더 편하게 구현할 수 있음.
 * 
 * 순서는 아래와 같음.
 * 
 * 1. 별도의 Repository 인터페이스를 하나 생성
 * 2. 위 인터페이스를 구현하는 구현 클래스 생성(반드시 위 생성된 인터페이스명+Impl로 생성) --> 여기까지는 JPA 를 사용하는것과 동일
 * 3. 2번 구현할때 반드시 수퍼 클래스로 QueryDSLRepositorySupport 를 상속받아야 함.(반드시)
 * 4. 이렇게 상속받은 서비스의 구현 클래스에서 Q도메인 클래스(컴파일된 QBoard 등)을 이용해서 JPQLQuery 를 구현.
 */
public interface SearchBoardRepository {

	//test 용으로 board 를 리턴하는 메소드 선언
	Board search1();
	
	Page<Object[]> searchPage(String type, String keyword, Pageable pageable);
}
