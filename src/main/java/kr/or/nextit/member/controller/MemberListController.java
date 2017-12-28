/*package kr.or.nextit.member.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import kr.or.nextit.member.model.Member;
import kr.or.nextit.member.service.MemberService;
import kr.or.nextit.member.service.impl.MemberServiceImpl;


public class MemberListController implements Controller{
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		request.setCharacterEncoding("utf-8");
		
		String searchType = request.getParameter("searchType");
		String searchWord = request.getParameter("searchWord");

		
		Map<String, Object> paramMap = new HashMap();
		
		if(searchWord != null && !searchWord.isEmpty()) {
			paramMap.put("searchType", searchType);
			paramMap.put("searchWord", searchWord);
		}
		
		MemberService memberService = MemberServiceImpl.getInstance();
		
		List<Member> memberList = memberService.getMemberList(paramMap);
		
		ModelAndView mav = new ModelAndView("member/memberList");
		
		mav.addObject("memberList", memberList);
		
		//request.setAttribute("memberList", memberList);
		
		return mav; //업무기능까지만
	}
	
}
*/