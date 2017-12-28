package kr.or.nextit.member.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;

import kr.or.nextit.member.model.Member;

@Mapper
public interface MemberMapper {
	
	// 회원목록 조회
		public List<Member> selectMemberList(Map<String, Object> paramMap) throws Exception;
		
		// 회원정보 조회
		public Member selectMember(String mem_id) throws Exception;
				
		// 회원 등록
		public int insertMember(Member member) throws Exception;
		
		// 회원 수정
		public int updateMember(Member member) throws Exception;
	
		// 회원 삭제
		public int deleteMember(String mem_id) throws Exception;

}
