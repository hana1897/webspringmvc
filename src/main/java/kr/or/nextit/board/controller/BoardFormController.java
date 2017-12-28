/*package kr.or.nextit.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.nextit.board.model.Board;
import kr.or.nextit.board.service.BoardService;
import kr.or.nextit.board.service.impl.BoardServiceImpl;
import kr.or.nextit.member.model.Member;
import kr.or.nextit.web.servlet.Controller;

public class BoardFormController implements Controller{
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//request.setCharacterEncoding("utf-8");

		// 로그인 여부 확인
		HttpSession session = request.getSession();
		
		Member member = (Member)session.getAttribute("LOGIN_USER");
		
		// 로그인 폼으로 리다이렉트
		if(member == null) {
			return "redirect:/login/loginForm.do";
		}
		
		String boSeqNo = request.getParameter("bo_seq_no");

		Board board = new Board();
		
		if(boSeqNo != null) {
			BoardService boardService = BoardServiceImpl.getInstance();
			int bo_seq_no = Integer.parseInt(boSeqNo);
			board = boardService.getBoard(bo_seq_no);
			
		}else {
			// 로그인 사용자 정보
			board.setBo_writer(member.getMem_id());
			board.setBo_writer_name(member.getMem_name());
		}
		
		request.setAttribute("board", board);
		
		return "board/boardForm";
	}

}
*/