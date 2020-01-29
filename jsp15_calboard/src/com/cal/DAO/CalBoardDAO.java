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

	// 일정 추가
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
	
	public int multiDelete(String[] chks) {
		
		Connection conn = getConnection();
		
		String sql = " DELETE  "
		int res = 0;
		
		return res;
	}
}
