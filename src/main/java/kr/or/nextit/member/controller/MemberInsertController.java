/*package kr.or.nextit.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import kr.or.nextit.member.model.Member;
import kr.or.nextit.member.service.MemberService;
import kr.or.nextit.member.service.impl.MemberServiceImpl;
import kr.or.nextit.web.servlet.Controller;

public class MemberInsertController implements Controller{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		
		//String viewPage = "redirect:/member/memberList.do";
		//String viewPage = "/member/memberInsert";
		String viewPage = "common/message";
		
		Member member = new Member();
		
		BeanUtils.populate(member, request.getParameterMap());
		
		boolean isError = false;
		String message = "정상적으로 회원가입 되었습니다.";
		
		// 유효성검증.
		if(member.getMem_id() == null || member.getMem_id().isEmpty() ) {
			isError = true;
			message = "아이디를 입력하세요!.";
		}
		if(StringUtils.isBlank(member.getMem_name())) {
			isError = true;
			message = "이름을 입력하세요!.";
		}
		
		try {
			
			if(!isError) {
			
				MemberService memberService = MemberServiceImpl.getInstance();
	
				int updCnt = memberService.insertMember(member);
	
				if (updCnt == 0) {
					// 에러
					message = "회원등록 실패하였습니다.";
					isError = true;
				}
			}
		} catch (Exception e) {
			// 에러
			e.printStackTrace();
			message = "회원등록 실패하였습니다.";
			isError = true;
		}
		
		//response.sendRedirect(request.getContextPath()+"/member/memberList.do");
		request.setAttribute("isError", isError);
		request.setAttribute("message", message);
		request.setAttribute("locationURL", "/member/memberList.do");
		return viewPage;
	}
	
}
*/