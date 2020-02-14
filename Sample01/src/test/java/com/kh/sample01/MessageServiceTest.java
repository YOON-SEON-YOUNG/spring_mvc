package com.kh.sample01;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kh.sample01.domain.MessageVo;
import com.kh.sample01.service.MessageService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class MessageServiceTest {
	
	@Inject
	private MessageService messageService;
	
	// 쪽지 읽기
	@Test
	public void testReadMessage() throws Exception {
		
		messageService.readMessage(2, "user02");
	}
	
	// 쪽지 쓰기
	@Test
	public void testSendMessage() throws Exception {
		MessageVo messageVo = new MessageVo();
		
		messageVo.setSender("user01");			// 보내는 사람
		messageVo.setTarget_id("user02");		// 읽는 사람
		messageVo.setMessage_text("쪽지 테스트1");
		
		messageService.sendMessage(messageVo);
	}
}
