package com.kh.sample01;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kh.sample01.domain.MessageVo;
import com.kh.sample01.persistence.MessageDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class MessageDaoTest {
	
	@Inject
	private MessageDao messageDao;
	
	@Test
	public void testInsert() throws Exception {
		
		MessageVo messageVo = new MessageVo();
		
		messageVo.setSender("user01");
		messageVo.setTarget_id("user02");
		messageVo.setMessage_text("쪽지 테스트 1");

		messageDao.insert(messageVo);
	}
	
	@Test
	public void testSelect() throws Exception {
		messageDao.select(1);
	}
	
	@Test
	public void testUpdate() throws Exception {
		messageDao.update(1);
	}
	
	@Test
	public void testDelete() throws Exception {
		messageDao.delete(1);
	}

}
