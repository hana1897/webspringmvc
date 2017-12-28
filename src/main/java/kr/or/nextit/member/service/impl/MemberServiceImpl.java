package kr.or.nextit.member.service.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.nextit.common.exception.NoSuchDataException;
import kr.or.nextit.common.exception.ServiceException;
import kr.or.nextit.common.jdbc.ConnectionProvider;
import kr.or.nextit.member.dao.MemberDao;
import kr.or.nextit.member.mapper.MemberMapper;
import kr.or.nextit.member.model.Member;
import kr.or.nextit.member.service.MemberService;
import kr.or.nextit.mybatis.MybatisSqlSessionFactory;
//이름설정안해주면 클래스이름 따라감
@Service("memberService")

public class MemberServiceImpl implements MemberService {

	private static Logger logger = Logger.getLogger(MemberServiceImpl.class);
	
	@Autowired
	MemberMapper memberMapper ;
	
	
	@Override
	public List<Member> getMemberList(Map<String, Object> paramMap) throws Exception {
		return memberMapper.selectMemberList(paramMap);
	}

	@Override
	public Member getMember(String mem_id) throws Exception {
		return memberMapper.selectMember(mem_id);
	}

	@Override
	public int insertMember(Member member) throws Exception {
		return memberMapper.insertMember(member);
	}

	@Override
	public int updateMember(Member member) throws Exception {
		return memberMapper.updateMember(member);
	}

	@Override
	public int deleteMember(String mem_id) throws Exception {
		return memberMapper.deleteMember(mem_id);
	}
}
