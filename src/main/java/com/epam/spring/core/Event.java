package com.epam.spring.core;

import java.util.Date;
import java.util.Random;

public class Event {

	int id;
	String msg;
	Random random;
	Date date;

	{
		random = new Random();
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Event(Date date) {
		this.date = date;
		this.id = random.nextInt();
	}

	@Override
	public String toString() {
		return "Event [id=" + id + 
			", msg=" + msg + 
			", date=" + date + "]";
	}

}
