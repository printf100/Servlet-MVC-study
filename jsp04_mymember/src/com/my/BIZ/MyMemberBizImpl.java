package com.my.BIZ;

import java.util.List;

import com.my.DAO.MyMemeberDAO;
import com.my.DAO.MyMemeberDAOImpl;
import com.my.DTO.MyMemberDTO;

public class MyMemberBizImpl implements MyMemberBiz {
	
	private MyMemeberDAO dao = new MyMemeberDAOImpl();

	@Override
	public List<MyMemberDTO> selectList() {
		return dao.selectList();
	}

	@Override
	public List<MyMemberDTO> selecteEnabled() {
		return dao.selecteEnabled();
	}

	@Override
	public int updateRole(int myNo, String myRole) {
		return dao.updateRole(myNo, myRole);
	}

	
	// 로그인
	@Override
	public MyMemberDTO login(String id, String pw) {
		return dao.login(id, pw);
	}

	@Override
	public MyMemberDTO idChk(String id) {
		return null;
	}

	@Override
	public int insertUser(MyMemberDTO dto) {
		return 0;
	}

	@Override
	public MyMemberDTO selectOneUser(int myNo) {
		return null;
	}

	@Override
	public int updateUser(MyMemberDTO dto) {
		return 0;
	}

	@Override
	public int deleteUser(int myNo) {
		return 0;
	}

}
