/*package kr.or.nextit.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.nextit.board.model.Board;
import kr.or.nextit.board.service.BoardService;
import kr.or.nextit.board.service.impl.BoardServiceImpl;
import kr.or.nextit.common.util.PagingUtil;
import kr.or.nextit.web.servlet.Controller;

public class BoardListController implements Controller{
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		
		String searchType = request.getParameter("searchType");
		String searchWord = request.getParameter("searchWord");
		
		// 페이징 처리 데이터
		int currentPage = 1;
		int pageSize = 10;
		
		int pageCount = 5;	//기본값
		int totalCount = 0;
		
		if(StringUtils.isNotBlank(request.getParameter("currentPage"))) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		if(StringUtils.isNotBlank(request.getParameter("pageSize"))) {
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
		}
		
		Map<String, Object> paramMap = new HashMap<>();

		if(StringUtils.isNotBlank(searchType) && StringUtils.isNotBlank( searchWord )) {
			paramMap.put("searchType", searchType);
			paramMap.put("searchWord", searchWord);
		}
		
		BoardService boardService = BoardServiceImpl.getInstance();
		
		// 총 게시글 수
		totalCount = boardService.getBoardConunt(paramMap);
		
		// 페이징 처리
		PagingUtil pagingUtil = new PagingUtil(currentPage, totalCount, pageSize, pageCount);
		
		paramMap.put("startRow", pagingUtil.getStartRow());
		paramMap.put("endRow", pagingUtil.getEndRow());
		
		List<Board> boardList = boardService.getBoardList(paramMap);
		
		request.setAttribute("boardList", boardList);
		request.setAttribute("pagingUtil", pagingUtil);
		
		return "board/boardList";
	}

}
*/