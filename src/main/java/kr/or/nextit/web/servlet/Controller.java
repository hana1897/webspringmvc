package kr.or.nextit.web.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Controller {
	
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
}
