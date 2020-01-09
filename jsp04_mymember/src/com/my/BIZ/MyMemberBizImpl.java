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
		return dao.idChk(id);
	}

	@Override
	public int insertUser(MyMemberDTO dto) {
		return dao.insertUser(dto);
	}

	@Override
	public MyMemberDTO selectOneUser(int myNo) {
		return dao.selectOneUser(myNo);
	}

	@Override
	public int updateUser(MyMemberDTO dto) {
		return dao.updateUser(dto);
	}

	@Override
	public int deleteUser(int myNo) {
		return dao.deleteUser(myNo);
	}

}
