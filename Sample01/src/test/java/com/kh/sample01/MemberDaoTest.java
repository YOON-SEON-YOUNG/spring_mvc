package com.kh.sample01;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kh.sample01.common.MyConstants;
import com.kh.sample01.domain.MemberVo;
import com.kh.sample01.persistence.MemberDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class MemberDaoTest {
	
	@Inject //자동주입
	private MemberDao dao;
	
	@Test
	public void testGetTime() throws Exception {
		String time = dao.getTime();
		System.out.println("time: " + time);
	}
	
	@Test
	public void testInsertMember() throws Exception {
		MemberVo vo = new MemberVo();
		vo.setUserid("user02");
		vo.setUserpw("1234");
		vo.setUsername("유저2");
		vo.setEmail("user02@email.com");
		
		dao.insertMember(vo);
	}
	
	@Test
	public void testReadMember() throws Exception {
		String userid = "user02";
		dao.readMember(userid);
	}
	
	@Test
	public void testReadWithPwMember() throws Exception {
		String userid = "user01";
		String userpw = "1234";
		dao.readWithPw(userid, userpw);
	}
	
	@Test
	public void testUpdatePoint() throws Exception {
		
		dao.updatePoint(MyConstants.MESSAGE_SEND_POINT, "user01");
	}
	
}
