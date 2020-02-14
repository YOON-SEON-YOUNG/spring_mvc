package com.kh.sample01.persistence;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kh.sample01.domain.MemberVo;

@Repository 
public class MemberDaoImpl implements MemberDao {

	private static final String NAMESPACE = "com.kh.mappers.memberMapper";
	
	@Inject
	private SqlSession sqlSession;
	
	@Override
	public String getTime() {
		
		String time = sqlSession.selectOne(NAMESPACE + ".getTime");
		return time;
	}

	@Override
	public void insertMember(MemberVo vo) {
		
		sqlSession.insert(NAMESPACE + ".insertMember", vo);
	
	}

	@Override
	public MemberVo readMember(String userid) {
		
		MemberVo vo = sqlSession.selectOne(NAMESPACE + ".selectMember", userid);
		return vo;
		
	}

	@Override
	public MemberVo readWithPw(String userid, String userpw) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userid", userid);
		paramMap.put("userpw", userpw);
		
		return sqlSession.selectOne(NAMESPACE + ".readWithPw", paramMap);
		
	}

	@Override
	public void updatePoint(int point, String userid) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("point", point);
		paramMap.put("userid", userid);
		
		sqlSession.update(NAMESPACE + ".updatePoint", paramMap);
	}

}
