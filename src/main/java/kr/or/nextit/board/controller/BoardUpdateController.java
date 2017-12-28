/*package kr.or.nextit.board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import kr.or.nextit.board.model.Board;
import kr.or.nextit.board.service.BoardService;
import kr.or.nextit.board.service.impl.BoardServiceImpl;
import kr.or.nextit.common.file.model.FileItem;
import kr.or.nextit.common.file.service.FileItemService;
import kr.or.nextit.common.file.service.impl.FileItemServiceImpl;
import kr.or.nextit.member.model.Member;
import kr.or.nextit.web.servlet.Controller;

public class BoardUpdateController implements Controller{
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//request.setCharacterEncoding("utf-8");
		
		
		Board board = new Board();
		
		BeanUtils.populate(board, request.getParameterMap());
		
		
		// 로그인 여부 확인
		HttpSession session = request.getSession();
				
		Member member = (Member)session.getAttribute("LOGIN_USER");
			
		if(member == null) {
			return "redirect:/login/loginForm.do";
		}
		
		// 세션에서 로그인 사용자 정보 셋팅.
		board.setUpd_user(member.getMem_id());
		
		String viewPage = "common/message";

		boolean isError = false;
		String message = "정상 수정되었습니다.";
		String locationURL = "/board/boardView.do?bo_seq_no=" + board.getBo_seq_no();
		
		try {
			BoardService boardService = BoardServiceImpl.getInstance();
			
			FileItemService fileItemService = FileItemServiceImpl.getInstance();
			
			//파일 업로드처리
			List<FileItem> fileItemList = fileItemService.uploadFiles(request, board.getBo_type());
			board.setFileItemList(fileItemList);
			
			int updCnt = boardService.updateBoard(board);
			
			if(updCnt == 0) {
				isError = true;
				message = "수정 실패하였습니다.";
			}
		}catch (Exception e) {
			isError = true;
			message = "수정 실패하였습니다.";
		}
		
		request.setAttribute("isError", isError);
		request.setAttribute("message", message);
		request.setAttribute("locationURL", locationURL);
		
		return viewPage;
	}

}
*/