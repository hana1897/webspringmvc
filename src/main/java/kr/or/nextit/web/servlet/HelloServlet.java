package kr.or.nextit.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/hello2", "/hello", "/hi"}) // 어노테이션제공
public class HelloServlet extends HttpServlet{
	
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		
		System.out.println("init(ServletConfig config) 호출");
		super.init(config);
	}
	
	@Override
	public void init() throws ServletException {
		
		System.out.println("init() 호출");
		super.init();
	}
	
	@Override
	public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
		
		System.out.println("service(ServletRequest arg0, ServletResponse arg1) 호출");
		super.service(arg0, arg1);
	}
	
	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		
		System.out.println("service(HttpServletRequest arg0, HttpServletResponse arg1) 호출");
		super.service(arg0, arg1);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("doGet(HttpServletRequest request, HttpServletResponse response) 호출");
		request.setCharacterEncoding("utf-8");
		
		response.setContentType("text/html; charset=utf-8");
		
		PrintWriter writer = response.getWriter();
		
		writer.println("<html>");
		writer.println("<head>");
		writer.println("<title>");
		writer.println("Servlet 예제");
		writer.println("</title>");
		writer.println("</head>");
		writer.println("<body>");
		writer.println("안녕하세요.");
		writer.println(request.getParameter("user_name"));
		writer.println("님.");
		writer.println("</body>");
		writer.println("</html>");
		
		writer.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("doPost(HttpServletRequest req, HttpServletResponse resp) 호출");
		super.doPost(req, resp);
	}

	@Override
	public void destroy() {

		System.out.println("destroy() 호출");
		super.destroy();
	}
	
}
