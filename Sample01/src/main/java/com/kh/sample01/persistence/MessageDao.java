package com.kh.sample01.persistence;

import com.kh.sample01.domain.MessageVo;

public interface MessageDao {
	
	// 쪽지 쓰기
	public void insert(MessageVo messageVo) throws Exception;
	
	
	// 쪽지 읽기
	public MessageVo select(int message_id) throws Exception;
	
	
	// 쪽지 열람 시각 수정
	public void update(int message_id) throws Exception;
	
	
	// 쪽지 삭제
	public void delete(int message_id) throws Exception;
	
	
}
