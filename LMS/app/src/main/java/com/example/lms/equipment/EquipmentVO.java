package com.example.lms.equipment;

import java.io.Serializable;
import java.sql.Date;

public class EquipmentVO implements Serializable {
	private String equipment, situation;
	private int equipment_num,  price;
	private String buy_day;
	public String getEquipment() {
		return equipment;
	}
	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}
	public String getSituation() {
		return situation;
	}
	public void setSituation(String situation) {
		this.situation = situation;
	}
	public int getEquipment_num() {
		return equipment_num;
	}
	public void setEquipment_num(int equipment_num) {
		this.equipment_num = equipment_num;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

	public String getBuy_day() {
		return buy_day;
	}

	public void setBuy_day(String buy_day) {
		this.buy_day = buy_day;
	}
/*
	public Date getBuy_day() {
		return buy_day;
	}
	public void setBuy_day(Date buy_day) {
		this.buy_day = buy_day;
	}
*/




}
