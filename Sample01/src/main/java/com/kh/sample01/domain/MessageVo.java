package com.kh.sample01.domain;

import java.sql.Timestamp;

public class MessageVo {
	private int message_id;			// 메세지 번호
	private String target_id;		// 받는 사람
	private String sender;			// 보내는 사람
	private String message_text;	// 쪽지 내용
	private Timestamp open_date;	// 쪽지 열람 시각
	private Timestamp send_date;	// 쪽지 보낸 시각
	
	public int getMessage_id() {
		return message_id;
	}
	public void setMessage_id(int message_id) {
		this.message_id = message_id;
	}
	public String getTarget_id() {
		return target_id;
	}
	public void setTarget_id(String target_id) {
		this.target_id = target_id;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getMessage_text() {
		return message_text;
	}
	public void setMessage_text(String message_text) {
		this.message_text = message_text;
	}
	public Timestamp getOpen_date() {
		return open_date;
	}
	public void setOpen_date(Timestamp open_date) {
		this.open_date = open_date;
	}
	public Timestamp getSend_date() {
		return send_date;
	}
	public void setSend_date(Timestamp send_date) {
		this.send_date = send_date;
	}
	@Override
	public String toString() {
		return "MessageVo [message_id=" + message_id + ", target_id=" + target_id + ", sender=" + sender
				+ ", message_text=" + message_text + ", open_date=" + open_date + ", send_date=" + send_date + "]";
	}
	
	
}
