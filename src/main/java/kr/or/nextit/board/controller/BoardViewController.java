/*package kr.or.nextit.board.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.nextit.board.model.Board;
import kr.or.nextit.board.service.BoardService;
import kr.or.nextit.board.service.impl.BoardServiceImpl;
import kr.or.nextit.common.file.model.FileItem;
import kr.or.nextit.common.file.service.FileItemService;
import kr.or.nextit.common.file.service.impl.FileItemServiceImpl;
import kr.or.nextit.common.util.ConstantUtil;
import kr.or.nextit.web.servlet.Controller;

public class BoardViewController implements Controller{
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		
		String boSeqNo = request.getParameter("bo_seq_no");
		Board  board = null;
		FileItem  fileItem = null;
		String  downPath = null;
		if(boSeqNo != null) {
			
			int bo_seq_no = Integer.parseInt(boSeqNo);
			BoardService boardService = BoardServiceImpl.getInstance();
			board = boardService.getBoard(bo_seq_no);
			//업데이트수행하면안됨 X
			//boardService.updateCount(board);
			
			FileItemServie fileItemServie = FileItemServieImpl.getInstance();
			Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("bo_seq_no", bo_seq_no);
			fileItem = fileItemServie.getFileItem(paramMap);
			downPath= ConstantUtil.DOWNLOAD_PATH;
		}
		
		request.setAttribute("board", board);
		request.setAttribute("fileItem", fileItem);
		request.setAttribute("downPath", downPath);
		
		return "board/boardView";
	}

}
*/