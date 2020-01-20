package com.answer.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.answer.DTO.AnswerBoardDTO;
import com.answer.db.JDBCTemplate;

public class AnswerBoardDAOImpl extends JDBCTemplate implements AnswerBoardDAO {

	@Override
	public List<AnswerBoardDTO> selectList() {
		
		Connection conn = getConnection();
		
		Statement stmt = null;
		ResultSet rs = null;
		String sql = " SELECT * FROM ANSWERBOARD ORDER BY GROUPNO DESC, GROUPORDER ";
		
		List<AnswerBoardDTO> list = new ArrayList<AnswerBoardDTO>();
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				AnswerBoardDTO dto = new AnswerBoardDTO();
				
				dto.setBoardNo(rs.getInt("BOARDNO"));
				dto.setGroupNo(rs.getInt("GROUPNO"));
				dto.setGroupOrder(rs.getInt("GROUPORDER"));
				dto.setTitleTab(rs.getInt("TITLETAB"));
				dto.setTitle(rs.getString("TITLE"));
				dto.setContent(rs.getString("CONTENT"));
				dto.setWriter(rs.getString("WRITER"));
				dto.setRegDate(rs.getDate("REGDATE"));
				
				list.add(dto);
			}
			
		} catch (SQLException e) {
			System.out.println("[ERROR] : AnswerBoardDAOImpl - selectList 쿼리 실행 오류");
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt);
			close(conn);
		}
		
		return list;
	}

	@Override
	public AnswerBoardDTO selectOne(int boardNo) {
		
		Connection conn = getConnection();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = " SELECT * FROM ANSWERBOARD WHERE BOARDNO = ? ";
		
		AnswerBoardDTO dto = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				dto = new AnswerBoardDTO();
				
				dto.setBoardNo(rs.getInt("BOARDNO"));
				dto.setGroupNo(rs.getInt("GROUPNO"));
				dto.setGroupOrder(rs.getInt("GROUPORDER"));
				dto.setTitleTab(rs.getInt("TITLETAB"));
				dto.setTitle(rs.getString("TITLE"));
				dto.setContent(rs.getString("CONTENT"));
				dto.setWriter(rs.getString("WRITER"));
				dto.setRegDate(rs.getDate("REGDATE"));
			}
			
		} catch (SQLException e) {
			System.out.println("[ERROR] : AnswerBoardDAOImpl - selectOne 쿼리 실행 오류");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
			close(conn);
		}
		
		return dto;
	}

	// 일반 글 작성
	@Override
	public int insert(AnswerBoardDTO dto) {
		
		Connection conn = getConnection();
		
		PreparedStatement pstmt = null;
		String sql = " INSERT INTO ANSWERBOARD VALUES (BOARDNOSEQ.NEXTVAL, GROUPNOSEQ.NEXTVAL, 1, 0, ?, ?, ?, SYSDATE) ";
		int res = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContent());
			pstmt.setString(3, dto.getWriter());
			
			res = pstmt.executeUpdate();
			
			if(res > 0) {
				commit(conn);
			}
			
		} catch (SQLException e) {
			System.out.println("[ERROR] : AnswerBoardDAOImpl - insert 쿼리 실행 오류");
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(conn);
		}
		
		return res;
	}

	// 답글 작성 전에 부모글과 그룹번호가 같고 부모 그룹순서보다 더 큰 글들의 그룹순서를 +1 해주기
	@Override
	public boolean updateOrder(int boardNo) {
		
		Connection conn = getConnection();
		
		PreparedStatement pstmt = null;
		boolean res = false;
		String sql = " UPDATE ANSWERBOARD SET GROUPORDER = GROUPORDER + 1 " + 
				" WHERE GROUPNO = (SELECT GROUPNO FROM ANSWERBOARD WHERE BOARDNO = ?)"  + 
				" AND GROUPORDER > (SELECT GROUPORDER FROM ANSWERBOARD WHERE BOARDNO = ?) ";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardNo);
			pstmt.setInt(2, boardNo);
			
			pstmt.executeUpdate();
			res = true;
			
			if(res) {
				commit(conn);
			}
			
		} catch (SQLException e) {
			res = false;
			System.out.println("[ERROR] : AnswerBoardDAOImpl - updateOrder 쿼리 실행 오류");
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(conn);
		}
		
		return res;
	}
	
	// 답글 작성
	@Override
	public int insertAnswer(AnswerBoardDTO dto) {

		Connection conn = getConnection();
		
		PreparedStatement pstmt = null;
		String sql = " INSERT INTO ANSWERBOARD VALUES (BOARDNOSEQ.NEXTVAL, "
				+ " (SELECT GROUPNO FROM ANSWERBOARD WHERE BOARDNO = ?), "
				+ " (SELECT GROUPORDER FROM ANSWERBOARD WHERE BOARDNO = ?) + 1, "
				+ " (SELECT TITLETAB FROM ANSWERBOARD WHERE BOARDNO = ?) + 1, "
				+ " ?, ?, ?, SYSDATE) ";
		int res = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, dto.getBoardNo());
			pstmt.setInt(2, dto.getBoardNo());
			pstmt.setInt(3, dto.getBoardNo());
			
			pstmt.setString(4, dto.getTitle());
			pstmt.setString(5, dto.getContent());
			pstmt.setString(6, dto.getWriter());
			
			res = pstmt.executeUpdate();
			
			if(res > 0) {
				commit(conn);
			}
			
		} catch (SQLException e) {
			System.out.println("[ERROR] : AnswerBoardDAOImpl - insertAnswer 쿼리 실행 오류");
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(conn);
		}
		
		return res;
	}

	@Override
	public int update(AnswerBoardDTO dto) {

		Connection conn = getConnection();
		
		PreparedStatement pstmt = null;
		String sql = " UPDATE ANSWERBOARD SET TITLE = ?, CONTENT = ?, WRITER = ? WHERE BOARDNO = ? ";
		int res = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContent());
			pstmt.setString(3, dto.getWriter());
			pstmt.setInt(4, dto.getBoardNo());
			
			res = pstmt.executeUpdate();
			
			if(res > 0) {
				commit(conn);
			}
			
		} catch (SQLException e) {
			System.out.println("[ERROR] : AnswerBoardDAOImpl - update 쿼리 실행 오류");
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(conn);
		}
		
		return res;
	}

	// 글 삭제 전에 답글이 있는지 체크 (답글 갯수 출력)
	@Override
	public int delCheck(int boardNo) {
		
		Connection conn = getConnection();
		
		PreparedStatement pstmt = null;
		
		// 같은 그룹번호 내에서 내 그룹순서보다 하나 크고, 내 제목탭보다 큰 글이 있으면 삭제 불가능
		String sql = " SELECT COUNT(*) FROM ANSWERBOARD " + 
				" WHERE GROUPNO IN (SELECT GROUPNO FROM ANSWERBOARD WHERE BOARDNO = ?) " +
				" AND GROUPORDER = (SELECT GROUPORDER FROM ANSWERBOARD WHERE BOARDNO = ?) + 1 " +
				" AND TITLETAB > (SELECT TITLETAB FROM ANSWERBOARD WHERE BOARDNO = ?) ";
		ResultSet rs = null;
		int res = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardNo);
			pstmt.setInt(2, boardNo);
			pstmt.setInt(3, boardNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				res = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			System.out.println("[ERROR] : AnswerBoardDAOImpl - delCheck 쿼리 실행 오류");
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(conn);
		}
		
		return res;
	}

	@Override
	public int delete(int boardNo) {
		
		Connection conn = getConnection();
		
		PreparedStatement pstmt = null;
		String sql = " DELETE FROM ANSWERBOARD WHERE BOARDNO = ? ";
		int res = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardNo);
			
			res = pstmt.executeUpdate();
			
			if(res > 0) {
				commit(conn);
			}
			
		} catch (SQLException e) {
			System.out.println("[ERROR] : AnswerBoardDAOImpl - delete 쿼리 실행 오류");
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(conn);
		}
		
		return res;
	}

	// 글 삭제 후 GROUPORDER -1
	@Override
	public void delUpdateOrder(int boardNo) {
		
		Connection conn = getConnection();
		
		PreparedStatement pstmt = null;
		String sql = " UPDATE ANSWERBOARD " + 
				" SET GROUPORDER = GROUPORDER - 1 " + 
				" WHERE GROUPNO = (SELECT GROUPNO FROM ANSWERBOARD WHERE BOARDNO = ?) " + 
				" AND GROUPORDER > (SELECT GROUPORDER FROM ANSWERBOARD WHERE BOARDNO = ?) ";
		int res = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardNo);
			pstmt.setInt(2, boardNo);
			
			res = pstmt.executeUpdate();
			
			if(res > 0) {
				commit(conn);
			}
			
		} catch (SQLException e) {
			System.out.println("[ERROR] : AnswerBoardDAOImpl - delUpdateOrder 쿼리 실행 오류");
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(conn);
		}
	}

}
