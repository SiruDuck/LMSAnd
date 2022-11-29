package com.example.lms.board;


import java.io.Serializable;
import java.util.ArrayList;

public class BoardVO implements Serializable {
	private int id, readcnt =0;
	private String title, content, writer,filename,	filepath;
	private String writedate;
	ArrayList<BoardVO> list = new ArrayList<>();




	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getReadcnt() {
		return readcnt;
	}

	public void setReadcnt(int readcnt) {
		this.readcnt = readcnt;
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

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public String getWritedate() {
		return writedate;
	}

	public void setWritedate(String writedate) {
		this.writedate = writedate;
	}

	public ArrayList<BoardVO> getList() {
		return list;
	}

	public void setList(ArrayList<BoardVO> list) {
		this.list = list;
	}
}
