package com.bike.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.bike.db.JDBCTemplate;

public class BikeDAOImpl extends JDBCTemplate implements BikeDAO {

	@Override
	public int insert(String[] list) {
		
		Connection conn = getConnection();
		
		PreparedStatement pstmt = null;
		String sql = " INSERT INTO BIKE VALUES (?, ?, ?, ?, ?, ?, ?) ";
		int res = 0;
		int[] cnt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			for(int i=0; i<list.length; i++) {
				
				String[] dto = list[i].split("/");
				pstmt.setString(1, dto[0]);
				pstmt.setInt(2, Integer.parseInt(dto[1]));
				pstmt.setString(3, dto[2]);
				pstmt.setString(4, dto[3]);
				pstmt.setInt(5, Integer.parseInt(dto[4]));
				pstmt.setDouble(6, Double.parseDouble(dto[5]));
				pstmt.setDouble(7, Double.parseDouble(dto[6]));
				
				pstmt.addBatch();
			}
			
			cnt = pstmt.executeBatch();
			
			for(int i=0; i<cnt.length; i++) {
				
				// -2 : 성공
				// -3 : 실패
				
				if(cnt[i] == -2) {
					res++;
				}
			}
			
			if(res == list.length) {	// 파라미터 배열의 크기와 결과의 갯수가 같다면
				commit(conn);
			}
			
		} catch (SQLException e) {
			System.out.println("[ERROR] : BikeDAOImpl - insert 쿼리 실행 오류");
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(conn);
		}

		return res;
	}

}
