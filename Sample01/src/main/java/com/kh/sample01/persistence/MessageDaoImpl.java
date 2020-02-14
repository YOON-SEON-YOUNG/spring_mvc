package com.kh.sample01.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kh.sample01.domain.MessageVo;

@Repository
public class MessageDaoImpl implements MessageDao {
	
	private static final String NAMESTRING ="com.kh.mappers.messageMapper";
	
	@Inject
	private SqlSession sqlSession;
	
	@Override
	public void insert(MessageVo messageVo) throws Exception {
		sqlSession.insert(NAMESTRING + ".insert", messageVo);
	}

	@Override
	public MessageVo select(int message_id) throws Exception {
		return sqlSession.selectOne(NAMESTRING + ".select", message_id);
	}

	@Override
	public void update(int message_id) throws Exception {
		sqlSession.update(NAMESTRING + ".update", message_id);
	}

	@Override
	public void delete(int message_id) throws Exception {
		sqlSession.delete(NAMESTRING + ".delete", message_id);
	}

}
