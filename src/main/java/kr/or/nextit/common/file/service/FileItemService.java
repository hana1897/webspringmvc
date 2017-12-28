package kr.or.nextit.common.file.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import kr.or.nextit.common.file.model.FileItem;

public interface FileItemService {

	// 파일정보 조회
	public FileItem getFileItem(Map<String, Object> paramMap) throws Exception;
	
	// 다운로드 카운트 업데이트
	public int updateDownloadCnt(Map<String, Object> paramMap) throws Exception;
	
	// 파일 업로드
	public List<FileItem> uploadFiles(HttpServletRequest request, String biz_type) throws Exception;
	

}
