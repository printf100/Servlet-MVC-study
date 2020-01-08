package com.md.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.md.DTO.MDBoardDTO;
import com.md.db.JDBCTemplate;

public class MDBoardDAOImpl extends JDBCTemplate implements MDBoardDAO {

	@Override
	public List<MDBoardDTO> selectList() {
		
		Connection conn = getConnection();
		
		Statement stmt = null;
		ResultSet rs = null;
		String sql = " SELECT SEQ, WRITER, TITLE, CONTENT, REGDATE FROM MDBOARD ORDER BY SEQ DESC ";
		
		List<MDBoardDTO> list = new ArrayList<MDBoardDTO>();
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				MDBoardDTO dto = new MDBoardDTO();
				
				dto.setSeq(rs.getInt("SEQ"));
				dto.setWriter(rs.getString("WRITER"));
				dto.setTitle(rs.getString("TITLE"));
				dto.setContent(rs.getString("CONTENT"));
				dto.setRegDate(rs.getDate("REGDATE"));
				
				list.add(dto);
			}
			
		} catch (SQLException e) {
			System.out.println("[ERROR] : MDDAOImpl - selectList 쿼리 실행 오류");
			e.printStackTrace();
		}
		
		close(rs);
		close(stmt);
		close(conn);
		
		return list;
	}

	@Override
	public MDBoardDTO selectOne(int seq) {
		
		Connection conn = getConnection();
		
		Statement stmt = null;
		ResultSet rs = null;
		String sql = " SELECT SEQ, WRITER, TITLE, CONTENT, REGDATE FROM MDBOARD WHERE SEQ = " + seq;
		
		MDBoardDTO dto = new MDBoardDTO();
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				dto.setSeq(rs.getInt("SEQ"));
				dto.setWriter(rs.getString("WRITER"));
				dto.setTitle(rs.getString("TITLE"));
				dto.setContent(rs.getString("CONTENT"));
				dto.setRegDate(rs.getDate("REGDATE"));
			}
			
		} catch (SQLException e) {
			System.out.println("[ERROR] : MDDAOImpl - selectOne 쿼리 실행 오류");
			e.printStackTrace();
		}
		
		close(rs);
		close(stmt);
		close(conn);
		
		return dto;
	}

	@Override
	public int insert(MDBoardDTO dto) {
		
		Connection conn = getConnection();
		
		PreparedStatement pstmt = null;
		int res = 0;
		String sql = " INSERT INTO MDBOARD VALUES (MDBOARDSEQ.NEXTVAL, ?, ?, ?, SYSDATE) ";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getWriter());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getContent());
			
			res = pstmt.executeUpdate();
			
			if(res > 0) {
				commit(conn);
			}
			
		} catch (SQLException e) {
			System.out.println("[ERROR] : MDDAOImpl - insert 쿼리 실행 오류");
			e.printStackTrace();
		}
		
		close(pstmt);
		close(conn);
		
		return res;
	}

	@Override
	public int update(MDBoardDTO dto) {
		
		Connection conn = getConnection();
		
		PreparedStatement pstmt = null;
		int res = 0;
		String sql = " UPDATE MDBOARD SET WRITER = ?, TITLE = ?, CONTENT = ? WHERE SEQ = ? ";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getWriter());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getContent());
			pstmt.setInt(4, dto.getSeq());
			
			res = pstmt.executeUpdate();
			
			if(res > 0) {
				commit(conn);
			}
			
		} catch (SQLException e) {
			System.out.println("[ERROR] : MDDAOImpl - update 쿼리 실행 오류");
			e.printStackTrace();
		}
		
		close(pstmt);
		close(conn);
		
		return res;	
	}

	@Override
	public int delete(int seq) {
		
		Connection conn = getConnection();
		
		PreparedStatement pstmt = null;
		int res = 0;
		String sql = " DELETE FROM MDBOARD WHERE SEQ = " + seq;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			res = pstmt.executeUpdate();
			
			if(res > 0) {
				commit(conn);
			}
			
		} catch (SQLException e) {
			System.out.println("[ERROR] : MDDAOImpl - delete 쿼리 실행 오류");
			e.printStackTrace();
		}
		
		close(pstmt);
		close(conn);
		
		return res;
	}

	@Override
	public int multiDelete(String[] chks) {
		
		Connection conn = getConnection();
		
		PreparedStatement pstmt = null;
		int res = 0;
		String sql = " DELETE FROM MDBOARD WHERE SEQ = ?";
		
		int[] cnt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			for(int i=0; i<chks.length; i++) {
				
				pstmt.setString(1, chks[i]);
				pstmt.addBatch();	// 메모리에 적재 후, executeBatch() 메소드가 호출될 때 한 번에 실행!
				
				System.out.println("삭제할 번호 : " + chks[i]);
			}
			
			cnt = pstmt.executeBatch();	// 메모리에 있던 쿼리를 한 번에 실행, int[]로 리턴 받음
			
			for(int i=0; i<cnt.length; i++) {
				
				// -2 : 성공
				// -3 : 실패
				
				if(cnt[i] == -2) {
					res++;
				}
			}
			
			if(res == chks.length) {	// 파라미터 배열의 크기와 결과의 갯수가 같다면
				commit(conn);
			}
			
		} catch (SQLException e) {
			System.out.println("[ERROR] : MDDAOImpl - multiDelete 쿼리 실행 오류");
			e.printStackTrace();
		}
		
		close(pstmt);
		close(conn);
		
		return res;
	}
}
