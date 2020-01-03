package DTO;

import java.util.Date;

public class MyBoardDTO {

	private int myNo;
	private String myName;
	private String myTitle;
	private String myContent;
	private Date myDate;
	
	public MyBoardDTO() {
	}

	public MyBoardDTO(int myNo, String myName, String myTitle, String myContent, Date myDate) {
		this.myNo = myNo;
		this.myName = myName;
		this.myTitle = myTitle;
		this.myContent = myContent;
		this.myDate = myDate;
	}

	public int getMyNo() {
		return myNo;
	}

	public void setMyNo(int myNo) {
		this.myNo = myNo;
	}

	public String getMyName() {
		return myName;
	}

	public void setMyName(String myName) {
		this.myName = myName;
	}

	public String getMyTitle() {
		return myTitle;
	}

	public void setMyTitle(String myTitle) {
		this.myTitle = myTitle;
	}

	public String getMyContent() {
		return myContent;
	}

	public void setMyContent(String myContent) {
		this.myContent = myContent;
	}

	public Date getMyDate() {
		return myDate;
	}

	public void setMyDate(Date myDate) {
		this.myDate = myDate;
	}

	@Override
	public String toString() {
		return "MyBoardDTO [myNo=" + myNo + ", myName=" + myName + ", myTitle=" + myTitle + ", myContent=" + myContent
				+ ", myDate=" + myDate + "]";
	}
}
