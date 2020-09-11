package com.myp.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.myp.domain.MemberVO;

@Repository
public class MemberDAOImpl implements MemberDAO{

	@Inject
	private SqlSession session;
	
	private static String namespace = "com.myp.mapper.UserMapper";
	
	@Override
	public void register(MemberVO vo) throws Exception {
		session.insert(namespace+".register", vo);
	}
	
	@Override
	public MemberVO login(MemberVO vo) throws Exception {
		return session.selectOne(namespace+".login", vo);
	}
	
	@Override
	public void memberUpdate(MemberVO vo) throws Exception {
		session.update(namespace+".memberUpdate", vo);
	}
	
	@Override
	public void memberDelete(MemberVO vo) throws Exception {
		// TODO Auto-generated method stub
		session.delete(namespace+".memberDelete",vo);
	}
	
	@Override
	public int passChk(MemberVO vo) throws Exception {
		int result = session.selectOne(namespace+".passChk", vo);
		return result;
	}
	
	@Override
	public int idChk(MemberVO vo) throws Exception {
		int result = session.selectOne(namespace+".idChk", vo);
		return result;
	}
}
