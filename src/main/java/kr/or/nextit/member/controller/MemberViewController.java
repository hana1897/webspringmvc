/*package kr.or.nextit.member.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.nextit.member.model.Member;
import kr.or.nextit.member.service.MemberService;
import kr.or.nextit.member.service.impl.MemberServiceImpl;
import kr.or.nextit.web.servlet.Controller;

public class MemberViewController implements Controller{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");

		String mem_id = request.getParameter("mem_id");

		MemberService memberService = MemberServiceImpl.getInstance();
		
		Member member = memberService.getMember(mem_id);
		
		request.setAttribute("member", member);
		
		String viewPage ="member/memberView";
		
		return viewPage;
	}
	
}
*/