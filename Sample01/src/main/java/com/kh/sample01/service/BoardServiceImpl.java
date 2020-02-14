package com.kh.sample01.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.sample01.domain.BoardVo;
import com.kh.sample01.domain.PagingDto;
import com.kh.sample01.persistence.BoardDao;
import com.kh.sample01.persistence.ReplyDao;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Inject
	private BoardDao boardDao; 
	
	@Inject
	private ReplyDao replyDao;

	
	@Override
	@Transactional
	public void regist(BoardVo vo) throws Exception {
		
		int bno = boardDao.getNextVal();
		vo.setBno(bno);
		
		boardDao.create(vo);				//tbl_board에 insert
		
		String[] files = vo.getFiles();
		if(files != null && files.length != 0) {
			for( String file : files ) {
				boardDao.attach(file, bno); // tbl_attach에 insert
			}
		}
	}

	@Override
	public BoardVo read(Integer bno) throws Exception {
		boardDao.updateViewCnt(bno);	// 조회수 증가
		
		return boardDao.read(bno);
	}

	@Override
	public void modify(BoardVo vo) throws Exception {
		boardDao.update(vo);
	}

	@Override
	@Transactional
	public void delete(Integer bno) throws Exception {
		boardDao.deleteAttachByBno(bno);	// 첨부파일 데이터 삭제(by bno)
		replyDao.deleteReplyByBno(bno);		// 댓글 삭제
		boardDao.delete(bno);				// 게시글 삭제
	}

	@Override
	public List<BoardVo> listAll(PagingDto pagingDto) throws Exception {
		return boardDao.listAll(pagingDto);
	}

	@Override
	public int listCount(PagingDto pagingDto) throws Exception {
		return  boardDao.listCount(pagingDto);
	}

	@Override
	public List<String> getAttach(int bno) throws Exception {
		return boardDao.getAttach(bno);
	}

	@Override
	public void deleteAttach(String full_name) throws Exception {
		boardDao.deleteAttach(full_name);
	}


}
