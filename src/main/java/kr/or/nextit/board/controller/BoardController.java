package kr.or.nextit.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.nextit.board.model.Board;
import kr.or.nextit.board.service.BoardService;
import kr.or.nextit.common.file.model.FileItem;
import kr.or.nextit.common.file.service.FileItemService;
import kr.or.nextit.common.util.PagingUtil;
import kr.or.nextit.member.model.Member;

//주석추가
@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	@Autowired
	FileItemService fileItemService;
	
	@RequestMapping("/boardList")
	public String boardList(
			@RequestParam(value="searchType", required=false, defaultValue="") String searchType,
			@RequestParam(value="searchWord", required=false, defaultValue="") String searchWord,
			@RequestParam(value="currentPage", required=false, defaultValue="1") int currentPage,
			@RequestParam(value="pageSize", required=false, defaultValue="10") int pageSize,
			Model model
			) throws Exception {
		
			// 페이징 처리 데이터
			int pageCount = 5;	//기본값
			int totalCount = 0;
			
			Map<String, Object> paramMap = new HashMap<>();

			if(StringUtils.isNotBlank(searchType) && StringUtils.isNotBlank( searchWord )) {
				paramMap.put("searchType", searchType);
				paramMap.put("searchWord", searchWord);
			}
			
			// 총 게시글 수
			totalCount = boardService.getBoardConunt(paramMap);
			
			// 페이징 처리
			PagingUtil pagingUtil = new PagingUtil(currentPage, totalCount, pageSize, pageCount);
			
			paramMap.put("startRow", pagingUtil.getStartRow());
			paramMap.put("endRow", pagingUtil.getEndRow());
			
			List<Board> boardList = boardService.getBoardList(paramMap);
			
			model.addAttribute("boardList", boardList);
			model.addAttribute("pagingUtil", pagingUtil);
			
			return "board/boardList";
		}
			
			@RequestMapping("/boardView/{bo_seq_no}")
			public String boardView(
				//@RequestParam(value="bo_seq_no") int bo_seq_no,
				@PathVariable(value="bo_seq_no", required=true) int bo_seq_no,
				Model model
				) throws Exception {
					
			Board board = null;
			
			if(bo_seq_no != 0) {
				board = boardService.getBoard(bo_seq_no);
			}
			
			model.addAttribute("board", board);
			
				return "board/boardView";
		}		
			
			@RequestMapping("/boardForm")
			public String boardForm(
					HttpSession session,
					@RequestParam(value="bo_seq_no",  required=false, defaultValue="0") int bo_seq_no,
					Model model
					) throws Exception {
				
				// 로그인 여부 확인		
				Member member = (Member)session.getAttribute("LOGIN_USER");
				
				// 로그인 폼으로 리다이렉트
				if(member == null) {
					return "redirect:/login/loginForm";
				}
				
				Board board = new Board();
				
				if(bo_seq_no != 0) {
					board = boardService.getBoard(bo_seq_no);
					
				}else {
					// 로그인 사용자 정보
					board.setBo_writer(member.getMem_id());
					board.setBo_writer_name(member.getMem_name());
				}
				
				model.addAttribute("board", board);
				
				return "board/boardForm";
		}
			
			@RequestMapping(value="/boardInsert", method=RequestMethod.POST)
			public String boardInsert(
					Board board,
					HttpServletRequest request,
					Model model
					) throws Exception {
					
					String viewPage = "common/message";

					boolean isError = false;
					String message = "정상 등록되었습니다.";
					String locationURL = "/board/boardList";
					
					try {
						
						// 파일 목록 생성.
						List<FileItem> fileItemList = fileItemService.uploadFiles(request, board.getBo_type());
						
						board.setFileItemList(fileItemList);
						
						int updCnt = boardService.insertBoard(board);
						
						if(updCnt == 0) {
							isError = true;
							message = "등록 실패하였습니다.";
						}
					}catch (Exception e) {
						isError = true;
						message = "등록 실패하였습니다.";
						throw e;
					}
					
					model.addAttribute("isError", isError);
					model.addAttribute("message", message);
					model.addAttribute("locationURL", locationURL);
					
					return viewPage;
				}
			
			@RequestMapping(value="/boardUpdate", method=RequestMethod.POST)
			public String boardUpdate(
				Board board,
				HttpSession session,
				HttpServletRequest request,
				Model model
				) throws Exception {
				
				Member member = (Member)session.getAttribute("LOGIN_USER");
					
				if(member == null) {
					return "redirect:/login/loginForm";
				}
				
				// 세션에서 로그인 사용자 정보 셋팅.
				board.setUpd_user(member.getMem_id());
				
				String viewPage = "common/message";

				boolean isError = false;
				String message = "정상 수정되었습니다.";
				String locationURL = "/board/boardView/" + board.getBo_seq_no();
				
				try {
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
					throw e;
				}
				
				model.addAttribute("isError", isError);
				model.addAttribute("message", message);
				model.addAttribute("locationURL", locationURL);
				
				return viewPage;
			}
		
			@RequestMapping("/boardDelete")
			public String boardDelete(
					HttpSession session,
					@RequestParam(value="bo_seq_no") int bo_seq_no,
					Model model
					) {
				
				Member member = (Member)session.getAttribute("LOGIN_USER");
							
				if(member == null) {
					return "redirect:/login/loginForm";
				}
				
				Map<String, Object> paramMap = new HashMap<String, Object>();
				
				paramMap.put("upd_user", member.getMem_id());
				paramMap.put("bo_seq_no", bo_seq_no);
				
				String viewPage = "common/message";

				boolean isError = false;
				String message = "정상 삭제되었습니다.";
				String locationURL = "/board/boardList";
				
				try {
					
					int updCnt = boardService.deleteBoard(paramMap);
					
					if(updCnt == 0) {
						isError = true;
						message = "삭제 실패하였습니다.";
					}
				}catch (Exception e) {
					isError = true;
					message = "삭제 실패하였습니다.";
				}
				
				model.addAttribute("isError", isError);
				model.addAttribute("message", message);
				model.addAttribute("locationURL", locationURL);
				
				return viewPage;
			}
}
	
	
	
