package com.my.BIZ;

import java.util.List;

import com.my.DTO.MyMemberDTO;

public interface MyMemberBiz {

	// 관리자(ADMIN) 기능
	
	// 1. 회원 전체 정보 (탈퇴 회원 포함)
	public List<MyMemberDTO> selectList();
	
	// 2. 회원 전체 정보 (가입된 회원들만) - ENABLED = 'Y'
	public List<MyMemberDTO> selecteEnabled();
	
	// 3. 회원 등급 조정
	public int updateRole(int myNo, String myRole);
	

	
	// 사용자(USER) 기능
	
	// 1. 로그인
	public MyMemberDTO login(String id, String pw);
	
	// 2. 회원 가입
	// 2-1. 아이디 증복 체크
	public MyMemberDTO idChk(String id);
	// 2-2. 회원가입
	public int insertUser(MyMemberDTO dto);
	
	// 3. 내 정보 조회
	public MyMemberDTO selectOneUser(int myNo);
	
	// 4. 내 정보 수정
	public int updateUser(MyMemberDTO dto);
	
	// 5. 회원 탈퇴 - UPDATE ENABLED = 'N'
	public int deleteUser(int myNo);
}
