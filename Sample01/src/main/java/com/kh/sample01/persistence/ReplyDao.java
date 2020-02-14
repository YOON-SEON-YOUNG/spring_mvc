package com.kh.sample01.persistence;

import java.util.List;

import com.kh.sample01.domain.ReplyVo;

public interface ReplyDao {
	
	public void create(ReplyVo replyVo) throws Exception;
	
	public List<ReplyVo> list(int bno) throws Exception;
	
	public void update(ReplyVo replyVo) throws Exception;
	
	public void delete(int rno) throws Exception;

	public void deleteReplyByBno(int bno) throws Exception;


}


