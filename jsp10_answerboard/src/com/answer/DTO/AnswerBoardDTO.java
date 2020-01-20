package com.answer.DTO;

import java.util.Date;

public class AnswerBoardDTO {

	private int boardNo;
	private int groupNo;
	private int groupOrder;
	private int titleTab;
	private String title;
	private String content;
	private String writer;
	private Date regDate;
	
	public AnswerBoardDTO() {}

	public AnswerBoardDTO(int boardNo, int groupNo, int groupOrder, int titleTab, String title, String content,
			String writer, Date regDate) {
		this.boardNo = boardNo;
		this.groupNo = groupNo;
		this.groupOrder = groupOrder;
		this.titleTab = titleTab;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.regDate = regDate;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public int getGroupNo() {
		return groupNo;
	}

	public void setGroupNo(int groupNo) {
		this.groupNo = groupNo;
	}

	public int getGroupOrder() {
		return groupOrder;
	}

	public void setGroupOrder(int groupOrder) {
		this.groupOrder = groupOrder;
	}

	public int getTitleTab() {
		return titleTab;
	}

	public void setTitleTab(int titleTab) {
		this.titleTab = titleTab;
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

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "AnswerBoardDTO [boardNo=" + boardNo + ", groupNo=" + groupNo + ", groupOrder=" + groupOrder
				+ ", titleTab=" + titleTab + ", title=" + title + ", content=" + content + ", writer=" + writer
				+ ", regDate=" + regDate + "]";
	}
}
