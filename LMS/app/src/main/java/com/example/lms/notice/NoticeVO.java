package com.example.lms.notice;

import androidx.fragment.app.Fragment;

import com.example.lms.sidemenu.SideVO;

import java.io.Serializable;
import java.util.ArrayList;

public class NoticeVO implements Serializable {
	private String id;
	private int  readcnt;
	private String title, content, writer,filename,	filepath;
	private String writedate;
	private Fragment fragment;
	ArrayList<NoticeVO> list = new ArrayList<>();

	public NoticeVO() {
	}

	public NoticeVO(String id, int readcnt, String title, String content, String writer, String filename, String filepath, String writedate, Fragment fragment, ArrayList<NoticeVO> list) {
		this.id = id;
		this.readcnt = readcnt;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.filename = filename;
		this.filepath = filepath;
		this.writedate = writedate;
		this.fragment = fragment;
		this.list = list;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public Fragment getFragment() {
		return fragment;
	}

	public void setFragment(Fragment fragment) {
		this.fragment = fragment;
	}

	public ArrayList<NoticeVO> getList() {
		return list;
	}

	public void setList(ArrayList<NoticeVO> list) {
		this.list = list;
	}
}
