package kr.or.nextit.login.controller;
/*package kr.or.nextit.login.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.nextit.common.util.CookieBox;
import kr.or.nextit.member.model.Member;
import kr.or.nextit.member.service.MemberService;
import kr.or.nextit.member.service.impl.MemberServiceImpl;
import kr.or.nextit.web.servlet.Controller;

public class LoginController implements Controller {
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");

		String mem_id = request.getParameter("mem_id");
		String mem_pwd = request.getParameter("mem_pwd");
		String remember_me = request.getParameter("remember_me");
		
		boolean isError = true;
		String message = "";
		//String locationURL = "/";
		
		HttpSession session = request.getSession();	// 세션이 없으면 새로 생성해서 리턴. 옵션안주면 true
		
		MemberService memberService = MemberServiceImpl.getInstance();
		
		Member member = memberService.getMember(mem_id);
		
		if(member != null){
			
			if(member.getMem_pwd().equals(mem_pwd)) {
				// 로그인 처리
				session.setAttribute("LOGIN_USER", member);
				isError = false;
				message = member.getMem_name() + "님, 환영합니다.";
				
				
				
				// 쿠키 관련 처리
				if ("Y".equals(remember_me)) {
					response.addCookie(CookieBox.createCookie("USER_ID", member.getMem_id(), "/", 60 * 60 * 24 * 30));
					response.addCookie(CookieBox.createCookie("REMEMBER_ME", "Y", "/", 60 * 60 * 24));
				} else {
					response.addCookie(CookieBox.createCookie("USER_ID", "", "/", 0));
					response.addCookie(CookieBox.createCookie("REMEMBER_ME", "", "/", 0)); // 쿠키삭제
				}
				
			}else {
				message = "비밀번호가 일치하지 않습니다.";				
			}
		
		}else {
			message = "해당 아이디의 사용자를 찾을 수가 없습니다.";
		}
		
		if(isError) {
			request.setAttribute("isError", isError);
			request.setAttribute("message", message);
		}else {
			request.setAttribute("isError", isError);
			request.setAttribute("message", message);
			
			if(session.getAttribute("previewPage") != null) {
				String previewPage = (String)session.getAttribute("previewPage");
				request.setAttribute("locationURL", previewPage);
				session.removeAttribute("previewPage");
			}else {
				request.setAttribute("locationURL", "/");
			}
			//request.setAttribute("locationURL", locationURL);
		}
		
		return "common/message";
	}
}
*/