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

		// 5. db 종료
		try {
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("[ ERROR ] : MyDAO - selectList() db 종료 오류");
			e.printStackTrace();
		}

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
			rs = pstmt.executeQuery();
			
			pstmt.setInt(1, myNo);
			dto.setMyName(rs.getString("MYNAME"));
			dto.setMyTitle(rs.getString("MYTITLE"));
			dto.setMyContent(rs.getString("MYCONTENT"));
			dto.setMyDate(rs.getDate("MYDATE"));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

		return null;
	}

	public int insert(MyDTO dto) {
		return 0;
	}

	public int update(int myNo) {
		return 0;
	}

	public int delete(int myNo) {
		return 0;
	}
}
