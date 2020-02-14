package com.kh.sample01;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kh.sample01.domain.BoardVo;
import com.kh.sample01.service.BoardService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class BoardServiceTest {

	@Inject
	private BoardService boardService;
	
	@Test
	public void testRegist() throws Exception {
		
		BoardVo vo = new BoardVo();
		
		vo.setTitle("글제목 581");
		vo.setContent("글내용581");
		vo.setWriter("user01");
		vo.setFiles(new String[] {
						"test01.jpg", "test02.jpg", "test03.jpg" });
		
		boardService.regist(vo);
	}
	
	
	
}
