package kr.or.nextit.login.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.nextit.common.util.CookieBox;
import kr.or.nextit.web.servlet.Controller;

public class LoginFormController implements Controller{
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		/*String referer = request.getHeader("Referer");
		System.out.println("referer" + referer);
		
		HttpSession session = request.getSession();	// 유지가능해서 세션적용
		session.setAttribute("previewPage", referer);*/
		
		return "login/loginForm";
		
	}
}
