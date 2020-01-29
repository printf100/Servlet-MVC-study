package com.cal.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cal.DTO.CalBoardDTO;
import com.cal.db.JDBCTemplate;

public class CalBoardDAO extends JDBCTemplate {

	// 일정 추가하기
	public int insert(CalBoardDTO dto) {
		
		Connection conn = getConnection();
		
		PreparedStatement pstmt = null;
		String sql = " INSERT INTO CALBOARD VALUES (CALBOARDSEQ.NEXTVAL, ?, ?, ?, ?, SYSDATE) ";
		int res = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getContent());
			pstmt.setString(4, dto.getmDate());
			
			res = pstmt.executeUpdate();
			
			if(res > 0) {
				commit(conn);
			}
			
		} catch (SQLException e) {
			System.out.println("[ERROR] : CalBoardDAO - insert 쿼리 실행 오류");
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(conn);
		}
		
		return res;
	}
	
	// 해당 날짜의 일정 목록 가져오기
	public List<CalBoardDTO> selectList(String id, String yyyyMMdd) {
		
		Connection conn = getConnection();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = " SELECT * FROM CALBOARD WHERE ID = ? AND SUBSTR(MDATE, 1, 8) = ? ";
		
		List<CalBoardDTO> list = new ArrayList<CalBoardDTO>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, yyyyMMdd);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CalBoardDTO dto = new CalBoardDTO();
				
				dto.setSeq(rs.getInt("seq"));
				dto.setId(rs.getString("id"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setmDate(rs.getString("mDate"));
				dto.setRegDate(rs.getDate("regDate"));
				
				list.add(dto);
			}
			
		} catch (SQLException e) {
			System.out.println("[ERROR] : CalBoardDAO - selectList 쿼리 실행 오류");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
			close(conn);
		}
		
		return list;
	}
	
	// 캘린더 칸 내에 최대 3개까지 일정 보여주기
	public List<CalBoardDTO> getCalViewList(String id, String yyyyMM) {
		
		Connection conn = getConnection();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = " SELECT * " + 
				" FROM ( " + 
				"		SELECT (ROW_NUMBER() OVER(PARTITION BY SUBSTR(MDATE, 1, 8) ORDER BY MDATE)) RN, " + 
				"		SEQ, ID, TITLE, CONTENT, MDATE, REGDATE " + 
				"		FROM CALBOARD " + 
				"		WHERE ID = ? AND SUBSTR(MDATE, 1, 6) = ? " + 
				" ) " + 
				" WHERE RN BETWEEN 1 AND 3 ";
		
		List<CalBoardDTO> list = new ArrayList<CalBoardDTO>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, yyyyMM);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				CalBoardDTO dto = new CalBoardDTO();
				
				dto.setSeq(rs.getInt("seq"));
				dto.setId(rs.getString("id"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setmDate(rs.getString("mDate"));
				dto.setRegDate(rs.getDate("regDate"));
				
				list.add(dto);				
			}
		} catch (SQLException e) {
			System.out.println("[ERROR] : CalBoardDAO - getCalViewList 쿼리 실행 오류");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
			close(conn);
		}
		
		return list;
	}
}
