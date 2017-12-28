package kr.or.nextit.board.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.nextit.board.mapper.BoardMapper;
import kr.or.nextit.board.model.Board;
import kr.or.nextit.board.service.BoardService;
import kr.or.nextit.common.file.mapper.FileItemMapper;
import kr.or.nextit.common.file.model.FileItem;
import kr.or.nextit.mybatis.MybatisSqlSessionFactory;

@Service("boardService")
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	BoardMapper boardMapper;
	
	@Autowired
	FileItemMapper fileItemMapper;

	@Override
	public int getBoardConunt(Map<String, Object> paramMap) throws Exception {
			return boardMapper.selectBoardConunt(paramMap);
	}

	
	@Override
	public List<Board> getBoardList(Map<String, Object> paramMap) throws Exception {
			return boardMapper.selectBoardList(paramMap);
	}

	@Override
	public Board getBoard(int bo_seq_no) throws Exception {
			//조회수 업데이트 수행
			boardMapper.updateHitCnt(bo_seq_no);
	
			Board board =  boardMapper.selectBoard(bo_seq_no);
			
			//파일 목록 조회
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("ref_seq_no", board.getBo_seq_no());
			paramMap.put("biz_type", board.getBo_type());
			
			List<FileItem> fileItemList = fileItemMapper.selectFileItemList(paramMap);
			board.setFileItemList(fileItemList);
		
			return board;
	}
	
	@Override
	public int insertBoard(Board board) throws Exception {
	
			int updCnt = boardMapper.insertBoard(board);
			
			// 파일 저장.
			List<FileItem> fileItemList = board.getFileItemList();
			if(fileItemList != null && !fileItemList.isEmpty()) {
				for (FileItem fileItem : fileItemList) {
					fileItem.setRef_seq_no(board.getBo_seq_no() + "");
					fileItem.setReg_user(board.getBo_writer());
					fileItem.setUpd_user(board.getBo_writer());
					fileItemMapper.insertFileItem(fileItem);
				}
			}
			
			return updCnt;
	
	}

	@Override
	public int updateBoard(Board board) throws Exception {
	
			// 기존 파일 삭제
			String[] delFileSeqNo = board.getDelFileSeqNo();
			if(delFileSeqNo != null && delFileSeqNo.length > 0) {
				Map<String, Object> paramMap = new HashMap<>();
				paramMap.put("delFileSeqNo", delFileSeqNo);
				fileItemMapper.deleteFileItem(paramMap);
			}
			
			int updCnt = boardMapper.updateBoard(board);
			
			// 신규 파일 등록
			List<FileItem> fileItemList = board.getFileItemList();
			if(fileItemList != null && !fileItemList.isEmpty()) {
				for (FileItem fileItem : fileItemList) {
					fileItem.setRef_seq_no(board.getBo_seq_no() + "");
					fileItem.setReg_user(board.getBo_writer());
					fileItem.setUpd_user(board.getBo_writer());
					fileItemMapper.insertFileItem(fileItem);
				}
			}
		
			return updCnt;
		
	}
	
	@Override
	public int deleteBoard(Map<String, Object> paramMap) throws Exception {
		return boardMapper.deleteBoard(paramMap);
		
	}
}
