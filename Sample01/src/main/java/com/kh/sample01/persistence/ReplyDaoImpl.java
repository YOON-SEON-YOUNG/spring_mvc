package com.kh.sample01.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kh.sample01.domain.ReplyVo;

@Repository
public class ReplyDaoImpl implements ReplyDao {
	
	private static final String NAMESPACE = "com.kh.mappers.replyMapper";
	
	@Inject
	private SqlSession sqlSession;
	
	@Override
	public void create(ReplyVo replyVo) throws Exception {
		sqlSession.insert(NAMESPACE + ".create", replyVo);
	}

	@Override
	public List<ReplyVo> list(int bno) throws Exception {
		return sqlSession.selectList(NAMESPACE + ".list", bno);
	}

	@Override
	public void update(ReplyVo replyVo) throws Exception {
		sqlSession.update(NAMESPACE + ".update", replyVo);
	}

	@Override
	public void delete(int rno) throws Exception {
		sqlSession.delete(NAMESPACE + ".delete", rno);

	}

	@Override
	public void deleteReplyByBno(int bno) throws Exception {
		sqlSession.delete(NAMESPACE + ".deleteReplyByBno", bno);		
	}

}
