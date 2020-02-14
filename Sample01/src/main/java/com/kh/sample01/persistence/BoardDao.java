package com.kh.sample01.persistence;

import java.util.List;

import com.kh.sample01.domain.BoardVo;
import com.kh.sample01.domain.PagingDto;

public interface BoardDao {
	
	// 데이터 추가
	public void create(BoardVo vo) throws Exception;
	
	// 데이터 읽기
	public BoardVo read(Integer bno) throws Exception;
	
	// 데이터 수정/변경
	public void update(BoardVo vo) throws Exception;
	
	// 데이서 삭제
	public void delete(Integer bno) throws Exception;
	
	// 데이터 목록
	public List<BoardVo> listAll(PagingDto pagingDto) throws Exception;
	
	// 데이터 개수
	public int listCount(PagingDto pagingDto) throws Exception;
	
	// 댓글 개수 수정
	public void updateReplyCnt(int count, int bno) throws Exception;
	
	// 조회수 수정
	public void updateViewCnt(int bno) throws Exception;
	
	// 첨부파일 추가
	public void attach(String full_name, int bno) throws Exception;
	
	// 다음 시퀀스 값 얻기
	public int getNextVal() throws Exception;
	
	// 첨부 파일명 목록 얻기
	public List<String> getAttach(int bno) throws Exception;
	
	// 첨부 파일 데이터 삭제
	public void deleteAttach(String full_name) throws Exception;
	
	// 첨부 파일 데이터 삭제 (by bno)
	public void deleteAttachByBno(int bno) throws Exception;
	
	
	
	
}
