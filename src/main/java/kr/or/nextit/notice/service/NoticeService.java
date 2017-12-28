package kr.or.nextit.notice.service;

import java.util.List;
import java.util.Map;

import kr.or.nextit.notice.model.Notice;

public interface NoticeService {

	// 목록조회
	public List<Notice> getNoticeList(Map<String, Object> paramMap) throws Exception;// ArrayList도 가능 검색  Map<String, Object> String searchType,String searchWord
	
	// 정보조회
	public Notice getNotice(int notice_seq_no) throws Exception;
	
	// 등록
	public int insertNotice(Notice notice) throws Exception;
	
	// 수정
	public int updateNotice(Notice notice) throws Exception;
	
	// 삭제
	public int deleteNotice(int notice_seq_no) throws Exception;

	
	
}
