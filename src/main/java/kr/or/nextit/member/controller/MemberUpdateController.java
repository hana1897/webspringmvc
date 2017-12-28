/*package kr.or.nextit.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import kr.or.nextit.member.model.Member;
import kr.or.nextit.member.service.MemberService;
import kr.or.nextit.member.service.impl.MemberServiceImpl;
import kr.or.nextit.web.servlet.Controller;

public class MemberUpdateController implements Controller{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		
		String mem_id = request.getParameter("mem_id");
		
		Member member = new Member();
		
		BeanUtils.populate(member, request.getParameterMap());
		
		String viewPage = "common/message";
		
		boolean isError = false;
		String message = "정상 수정되었습니다.";
		
		if(StringUtils.isBlank(member.getMem_id())) {
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
	
				int updCnt = memberService.updateMember(member);
	
				if (updCnt == 0) {
					// 에러
					message = "회원수정에 실패하였습니다.";
					isError = true;
				}
			}

		} catch (Exception e) {
			// 에러
			e.printStackTrace();
			message = "회원수정에 실패하였습니다.";
			isError = true;
		}
		request.setAttribute("isError", isError);
		request.setAttribute("message", message);
		request.setAttribute("locationURL", "/member/memberView.do?mem_id=" + member.getMem_id());
		return viewPage;
	}
	
}
*/