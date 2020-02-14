package com.kh.sample01.service;

import com.kh.sample01.domain.MessageVo;

public interface MessageService {
	
	// 쪽지 쓰기
	public void sendMessage(MessageVo messageVo) throws Exception;

	// 쪽지 읽기
	public MessageVo readMessage(int message_id, String userid) throws Exception;

}
