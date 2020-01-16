package com.mvc.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mvc.DTO.MVCBoardDTO;
import static com.mvc.db.JDBCTemplate.*;

public class MVCBoardDAOImpl implements MVCBoardDAO {

	@Override
	public List<MVCBoardDTO> selectList() {
		
		Connection conn = getConnection();
		
		Statement stmt = null;
		ResultSet rs = null;
		String sql = SELECT_LIST_SQL;
		
		List<MVCBoardDTO> list = new ArrayList<MVCBoardDTO>();
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				MVCBoardDTO dto = new MVCBoardDTO();
				
				dto.setSeq(rs.getInt("SEQ"));
				dto.setWriter(rs.getString("WRITER"));
				dto.setTitle(rs.getString("TITLE"));
				dto.setContent(rs.getString("CONTENT"));
				dto.setRegDate(rs.getDate("REGDATE"));
				
				list.add(dto);
			}

		} catch (SQLException e) {
			System.out.println("[ERROR] : MVCBoardDAOImpl - selectList 쿼리실행 오류");
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt);
			close(conn);
		}
		
		return list;
	}

	@Override
	public MVCBoardDTO selectOne(int seq) {
		
		Connection conn = getConnection();
		
		Statement stmt = null;
		ResultSet rs = null;
		String sql = SELECT_ONE_SQL + seq;
		
		MVCBoardDTO dto = new MVCBoardDTO();
		
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
			System.out.println("[ERROR] : MVCBoardDAOImpl - selectOne 쿼리실행 오류");
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt);
			close(conn);
		}
		
		return dto;
	}

	@Override
	public int insert(MVCBoardDTO dto) {
		
		Connection conn = getConnection();
		
		PreparedStatement pstmt = null;
		int res = 0;
		String sql = INSERT_SQL;
		
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
			System.out.println("[ERROR] : MVCBoardDAOImpl - insert 쿼리실행 오류");
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(conn);
		}
		
		return res;
	}

	@Override
	public int update(MVCBoardDTO dto) {

		Connection conn = getConnection();
		
		PreparedStatement pstmt = null;
		int res = 0;
		String sql = UPDATE_SQL;
		
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
			System.out.println("[ERROR] : MVCBoardDAOImpl - update 쿼리실행 오류");
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(conn);
		}
		
		return res;
	}

	@Override
	public int delete(int seq) {
		
		Connection conn = getConnection();
		
		PreparedStatement pstmt = null;
		int res = 0;
		String sql = DELETE_SQL + seq;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			res = pstmt.executeUpdate();
			
			if(res > 0) {
				commit(conn);
			}
			
		} catch (SQLException e) {
			System.out.println("[ERROR] : MVCBoardDAOImpl - update 쿼리실행 오류");
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(conn);
		}
		
		return res;
	}

	@Override
	public boolean multiDelete(String[] seqs) {
		
		Connection conn = getConnection();
		
		PreparedStatement pstmt = null;
		int res = 0;
		String sql = DELETE_SQL + "?";
		boolean r = false;
		
		int[] cnt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			for(int i=0; i<seqs.length; i++) {
				
				pstmt.setString(1, seqs[i]);
				pstmt.addBatch();
				
				System.out.println("삭제할 번호 : " + seqs[i]);
			}
			
			cnt = pstmt.executeBatch();
			
			for(int i=0; i<cnt.length; i++) {
				
				// -2 : 성공
				// -3 : 실패
				
				if(cnt[i] == -2) {
					res++;
				}
			}
			
			if(res == seqs.length) {
				commit(conn);
				r = true;
			} else {
				rollback(conn);
				res = 0;
				r = false;
			}
			
		} catch (SQLException e) {
			System.out.println("[ERROR] : MVCBoardDAOImpl - multiDelete 쿼리 실행 오류");
			e.printStackTrace();
			r = false;
		} finally {
			close(pstmt);
			close(conn);
		}
		
		return r;
	}
}
