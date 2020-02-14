package com.kh.sample01.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kh.sample01.domain.BoardVo;
import com.kh.sample01.domain.PagingDto;


@Repository
public class BoardDaoImpl implements BoardDao {
	
	private static final String NAMESPACE = "com.kh.mappers.boardMapper";
	
	@Inject
	private SqlSession sqlSession;
	
	// 데이터 추가
	@Override
	public void create(BoardVo vo) throws Exception {
		
		sqlSession.insert(NAMESPACE + ".create", vo);
	}

	// 데이터 읽기
	@Override
	public BoardVo read(Integer bno) throws Exception {
		
		BoardVo vo = sqlSession.selectOne(NAMESPACE + ".read", bno);
		return vo;
	}

	// 데이터 수정/변경
	@Override
	public void update(BoardVo vo) throws Exception {
		
		sqlSession.update(NAMESPACE + ".update", vo);
		
	}

	// 데이터 삭제
	@Override
	public void delete(Integer bno) throws Exception {
		
		sqlSession.delete(NAMESPACE + ".delete", bno);
		
	}

	// 데이터 목록
	@Override
	public List<BoardVo> listAll(PagingDto pagingDto) throws Exception {
		
		List<BoardVo> list = sqlSession.selectList(NAMESPACE + ".listAll", pagingDto);
		return list;
	}


	// 데이터 개수
	@Override
	public int listCount(PagingDto pagingDto) throws Exception {
		
		int count = sqlSession.selectOne(NAMESPACE + ".listCount", pagingDto);
		
		return count;
	}

	// 댓글 개수 수정
	@Override
	public void updateReplyCnt(int count, int bno) throws Exception {
		
		Map<String, Integer> paramMap = new HashMap<>();
		paramMap.put("count", count);
		paramMap.put("bno", bno);
		
		sqlSession.update(NAMESPACE + ".updateReplyCnt", paramMap);		
	}

	// 조회수 수정
	@Override
	public void updateViewCnt(int bno) throws Exception {
		sqlSession.update(NAMESPACE + ".updateViewCnt", bno);
	}

	// 첨부파일 추가
	@Override
	public void attach(String full_name, int bno) throws Exception {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("full_name", full_name);
		paramMap.put("bno", bno);
		
		sqlSession.insert(NAMESPACE + ".attach", paramMap);
	}

	// 다음 시퀀스 값 얻기
	@Override
	public int getNextVal() throws Exception {
		return sqlSession.selectOne(NAMESPACE + ".getNextVal");
	}

	// 첨부 파일명 목록 얻기
	@Override
	public List<String> getAttach(int bno) throws Exception {
		return sqlSession.selectList(NAMESPACE + ".getAttach", bno);
	}

	// 첨부 파일 데이터 삭제
	@Override
	public void deleteAttach(String full_name) throws Exception {
		sqlSession.delete(NAMESPACE + ".deleteAttach", full_name);		
	}

	// 첨부 파일 데이터 삭제 (by bno)
	@Override
	public void deleteAttachByBno(int bno) throws Exception {
		sqlSession.delete(NAMESPACE + ".deleteAttachByBno", bno);		
	}
	
	

}
