package com.kh.sample01;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kh.sample01.domain.BoardVo;
import com.kh.sample01.domain.PagingDto;
import com.kh.sample01.persistence.BoardDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class BoardDaoTest {

	
		@Inject
		private BoardDao dao;
		
		
		@Test
		public void testCreate() throws Exception {
			
			BoardVo vo = new BoardVo();
			vo.setTitle("제목1");
			vo.setContent("내용1");
			vo.setWriter("작성자1");
			dao.create(vo);
		}
		
		
		@Test
		public void testRead() throws Exception {
			int bno = 1;
			dao.read(bno);
		}
		
		
		@Test
		public void testUpdate() throws Exception {
		
			BoardVo vo = new BoardVo();
			vo.setBno(01);
			vo.setTitle("제목1 수정");
			vo.setContent("내용1 수정");
			dao.update(vo);
		}
		
		
		@Test
		public void testDelete() throws Exception {
			int bno = 1;
			dao.delete(bno);
		}
		
		
		@Test
		public void testListAll() throws Exception {
			
			PagingDto pagingDto = new PagingDto();
			pagingDto.setPage(1);
			pagingDto.setPerPage(20);
			pagingDto.setSearchType("title_content");
			pagingDto.setKeyword("30");
			
			dao.listAll(pagingDto);
			
//			List<BoardVo> list = dao.listAll(pagingDto);
			System.out.println("dao: " + dao);
			
		}
		
		@Test
		public void testListCount() throws Exception {
			
			PagingDto pagingDto = new PagingDto();
			pagingDto.setSearchType("title");
			pagingDto.setKeyword("15");
			
			dao.listCount(pagingDto);
		}
		
}
