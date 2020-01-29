package com.cal.DTO;

import java.util.Date;

public class CalBoardDTO {

	private int seq;
	private String id;
	private String title;
	private String content;
	private String mDate;
	private Date regDate;
	
	public CalBoardDTO() {
		
	}
	
	public CalBoardDTO(String id, String title, String content, String mDate) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.mDate = mDate;
	}

	public CalBoardDTO(int seq, String id, String title, String content, String mDate, Date regDate) {
		this.seq = seq;
		this.id = id;
		this.title = title;
		this.content = content;
		this.mDate = mDate;
		this.regDate = regDate;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getmDate() {
		return mDate;
	}

	public void setmDate(String mDate) {
		this.mDate = mDate;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "CalBoardDTO [seq=" + seq + ", id=" + id + ", title=" + title + ", content=" + content + ", mDate="
				+ mDate + ", regDate=" + regDate + "]";
	}
}
