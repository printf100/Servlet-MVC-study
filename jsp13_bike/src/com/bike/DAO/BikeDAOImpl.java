package com.bike.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.bike.DTO.BikeDTO;
import com.bike.db.JDBCTemplate;

public class BikeDAOImpl extends JDBCTemplate implements BikeDAO {

	@Override
	public int insert(List<BikeDTO> bikes) {
		
		Connection conn = getConnection();
		
		PreparedStatement pstmt = null;
		String sql = " INSERT INTO BIKE VALUES (?, ?, ?, ?, ?, ?, ?) ";
		int res = 0;
		int[] cnt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			for(int i=0; i<bikes.size(); i++) {
				
				pstmt.setString(1, bikes.get(i).getADDR_GU());
				pstmt.setInt(2, bikes.get(i).getCONTENT_ID());
				pstmt.setString(3, bikes.get(i).getCONTENT_NAME());
				pstmt.setString(4, bikes.get(i).getNEW_ADDR());
				pstmt.setInt(5, bikes.get(i).getCRADLE_COUNT());
				pstmt.setDouble(6, bikes.get(i).getLONGITUDE());
				pstmt.setDouble(7, bikes.get(i).getLATITUDE());
				
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
			
			if(res == bikes.size()) {	// 파라미터 배열의 크기와 결과의 갯수가 같다면
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

	@Override
	public int delete() {
		
		Connection conn = getConnection();
		
		PreparedStatement pstmt = null;
		String sql = " DELETE FROM BIKE ";
		int res = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			res = pstmt.executeUpdate();
			
			if(res > 0) {
				commit(conn);
			}
			
		} catch (SQLException e) {
			System.out.println("[ERROR] : BikeDAOImpl - delete 쿼리 실행 오류");
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(conn);
		}		
		
		return res;
	}

}
