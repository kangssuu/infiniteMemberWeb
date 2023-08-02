package notice.model.service;

import java.sql.Connection;
import java.util.List;

import common.JDBCTemplate;
import notice.model.dao.NoticeDAO;
import notice.model.vo.Notice;

public class NoticeService {
	
	private NoticeDAO nDao;
	private JDBCTemplate jdbcTemplate;
	
	public NoticeService() {
		nDao = new NoticeDAO();
		jdbcTemplate = JDBCTemplate.getInstance();  // JDBCTemplate은 싱글톤 패턴때문에 new 못 씀
	}
	
	public int insertNotice(Notice notice) {
		Connection conn = jdbcTemplate.createConnectoin();
		int result = nDao.insertNotice(conn, notice);
		if(result > 0) {
			jdbcTemplate.commit(conn);
		}else {
			jdbcTemplate.rollback(conn);
		}
		jdbcTemplate.close(conn);
		return result;
	}

	public List<Notice> selectNoticeList() {
		Connection conn = jdbcTemplate.createConnectoin();
		List<Notice> nList = nDao.selectNotionList(conn);
		return nList;
	}

	public Notice selectOneByNo(int noticeNo) {
		Connection conn = jdbcTemplate.createConnectoin();
		Notice notice = nDao.selectOneByNo(conn, noticeNo);
		jdbcTemplate.close(conn);
		return notice;
	}

}
