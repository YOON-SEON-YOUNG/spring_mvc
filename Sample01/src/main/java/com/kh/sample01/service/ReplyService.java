package com.kh.sample01.service;

import java.util.List;

import com.kh.sample01.domain.ReplyVo;

public interface ReplyService {
	
	public void create(ReplyVo replyVo) throws Exception;
	
	public List<ReplyVo> list(int bno) throws Exception;
	
	public void update(ReplyVo replyVo) throws Exception;
	
	public void delete(int rno, int bno) throws Exception;
	
}
