package com.myp.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.myp.domain.MemberVO;
import com.myp.persistence.MemberDAO;

@Service
public class MemberServiceImpl implements MemberService{

	@Inject
	private MemberDAO dao;
	
	@Override
	public void register(MemberVO vo) throws Exception {
			dao.register(vo);
	}
	
	@Override
	public MemberVO login(MemberVO vo) throws Exception {
		return dao.login(vo);
	}
	
	@Override
	public void memberUpdate(MemberVO vo) throws Exception {
		dao.memberUpdate(vo);
	}
	
	@Override
	public void memberDelete(MemberVO vo) throws Exception {
		// MemberVO에 담긴 값들을 보내줍니다.
		// 그럼 xml에서 memberMapper.memberDelete에 보시면
		//  #{userId}, #{userPass}에 파라미터값이 매칭이 되겠지요.
		dao.memberDelete(vo);
	}
	
	@Override
	public int passChk(MemberVO vo) throws Exception {
		int result = dao.passChk(vo);
		return result;
	}
	
	@Override
	public int idChk(MemberVO vo) throws Exception {
		int result = dao.idChk(vo);
		return result;
	}
}
