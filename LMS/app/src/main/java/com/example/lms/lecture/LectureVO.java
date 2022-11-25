package com.example.lms.lecture;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

public class LectureVO implements Serializable {
	private String lecture_title, teacher_name, semester, sortation, lecture_room, lecture_time, id
	, enrolment, reception_status, capacity, subjectcredit, state, book, lecture_year, lecture_day, host
	, name, gender, grade, department_name, phone;
	private int lecture_num;
	private Date midex, finalex;
	
	
	public String getLecture_title() {
		return lecture_title;
	}
	public void setLecture_title(String lecture_title) {
		this.lecture_title = lecture_title;
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
	public String getLecture_time() {
		return lecture_time;
	}
	public void setLecture_time(String lecture_time) {
		this.lecture_time = lecture_time;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getLecture_year() {
		return lecture_year;
	}
	public void setLecture_year(String lecture_year) {
		this.lecture_year = lecture_year;
	}
	public String getLecture_day() {
		return lecture_day;
	}
	public void setLecture_day(String lecture_day) {
		this.lecture_day = lecture_day;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getDepartment_name() {
		return department_name;
	}
	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getLecture_num() {
		return lecture_num;
	}
	public void setLecture_num(int lecture_num) {
		this.lecture_num = lecture_num;
	}
	public Date getMidex() {
		return midex;
	}
	public void setMidex(Date midex) {
		this.midex = midex;
	}
	public Date getFinalex() {
		return finalex;
	}
	public void setFinalex(Date finalex) {
		this.finalex = finalex;
	}
	
	
	
	
	
	
	
	
	
	}
