package com.mvc.DTO;

import java.util.Date;

public class MVCBoardDTO {

	private int seq;
	private String writer;
	private String title;
	private String content;
	private Date regDate;
	
	public MVCBoardDTO() {}
	
	// 글쓰기
	public MVCBoardDTO(String writer, String title, String content) {
		this.writer = writer;
		this.title = title;
		this.content = content;
	}
	
	// 글 수정
	public MVCBoardDTO(int seq, String title, String content) {
		this.seq = seq;
		this.title = title;
		this.content = content;
	}
	
	public MVCBoardDTO(int seq, String writer, String title, String content, Date regDate) {
		this.seq = seq;
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.regDate = regDate;
	}
	
	public int getSeq() {
		return seq;
	}
	
	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
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

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "MVCBoardDTO [seq=" + seq + ", writer=" + writer + ", title=" + title + ", content=" + content
				+ ", regDate=" + regDate + "]";
	}
}
