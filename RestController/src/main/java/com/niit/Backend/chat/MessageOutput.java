package com.niit.Backend.chat;

import java.util.Date;

public class MessageOutput extends Message
{

	private Date time;
	
	public MessageOutput(Message original,Date time)
	{
		super(original.getId(),original.getMessage());
		this.setTime(time);
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	
}
