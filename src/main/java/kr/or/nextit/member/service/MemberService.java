package kr.or.nextit.member.service;

import java.util.List;
import java.util.Map;

import kr.or.nextit.member.model.Member;

public interface MemberService {

	// 회원목록조회
	public List<Member> getMemberList(Map<String, Object> paramMap) throws Exception;// ArrayList도 가능 검색  Map<String, Object> String searchType,String searchWord
	
	// 회원정보조회
	public Member getMember(String mem_id) throws Exception;
	
	// 회원 등록
	public int insertMember(Member member) throws Exception;
	
	// 회원 수정
	public int updateMember(Member member) throws Exception;
	
	// 회원 삭제
	public int deleteMember(String mem_id) throws Exception;

	// 회원 삭제
	//public int deleteMember(String[] mem_id) throws Exception; //대량삭제시
	
}
