package kr.or.nextit.notice.service.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import kr.or.nextit.common.exception.NoSuchDataException;
import kr.or.nextit.common.exception.ServiceException;
import kr.or.nextit.common.jdbc.ConnectionProvider;
import kr.or.nextit.member.model.Member;
import kr.or.nextit.notice.dao.NoticeDao;
import kr.or.nextit.notice.model.Notice;
import kr.or.nextit.notice.service.NoticeService;

public class NoticeServiceImpl implements NoticeService {

	NoticeDao noticeDao = NoticeDao.getInstance();
	
	private static NoticeServiceImpl instance = new NoticeServiceImpl();

	private NoticeServiceImpl(){};
	
	public static NoticeServiceImpl getInstance() { //클래스메소드생성
		if(instance == null) {				// null체크
			instance = new NoticeServiceImpl();
		}
		return instance;
	}

	
	
	@Override
	public List<Notice> getNoticeList(Map<String, Object> paramMap) throws Exception {
		List<Notice> noticeList = null;
		Connection conn = null;

		try {
			conn = ConnectionProvider.getConnection();
			noticeList = noticeDao.selectNoticeList(conn, paramMap);
		} catch (Exception e) {
			e.printStackTrace();
			// throw e;

			throw new NoSuchDataException("해당 데이터가 없습니다.", e);
		} finally {
			// 커넥션 반납
			conn.close();
		}

		return noticeList;
	}

	@Override
	public Notice getNotice(int notice_seq_no) throws Exception {
		Notice notice = null;
		Connection conn = null;

		try {
			conn = ConnectionProvider.getConnection();
			notice = noticeDao.selectNotice(conn, notice_seq_no);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			// 커넥션 반납
			conn.close();
		}
		return notice;
	}

	@Override
	public int insertNotice(Notice notice) throws Exception {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			return noticeDao.insertNotice(conn, notice);

		} catch (Exception e) {
			// 에러
			e.printStackTrace();
			throw new ServiceException("등록시 에러 발생.", e);
		} 
	}

	@Override
	public int updateNotice(Notice notice) throws Exception {
		
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			return noticeDao.updateNotice(conn, notice);

		} catch (Exception e) {
			// 에러
			e.printStackTrace();
			throw new ServiceException("수정시 에러 발생.", e);
		}finally {
			conn.close();
		} 
	}

	@Override
	public int deleteNotice(int notice_seq_no) throws Exception {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			return noticeDao.deleteNotice(conn, notice_seq_no);

		} catch (Exception e) {
			// 에러
			e.printStackTrace();
			throw new ServiceException("삭제시 에러 발생.", e);
		} 
	}

}
