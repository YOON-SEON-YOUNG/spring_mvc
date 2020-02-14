package com.kh.sample01.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.kh.sample01.domain.LoginDto;
import com.kh.sample01.domain.MemberVo;
import com.kh.sample01.persistence.MemberDao;

@Service
public class MemberServiceImpl implements MemberService {

	
	@Inject
	private MemberDao memberDao;
	
	@Override
	public MemberVo login(LoginDto loginDto) throws Exception {
		return memberDao.readWithPw(loginDto.getUserid(), loginDto.getUserpw());
	}

}
