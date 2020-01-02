package com.my.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.my.db.JDBCTemplate;
import com.my.dto.MyDTO;

public class MyDAO extends JDBCTemplate {

	public List<MyDTO> selectList() {

		// 1. driver 연결 , 2. 계정 연결
		Connection conn = getConnection();

		// 3. query 준비
		String sql = " SELECT MYNO, MYNAME, MYTITLE, MYCONTENT, MYDATE FROM MYBOARD ORDER BY MYNO DESC ";
		Statement stmt = null;
		List<MyDTO> list = new ArrayList<MyDTO>();

		ResultSet rs = null;

		try {
			stmt = conn.createStatement();

			// 4. query 실행 및 리턴
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				MyDTO dto = new MyDTO();

				dto.setMyNo(rs.getInt("MYNO"));
				dto.setMyName(rs.getString("MYNAME"));
				dto.setMyTitle(rs.getString("MYTITLE"));
				dto.setMyContent(rs.getString("MYCONTENT"));
				dto.setMyDate(rs.getDate("MYDATE"));

				list.add(dto);
			}

		} catch (SQLException e) {
			System.out.println("[ ERROR ] : MyDAO - selectList() 쿼리 실행 오류");
			e.printStackTrace();
		}

		close(rs);
		close(stmt);
		close(conn);

		return list;
	}

	public MyDTO selectOne(int myNo) {

		// 1. driver 연결 , 2. 계정 연결
		Connection conn = getConnection();

		// 3. query 준비
		String sql = " SELECT MYNO, MYNAME, MYTITLE, MYCONTENT, MYDATE FROM MYBOARD WHERE MYNO = ? ";
		PreparedStatement pstmt = null;
		MyDTO dto = new MyDTO();

		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, myNo);

			// 4. 실행 및 리턴
			rs = pstmt.executeQuery();
			rs.next();
			dto.setMyName(rs.getString("MYNAME"));
			dto.setMyTitle(rs.getString("MYTITLE"));
			dto.setMyContent(rs.getString("MYCONTENT"));
			dto.setMyDate(rs.getDate("MYDATE"));

		} catch (SQLException e) {
			System.out.println("[ ERROR ] : MyDAO - selectOne() 쿼리 실행 오류");
			e.printStackTrace();
		}

		close(rs);
		close(pstmt);
		close(conn);

		return dto;
	}

	public int insert(MyDTO dto) {

		// 1. driver 연결 , 2. 계정 연결
		Connection conn = getConnection();

		// 3. query 준비
		String sql = " INSERT INTO MYBOARD VALUES(MYSEQ.NEXTVAL, ?, ?, ?, SYSDATE) ";
		PreparedStatement pstmt = null;
		int result = 0;

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, dto.getMyName());
			pstmt.setString(2, dto.getMyTitle());
			pstmt.setString(3, dto.getMyContent());

			// 4. 실행 및 리턴
			result = pstmt.executeUpdate();

			if (result > 0) {
				commit(conn);
			}

		} catch (SQLException e) {
			System.out.println("[ ERROR ] : MyDAO - insert() 쿼리 실행 오류");
			e.printStackTrace();
		}

		close(pstmt);
		close(conn);

		return result;
	}

	public int update(MyDTO dto) {

		// 1. driver 연결 , 2. 계정 연결
		Connection conn = getConnection();

		// 3. query 준비
		String sql = " UPDATE MYBOARD SET MYNAME = ?, MYTITLE = ?, MYCONTENT = ? WHERE MYNO = ? ";
		PreparedStatement pstmt = null;
		int result = 0;

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, dto.getMyName());
			pstmt.setString(2, dto.getMyTitle());
			pstmt.setString(3, dto.getMyContent());
			pstmt.setInt(4, dto.getMyNo());

			result = pstmt.executeUpdate();

			if (result > 0) {
				commit(conn);
			}

		} catch (SQLException e) {
			System.out.println("[ ERROR ] : MyDAO - update() 쿼리 실행 오류");
			e.printStackTrace();
		}

		close(pstmt);
		close(conn);

		return result;
	}

	public int delete(int myNo) {

		// 1. driver 연결 , 2. 계정 연결
		Connection conn = getConnection();

		// 3. query 준비
		String sql = " DELETE FROM MYBOARD WHERE MYNO = ? ";
		PreparedStatement pstmt = null;
		int result = 0;

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, myNo);

			result = pstmt.executeUpdate();

			if (result > 0) {
				commit(conn);
			}

		} catch (SQLException e) {
			System.out.println("[ ERROR ] : MyDAO - delete() 쿼리 실행 오류");
			e.printStackTrace();
		}

		close(pstmt);
		close(conn);

		return result;
	}
}
