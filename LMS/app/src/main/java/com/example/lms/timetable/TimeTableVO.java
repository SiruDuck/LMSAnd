package com.example.lms.timetable;


import java.io.Serializable;

public class TimeTableVO implements Serializable {

	private String lecture_title, syllabus, teacher_name, semester, sortation, lecture_room, lecture_year, lecture_time, enrolment, reception_status, capacity, midex, finalex,
					subjectcredit, state, book, lecture_day, lecture_class;
	private int lecture_num;
	private int lecture_apply; /*수강신청여부*/

	public int getLecture_apply() {
		return lecture_apply;
	}

	public void setLecture_apply(int lecture_apply) {
		this.lecture_apply = lecture_apply;
	}

	public String getLecture_title() {
		return lecture_title;
	}
	public void setLecture_title(String lecture_title) {
		this.lecture_title = lecture_title;	}
	public String getSyllabus() {
		return syllabus;
	}
	public void setSyllabus(String syllabus) {
		this.syllabus = syllabus;
	}
	public String getTeacher_name() {
		return teacher_name;
	}
	public void setTeacher_name(String teacher_name) {
		this.teacher_name = teacher_name;
	}
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public String getSortation() {
		return sortation;
	}
	public void setSortation(String sortation) {
		this.sortation = sortation;
	}
	public String getLecture_room() {
		return lecture_room;
	}
	public void setLecture_room(String lecture_room) {
		this.lecture_room = lecture_room;
	}
	public String getLecture_year() {
		return lecture_year;
	}
	public void setLecture_year(String lecture_year) {
		this.lecture_year = lecture_year;
	}
	public String getLecture_time() {
		return lecture_time;
	}
	public void setLecture_time(String lecture_time) {
		this.lecture_time = lecture_time;
	}
	public String getEnrolment() {
		return enrolment;
	}
	public void setEnrolment(String enrolment) {
		this.enrolment = enrolment;
	}
	public String getReception_status() {
		return reception_status;
	}
	public void setReception_status(String reception_status) {
		this.reception_status = reception_status;
	}
	public String getCapacity() {
		return capacity;
	}
	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}
	public String getMidex() {
		return midex;
	}
	public void setMidex(String midex) {
		this.midex = midex;
	}
	public String getFinalex() {
		return finalex;
	}
	public void setFinalex(String finalex) {
		this.finalex = finalex;
	}
	public String getSubjectcredit() {
		return subjectcredit;
	}
	public void setSubjectcredit(String subjectcredit) {
		this.subjectcredit = subjectcredit;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getBook() {
		return book;
	}
	public void setBook(String book) {
		this.book = book;
	}
	public int getLecture_num() {
		return lecture_num;
	}
	public void setLecture_num(int lecture_num) {
		this.lecture_num = lecture_num;
	}
	public String getLecture_day() {
		return lecture_day;
	}
	public void setLecture_day(String lecture_day) {
		this.lecture_day = lecture_day;
	}

	public String getLecture_class() {
		return lecture_class;
	}

	public void setLecture_class(String lecture_class) {
		this.lecture_class = lecture_class;
	}
}
