package com.fullstack.springboot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class Reply extends BaseEntity{

	//댓글 번호, 댓글 내용, 작성자를 필드로 추가
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long rno;
	
	@Column(length = 50, nullable = false)
	private String text;
	
	@Column(length = 50, nullable = false)
	private String replyer;
	
	@ManyToOne
	private Board board;
}