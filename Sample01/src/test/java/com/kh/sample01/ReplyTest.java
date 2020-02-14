package com.kh.sample01;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kh.sample01.domain.ReplyVo;
import com.kh.sample01.persistence.ReplyDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class ReplyTest {
	
	@Inject
	private ReplyDao replyDao;

	
	@Test
	public void testCreate() throws Exception{
		ReplyVo replyVo = new ReplyVo();
		replyVo.setBno(562);
		replyVo.setReply_text("댓글2");
		replyVo.setReplyer("user02");
		replyDao.create(replyVo);
	}
	
	@Test
	public void testList() throws Exception{
		replyDao.list(562);
		
	}
	
	@Test
	public void testUpdate() throws Exception{
		ReplyVo replyVo = new ReplyVo();
		replyVo.setRno(1);
		replyVo.setReply_text("댓글1 수정");
		replyDao.update(replyVo);
	}
	
		
	@Test
	public void testDelete() throws Exception{
		replyDao.delete(1);
	}
}
