package member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import member.model.vo.Member;

public class MemberDAO {

	public Member selectCheckLogin(Connection conn, Member member) {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			String query = "SELECT * FROM MEMBER_TBL WHERE MEMBER_ID = ? AND MEMBER_PW = ?";
	//		String query = "SELECT COUNT(*) FROM MEMBER_TBL WHERE MEMBER_ID = ?";
			Member mOne = null;
			
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, member.getMemberId());
				pstmt.setString(2, member.getMemberPw());
				rset = pstmt.executeQuery();  // 누락 주의, 결과 값 받기 주의
				if(rset.next()) {
					mOne = rsetToMember(rset);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					rset.close();
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return mOne;
		}

	public Member selectOneById(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM MEMBER_TBL WHERE MEMBER_ID = ?";
		Member mOne = null;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery();  // 누작주의 결과값 받기 주의
			if (rset.next()) {
				mOne = rsetToMember(rset);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return mOne;
	}

	public int insertMember(Connection conn, Member member) {
		String query = "INSERT INTO MEMBER_TBL VALUES(?,?,?,?,?,?,?,?,?,DEFAULT,DEFAULT,DEFAULT)";
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPw());
			pstmt.setString(3, member.getMemberName());
			pstmt.setInt(4, member.getMemberAge());
			pstmt.setString(5, member.getMemberGender());
			pstmt.setString(6, member.getMemberEmail());
			pstmt.setString(7, member.getMemberPhone());
			pstmt.setString(8, member.getMemberAddress());
			pstmt.setString(9, member.getMemberHobby());
			result = pstmt.executeUpdate(); 
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public int updateMember(Connection conn, Member member) {
		String query = "UPDATE MEMBER_TBL SET MEMBER_PW = ?, MEMBER_GENDER = ?, MEMBER_EMAIL = ?, MEMBER_PHONE = ?, MEMBER_ADDRESS = ?, MEMBER_HOBBY = ?, UPDATE_DATE = DEFAULT WHERE MEMBER_ID = ?";
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getMemberPw());
			pstmt.setString(2, member.getMemberGender());
			pstmt.setString(3, member.getMemberEmail());
			pstmt.setString(4, member.getMemberPhone());
			pstmt.setString(5, member.getMemberAddress());
			pstmt.setString(6, member.getMemberHobby());
			pstmt.setString(7, member.getMemberId());
			result = pstmt.executeUpdate(); 
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public int deleteMember(Connection conn, String memberId) {
		String query = "DELETE FROM MEMBER_TBL WHERE MEMBER_ID = ?";
		PreparedStatement pstmt = null;
		int result = 0;
		
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	private Member rsetToMember(ResultSet rset) throws SQLException {  // 에러 54번줄에서 처리해 주라
		Member member = new Member();
		// 보기 쉽게 컬럼명으로 적는게 좋음
		member.setMemberId(rset.getString(1));
		member.setMemberPw(rset.getString(2));
		member.setMemberName(rset.getString(3));
		member.setMemberAge(rset.getInt("MEMBER_AGE"));
		member.setMemberGender(rset.getString(5));
		member.setMemberEmail(rset.getString(6));
		member.setMemberPhone(rset.getString("MEMBER_PHONE"));
		member.setMemberAddress(rset.getString(8));
		member.setMemberHobby(rset.getString("MEMBER_HOBBY"));
		member.setMemberDate(rset.getTimestamp("MEMBER_DATE"));
		member.setUpdateDate(rset.getTimestamp(11));
		member.setMemberYn(rset.getString("MEMBER_YN"));
		return member;
	}
}
