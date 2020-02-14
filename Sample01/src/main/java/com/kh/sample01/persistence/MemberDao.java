package com.kh.sample01.persistence;

import com.kh.sample01.domain.MemberVo;

public interface MemberDao {
	
	public String getTime();
	
	public void insertMember(MemberVo vo);
	
	public MemberVo readMember(String userid);
	
	public MemberVo readWithPw(String userid, String userpw);
	
	public void updatePoint(int point, String userid);
	
	
}
