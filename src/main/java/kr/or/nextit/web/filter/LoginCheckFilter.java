package kr.or.nextit.web.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCheckFilter implements Filter{

	List<String> excludeURI = new ArrayList<String>();

	@Override
	public void init(FilterConfig config) throws ServletException {
		
		excludeURI.add("/login/loginForm.do");
		excludeURI.add("/login/logout.do");
		excludeURI.add("/login/login.do");
		excludeURI.add("/board/boardList.do");
		excludeURI.add("/board/boardView.do");
		excludeURI.add("/member/memberList.do");
		excludeURI.add("/member/memberView.do");
		excludeURI.add("/member/memberForm.do");
		excludeURI.add("/member/memberInsert.do");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
	
		
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpServletResponse httpResponse = (HttpServletResponse)response;
		
		HttpSession session = httpRequest.getSession();
		
		// /login/loinForm.do, /board/boardList.do, /board/boardView.do
		String uri = httpRequest.getRequestURI(); // /webmvc/??
		if(uri.indexOf(httpRequest.getContextPath()) ==0) {
			uri = uri.substring(httpRequest.getContextPath().length());
		}
		
		
		// 로그인 정보 체크
		if(!excludeURI.contains(uri) && session.getAttribute("LOGIN_USER") == null) {
			
			session.setAttribute("previewPage", uri);
			
			httpResponse.sendRedirect(httpRequest.getContextPath()+ "/login/loginForm.do");
		}else {
			//정상 진행.
			chain.doFilter(request, response);
		}
		
	}

	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
