package com.kh.sample01.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.sample01.domain.ReplyVo;
import com.kh.sample01.persistence.BoardDao;
import com.kh.sample01.persistence.ReplyDao;

@Service
public class ReplyServiceImpl implements ReplyService {
	
	@Inject
	private ReplyDao replyDao;
	
	@Inject
	private BoardDao boardDao;

	@Transactional
	@Override
	public void create(ReplyVo replyVo) throws Exception {
		replyDao.create(replyVo);						// 댓글 작성
		boardDao.updateReplyCnt(1, replyVo.getBno());	// 댓글 개수 얻기
	}

	@Override
	public List<ReplyVo> list(int bno) throws Exception {
		return replyDao.list(bno);
	}

	@Override
	public void update(ReplyVo replyVo) throws Exception {
		replyDao.update(replyVo);
	}

	@Transactional
	@Override
	public void delete(int rno, int bno) throws Exception {
		replyDao.deleteReplyByBno(bno);		// 댓글 삭제(해당 게시글)
		replyDao.delete(rno);				// 댓글 삭제(해당 댓글 1개)
		boardDao.updateReplyCnt(-1, bno);	// 댓글 삭제시 게시글 목록에서 보이는거..개수 차감
	}

}
