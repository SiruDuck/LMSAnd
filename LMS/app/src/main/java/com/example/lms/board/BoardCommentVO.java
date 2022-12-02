package com.example.lms.board;

import java.io.Serializable;

public class BoardCommentVO implements Serializable {
	private  String id;
	private int board_id;
	private String content,	writer, writedate;



	public BoardCommentVO() {

    }

	public BoardCommentVO(String id, int board_id, String content, String writer, String writedate) {
		this.id = id;
		this.board_id = board_id;
		this.content = content;
		this.writer = writer;
		this.writedate = writedate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getBoard_id() {
		return board_id;
	}

	public void setBoard_id(int board_id) {
		this.board_id = board_id;
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

	public String getWritedate() {
		return writedate;
	}

	public void setWritedate(String writedate) {
		this.writedate = writedate;
	}
}
