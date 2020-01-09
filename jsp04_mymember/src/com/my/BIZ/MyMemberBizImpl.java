package com.my.BIZ;

import java.util.List;

import com.my.DAO.MyMemeberDAO;
import com.my.DAO.MyMemeberDAOImpl;
import com.my.DTO.MyMemberDTO;

public class MyMemberBizImpl implements MyMemberBiz {
	
	private MyMemeberDAO dao = new MyMemeberDAOImpl();

	@Override
	public List<MyMemberDTO> selectList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MyMemberDTO> selecteEnabled() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateRole(int myNo, String myRole) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	// 로그인
	@Override
	public MyMemberDTO login(String id, String pw) {
		
		return dao.login(id, pw);
	}

	@Override
	public MyMemberDTO idChk(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertUser(MyMemberDTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public MyMemberDTO selectOneUser(int myNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateUser(MyMemberDTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteUser(int myNo) {
		// TODO Auto-generated method stub
		return 0;
	}

}
