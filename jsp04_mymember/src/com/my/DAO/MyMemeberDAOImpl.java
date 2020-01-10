package com.my.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.my.DTO.MyMemberDTO;
import com.my.db.JDBCTemplate;

public class MyMemeberDAOImpl extends JDBCTemplate implements MyMemeberDAO {

	// 관리자(ADMIN) 기능
	
	// 회원 전체 정보 (탈퇴 회원 포함)
	@Override
	public List<MyMemberDTO> selectList() {
		
		Connection conn = getConnection();
		
		Statement stmt = null;
		ResultSet rs = null;
		String sql = " SELECT * FROM MYMEMBER ORDER BY MYNO DESC ";
		
		List<MyMemberDTO> list = new ArrayList<MyMemberDTO>();
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				MyMemberDTO dto = new MyMemberDTO();
				
				dto.setMyNo(rs.getInt("MYNO"));
				dto.setId(rs.getString("ID"));
				dto.setPw(rs.getString("PW"));
				dto.setName(rs.getString("NAME"));
				dto.setAddr(rs.getString("ADDR"));
				dto.setPhone(rs.getString("PHONE"));
				dto.setEmail(rs.getString("EMAIL"));
				dto.setEnabled(rs.getString("ENABLED"));
				dto.setMyRole(rs.getString("MYROLE"));
				
				list.add(dto);
			}
			
		} catch (SQLException e) {
			System.out.println("[ERROR] : MyMemeberDAOImpl - selectList 쿼리 실행 오류");
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt);
			close(conn);
		}
		
		return list;
	}

	// 회원 전체 정보 (가입된 회원들만) - ENABLED = 'Y'
	@Override
	public List<MyMemberDTO> selecteEnabled() {

		Connection conn = getConnection();
		
		Statement stmt = null;
		ResultSet rs = null;
		String sql = " SELECT * FROM MYMEMBER WHERE ENABLED='Y' ORDER BY MYNO DESC ";
		
		List<MyMemberDTO> list = new ArrayList<MyMemberDTO>();
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				MyMemberDTO dto = new MyMemberDTO();
				
				dto.setMyNo(rs.getInt("MYNO"));
				dto.setId(rs.getString("ID"));
				dto.setPw(rs.getString("PW"));
				dto.setName(rs.getString("NAME"));
				dto.setAddr(rs.getString("ADDR"));
				dto.setPhone(rs.getString("PHONE"));
				dto.setEmail(rs.getString("EMAIL"));
				dto.setEnabled(rs.getString("ENABLED"));
				dto.setMyRole(rs.getString("MYROLE"));
				
				list.add(dto);
			}
			
		} catch (SQLException e) {
			System.out.println("[ERROR] : MyMemeberDAOImpl - selecteEnabled 쿼리 실행 오류");
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt);
			close(conn);
		}
		
		return list;
	}

	// 회원 등급 조정
	@Override
	public int updateRole(int myNo, String myRole) {
		
		Connection conn = getConnection();
		
		PreparedStatement pstmt = null;
		String sql = " UPDATE MYMEMBER SET MYROLE = ? WHERE MYNO = ? ";
		int res = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, myRole);
			pstmt.setInt(2, myNo);
			
			res = pstmt.executeUpdate();
			
			if(res > 0) {
				commit(conn);
			}
			
		} catch (SQLException e) {
			System.out.println("[ERROR] : MyMemeberDAOImpl - updateRole 쿼리 실행 오류");
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(conn);
		}
		
		return res;
	}

	
	
	// 사용자(USER) 기능
	
	// 로그인
	@Override
	public MyMemberDTO login(String id, String pw) {
		
		Connection conn = getConnection();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MyMemberDTO dto = null;	// new MyMemberDTO()로 객체 생성이 되었을 때는 null이 아님!!
		String sql = " SELECT * FROM MYMEMBER WHERE ID = ? AND PW = ? AND ENABLED = ? ";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			pstmt.setString(3, "Y");
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				dto = new MyMemberDTO();
				
				dto.setMyNo(rs.getInt("MYNO"));
				dto.setId(id);
				dto.setPw(pw);
				dto.setName(rs.getString("NAME"));
				dto.setAddr(rs.getString("ADDR"));
				dto.setPhone(rs.getString("PHONE"));
				dto.setEmail(rs.getString("EMAIL"));
				dto.setEnabled(rs.getString("ENABLED"));
				dto.setMyRole(rs.getString("MYROLE"));
			}
			
		} catch (SQLException e) {
			System.out.println("[ERROR] : MyMemeberDAOImpl - login 쿼리 실행 오류");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
			close(conn);
		}
		
		return dto;
	}

	@Override
	public MyMemberDTO idChk(String id) {
		
		Connection conn = getConnection();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MyMemberDTO dto = null;	// 컨트롤러에서 null 체크를 할거기 때문에 null로 셋팅
								// new로 객체 생성해둘 거면 컨트롤러에서 if(dto.getId() != null) 이라고 해야함
		String sql = " SELECT * FROM MYMEMBER WHERE ID = ? ";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				dto = new MyMemberDTO();
				
				dto.setMyNo(rs.getInt("MYNO"));
				dto.setId(id);
				dto.setPw(rs.getString("PW"));
				dto.setName(rs.getString("NAME"));
				dto.setAddr(rs.getString("ADDR"));
				dto.setPhone(rs.getString("PHONE"));
				dto.setEmail(rs.getString("EMAIL"));
				dto.setEnabled(rs.getString("ENABLED"));
				dto.setMyRole(rs.getString("MYROLE"));
			}
			
		} catch (SQLException e) {
			System.out.println("[ERROR] : MyMemeberDAOImpl - idChk 쿼리 실행 오류");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
			close(conn);
		}
		
		return dto;
	}

	// 회원가입
	@Override
	public int insertUser(MyMemberDTO dto) {
		
		Connection conn = getConnection();
		
		PreparedStatement pstmt = null;
		String sql = " INSERT INTO MYMEMBER VALUES (MYMEMBERSEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, 'Y', ?) ";
		int res = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPw());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getAddr());
			pstmt.setString(5, dto.getPhone());
			pstmt.setString(6, dto.getEmail());
			pstmt.setString(7, dto.getMyRole());
			
			res = pstmt.executeUpdate();
			
			if(res > 0) {
				commit(conn);
			}
			
		} catch (SQLException e) {
			System.out.println("[ERROR] : MyMemeberDAOImpl - insertUser 쿼리 실행 오류");
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(conn);
		}
		
		return res;
	}

	// 내 정보
	@Override
	public MyMemberDTO selectOneUser(int myNo) {
		
		Connection conn = getConnection();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = " SELECT * FROM MYMEMBER WHERE MYNO = ? ";
		
		MyMemberDTO dto = new MyMemberDTO();

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, myNo);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				dto.setMyNo(rs.getInt("MYNO"));
				dto.setId(rs.getString("ID"));
				dto.setPw(rs.getString("PW"));
				dto.setName(rs.getString("NAME"));
				dto.setAddr(rs.getString("ADDR"));
				dto.setPhone(rs.getString("PHONE"));
				dto.setEmail(rs.getString("EMAIL"));
				dto.setEnabled(rs.getString("ENABLED"));
				dto.setMyRole(rs.getString("MYROLE"));
			}
			
		} catch (SQLException e) {
			System.out.println("[ERROR] : MyMemeberDAOImpl - selectOneUser 쿼리 실행 오류");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
			close(conn);
		}
		
		return dto;
	}

	// 내 정보 수정
	@Override
	public int updateUser(MyMemberDTO dto) {
		
		Connection conn = getConnection();
		
		PreparedStatement pstmt = null;
		String sql = " UPDATE MYMEMBER SET PW=?, NAME=?, ADDR=?, PHONE=?, EMAIL=? WHERE MYNO = ? ";
		int res = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getPw());
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getAddr());
			pstmt.setString(4, dto.getPhone());
			pstmt.setString(5, dto.getEmail());
			pstmt.setInt(6, dto.getMyNo());
			
			res = pstmt.executeUpdate();
			
			if(res > 0) {
				commit(conn);
			}
			
		} catch (SQLException e) {
			System.out.println("[ERROR] : MyMemeberDAOImpl - updateUser 쿼리 실행 오류");
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(conn);
		}
		
		return res;
	}

	@Override
	public int deleteUser(int myNo) {
		
		Connection conn = getConnection();
		
		PreparedStatement pstmt = null;
		String sql = " UPDATE MYMEMBER SET ENABLED='N' WHERE MYNO = ? ";
		int res = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, myNo);
			
			res = pstmt.executeUpdate();
			
			if(res > 0) {
				commit(conn);
			}
			
		} catch (SQLException e) {
			System.out.println("[ERROR] : MyMemeberDAOImpl - deleteUser 쿼리 실행 오류");
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(conn);
		}
		
		return res;
	}

}
