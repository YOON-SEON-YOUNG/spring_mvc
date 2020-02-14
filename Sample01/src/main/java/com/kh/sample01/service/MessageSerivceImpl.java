package com.kh.sample01.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.sample01.common.MyConstants;
import com.kh.sample01.domain.MessageVo;
import com.kh.sample01.persistence.MemberDao;
import com.kh.sample01.persistence.MessageDao;

@Service
public class MessageSerivceImpl implements MessageService {

	@Inject
	private MessageDao messageDao;
	
	@Inject
	private MemberDao memberDao;
	
	
	@Transactional
	@Override
	public void sendMessage(MessageVo messageVo) throws Exception {
		// 쪽지 보내기
		messageDao.insert(messageVo);			
		
		// 쪽지 보낸 포인트 + 10
		memberDao.updatePoint(MyConstants.MESSAGE_SEND_POINT, messageVo.getSender());	
	}
	
	@Transactional
	@Override
	public MessageVo readMessage(int message_id, String userid) throws Exception {
		
		// 읽은 사람의 포인트 + 5
		memberDao.updatePoint(MyConstants.MESSAGE_READ_POINT, userid);
		
		// 쪽지 정보의 읽은 시각 (open_date)
		messageDao.update(message_id);									
		
		// 쪽지 읽은 아이디
		return messageDao.select(message_id);							
	}
	

}
