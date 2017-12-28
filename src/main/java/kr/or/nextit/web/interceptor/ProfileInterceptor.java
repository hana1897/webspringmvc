package kr.or.nextit.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class ProfileInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		System.out.println("ProfileInterceptor.preHandle() 실행 : " + handler.getClass().getName());
		
		request.setAttribute("interceptor.startTime", System.currentTimeMillis());
		
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mav)
			throws Exception {
		
		
	}
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		// 컨트롤러 수행시간 체크, 로깅처리
		
		Logger logger = (Logger) LoggerFactory.getLogger(handler.getClass());
		
		Long startTime = (Long)request.getAttribute("interceptor.startTime");
		long duration = System.currentTimeMillis() - startTime;
		
		logger.info(request.getRequestURI() + " : 수행시간 = " + duration);
		
		if(ex != null) {
			logger.error("aferComletion : " + ex.getMessage(),ex);
		}
		
		
	}

}
	

