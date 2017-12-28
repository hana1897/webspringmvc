package kr.or.nextit.common.file.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.or.nextit.common.file.model.FileItem;

@Mapper
public interface FileItemMapper {
	
	public List<FileItem> selectFileItemList(Map<String, Object> paramMap) throws Exception;
	
	public FileItem selectFileItem(Map<String, Object> paramMap) throws Exception;
	
	public int insertFileItem(FileItem fileItem) throws Exception;
	
	public int deleteFileItem(Map<String, Object> paramMap) throws Exception;
	
	public int updateDownloadCnt(Map<String, Object> paramMap) throws Exception;
}
