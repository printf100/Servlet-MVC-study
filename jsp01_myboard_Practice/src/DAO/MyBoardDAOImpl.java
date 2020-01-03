package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DB.JDBCTemplate;
import DTO.MyBoardDTO;

public class MyBoardDAOImpl extends JDBCTemplate implements MyBoardDAO {

	@Override
	public List<MyBoardDTO> selectList() {

		Connection conn = getConnection();

		Statement stmt = null;
		ResultSet rs = null;
		List<MyBoardDTO> list = new ArrayList<MyBoardDTO>();

		String sql = " SELECT * FROM MYBOARD ORDER BY MYNO DESC ";

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				MyBoardDTO dto = new MyBoardDTO();

				dto.setMyNo(rs.getInt("MYNO"));
				dto.setMyName(rs.getString("MYNAME"));
				dto.setMyTitle(rs.getString("MYTITLE"));
				dto.setMyContent(rs.getString("MYCONTENT"));
				dto.setMyDate(rs.getDate("MYDATE"));

				list.add(dto);
			}

		} catch (SQLException e) {
			System.out.println("[ERROR] : MyBoardDAOImpl - selectList() 쿼리 실행 오류");
			e.printStackTrace();
		}

		close(rs);
		close(stmt);
		close(conn);

		return list;
	}

	@Override
	public MyBoardDTO selectOne(int myNo) {

		Connection conn = getConnection();

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MyBoardDTO dto = null;

		String sql = " SELECT * FROM MYBOARD WHERE MYNO = " + myNo;

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				dto = new MyBoardDTO();

				dto.setMyNo(myNo);
				dto.setMyName(rs.getString("MYNAME"));
				dto.setMyTitle(rs.getString("MYTITLE"));
				dto.setMyContent(rs.getString("MYCONTENT"));
				dto.setMyDate(rs.getDate("MYDATE"));
			}

		} catch (SQLException e) {
			System.out.println("[ERROR] : MyBoardDAOImpl - selectOne() 쿼리 실행 오류");
			e.printStackTrace();
		}

		close(rs);
		close(pstmt);
		close(conn);

		return dto;
	}

	@Override
	public int insert(MyBoardDTO dto) {
		
		Connection conn = getConnection();

		PreparedStatement pstmt = null;
		int res = 0;
		String sql = " INSERT INTO MYBOARD VALUES (MYSEQ.NEXTVAL, ?, ?, ?, SYSDATE) ";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getMyName());
			pstmt.setString(2, dto.getMyTitle());
			pstmt.setString(3, dto.getMyContent());
			
			res = pstmt.executeUpdate();
			
			if(res > 0) {
				commit(conn);
			}
			
		} catch (SQLException e) {
			System.out.println("[ERROR] : MyBoardDAOImpl - insert() 쿼리 실행 오류");
			e.printStackTrace();
		}
		
		close(pstmt);
		close(conn);
				
		return res;
	}

	@Override
	public int update(MyBoardDTO dto) {
		
		Connection conn = getConnection();

		PreparedStatement pstmt = null;
		int res = 0;
		String sql = " UPDATE MYBOARD SET MYNAME = ?, MYTITLE = ?, MYCONTENT = ? WHERE MYNO = ? ";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getMyName());
			pstmt.setString(2, dto.getMyTitle());
			pstmt.setString(3, dto.getMyContent());
			pstmt.setInt(4, dto.getMyNo());
			
			res = pstmt.executeUpdate();
			
			if(res > 0) {
				commit(conn);
			}
			
		} catch (SQLException e) {
			System.out.println("[ERROR] : MyBoardDAOImpl - update() 쿼리 실행 오류");
			e.printStackTrace();
		}
		
		close(pstmt);
		close(conn);
				
		return res;
	}

	@Override
	public int delete(int myNo) {

		Connection conn = getConnection();

		PreparedStatement pstmt = null;
		int res = 0;
		String sql = " DELETE FROM MYBOARD WHERE MYNO = " + myNo;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			res = pstmt.executeUpdate();
			
			if(res > 0) {
				commit(conn);
			}
			
		} catch (SQLException e) {
			System.out.println("[ERROR] : MyBoardDAOImpl - delete() 쿼리 실행 오류");
			e.printStackTrace();
		}
		
		close(pstmt);
		close(conn);
				
		return res;
	}

}
