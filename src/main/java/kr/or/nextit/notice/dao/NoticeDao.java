package kr.or.nextit.notice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import kr.or.nextit.notice.model.Notice;

public class NoticeDao {
	
	// 싱글톤 패턴
	
		private static NoticeDao instance = new NoticeDao(); //클래스메소드생성
		
		private NoticeDao() {}
		
		public static NoticeDao getInstance() { //클래스메소드생성
			if(instance == null) {				// null체크
				instance = new NoticeDao();
			}
			return instance;
		}
	
	// 회원목록 조회
	public List<Notice> selectNoticeList(Connection conn, Map<String, Object> paramMap) throws Exception{ 
		
		List<Notice> noticeList = new ArrayList<>(); //상속관계임 부모List 자식 ArrayList
		
		StringBuffer query = new StringBuffer();
		
		query.append(" SELECT              ");
		query.append("     notice_seq_no   ");
		query.append("   , title           ");
		query.append("   , name            ");
		query.append("   , content         ");
		query.append("   , reg_date        ");
		query.append("   , uploadfile        ");
		query.append(" FROM TB_NOTICE WHERE 1=1");
		
		if(paramMap != null && !paramMap.isEmpty()) {
			if("title".equals(paramMap.get("searchType"))) {
				query.append(" AND title LIKE '%' || ? || '%' ");
			}else if("name".equals(paramMap.get("searchType"))) {
				query.append(" AND name LIKE '%' || ? || '%' ");
			}
		}

		query.append(" ORDER BY reg_date DESC ");
		
		PreparedStatement pstmt = conn.prepareStatement(query.toString());
		
		int i = 1;
		if(paramMap != null && !paramMap.isEmpty()) {
			if("title".equals(paramMap.get("searchType"))) {
				pstmt.setString(i++, (String)paramMap.get("searchWord"));
			}else if("name".equals(paramMap.get("searchType"))) {
				pstmt.setString(i++, (String)paramMap.get("searchWord"));
			}
		}
		
		ResultSet rs =pstmt.executeQuery();
		
		while(rs.next()){
			Notice notice = new Notice();	//Notice객체생성. Bean에다 정보담기
			notice.setNotice_seq_no(rs.getInt("notice_seq_no"));
			notice.setTitle(rs.getString("title"));
			notice.setName(rs.getString("name"));
			notice.setContent(rs.getString("content"));
			notice.setReg_date(rs.getString("reg_date"));
			notice.setUploadfile(rs.getString("uploadfile"));
			
			noticeList.add(notice);
		}
		
		return noticeList;
		
	}
	
	// 회원정보 조회
	public Notice selectNotice(Connection conn, int notice_seq_no) throws Exception{
		StringBuffer query = new StringBuffer();

		query.append(" SELECT              		  ");
		query.append("     notice_seq_no          ");
		query.append("   , title           		  ");
		query.append("   , name            		  ");
		query.append("   , content         		  ");
		query.append("   , reg_date        		  ");
		query.append("   , uploadfile        	  ");
		query.append(" FROM TB_NOTICE	  		  ");
		query.append(" WHERE notice_seq_no = ?    ");

		PreparedStatement pstmt = conn.prepareStatement(query.toString());

		pstmt.setInt(1, notice_seq_no);

		ResultSet rs = pstmt.executeQuery();

		Notice notice = null;
		if (rs.next()) {
			notice = new Notice();
			notice.setNotice_seq_no(rs.getInt("notice_seq_no"));
			notice.setTitle(rs.getString("title"));
			notice.setName(rs.getString("name"));
			notice.setContent(rs.getString("content"));
			notice.setReg_date(rs.getString("reg_date"));
			notice.setUploadfile(rs.getString("uploadfile"));
		}
		return notice;
		
	}
	
	
	// 회원 등록
	public int insertNotice(Connection conn, Notice notice) throws Exception{
		
		StringBuffer query = new StringBuffer();
		
		query.append("insert into tb_notice (		  ");
		query.append("     notice_seq_no              ");
		query.append("   , title           		      ");
		query.append("   , name            		      ");
		query.append("   , content         		      ");
		query.append("   , reg_date        		      ");
		query.append("   , uploadfile        	      ");
		query.append("	) values (                	  ");
		query.append("     SEQ_NOTICE_SEQ_NO.NEXTVAL  ");
		query.append("   , ?           		  	  	  ");
		query.append("   , ?            		  	  ");
		query.append("   , ?         		  	  	  ");
		query.append("   , SYSDATE        		      ");
		query.append("   , ?        	  		      ");
		query.append("	)                    	      ");
		
		PreparedStatement pstmt = conn.prepareStatement(query.toString());
		
		int i=1;
		pstmt.setString(i++, notice.getTitle());
		pstmt.setString(i++, notice.getName());
		pstmt.setString(i++, notice.getContent());
		pstmt.setString(i++, notice.getUploadfile());
		
		int updCnt = pstmt.executeUpdate();
		
		return updCnt;
		
	}
	
	// 회원 수정
	public int updateNotice(Connection conn, Notice notice) throws Exception{
		StringBuffer query = new StringBuffer();
		
		query.append("update tb_notice set     ");
		query.append("	  title = ?            ");
		query.append("	, name = ?             ");
		query.append("	, content = ?          ");
		query.append(" WHERE notice_seq_no = ? ");
		
		PreparedStatement pstmt = conn.prepareStatement(query.toString());
		
		// 바인딩
		int i=1;
		pstmt.setString(i++, notice.getTitle());
		pstmt.setString(i++, notice.getName());
		pstmt.setString(i++, notice.getContent());
		pstmt.setInt(i++, notice.getNotice_seq_no());
		
		int updCnt = pstmt.executeUpdate();
		
		return updCnt;
		
	}
	
	// 회원 삭제
	public int deleteNotice(Connection conn, int notice_seq_no) throws Exception{
		StringBuffer query = new StringBuffer();
		
		query.append(" DELETE FROM TB_NOTICE WHERE notice_seq_no = ? ");
		
		PreparedStatement pstmt = conn.prepareStatement(query.toString());
		
		int i =1;
		pstmt.setInt(i++,  notice_seq_no);
		
		int updCnt = pstmt.executeUpdate();
		return updCnt;
		
	}
}
