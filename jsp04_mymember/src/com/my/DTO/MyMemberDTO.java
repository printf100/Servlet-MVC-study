package com.my.DTO;

public class MyMemberDTO {

	private int myNo;
	private String id;
	private String pw;
	private String name;
	private String addr;
	private String phone;
	private String email;
	private String enabled;
	private String myRole;
	
	public MyMemberDTO() {}
	
	public MyMemberDTO(int myNo, String id, String pw, String name, String addr, String phone, String email,
			String enabled, String myRole) {
		this.myNo = myNo;
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.addr = addr;
		this.phone = phone;
		this.email = email;
		this.enabled = enabled;
		this.myRole = myRole;
	}

	public int getMyNo() {
		return myNo;
	}

	public void setMyNo(int myNo) {
		this.myNo = myNo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public String getMyRole() {
		return myRole;
	}

	public void setMyRole(String myRole) {
		this.myRole = myRole;
	}

	@Override
	public String toString() {
		return "MyMemberDTO [myNo=" + myNo + ", id=" + id + ", pw=" + pw + ", name=" + name + ", addr=" + addr
				+ ", phone=" + phone + ", email=" + email + ", enabled=" + enabled + ", myRole=" + myRole + "]";
	}
}
