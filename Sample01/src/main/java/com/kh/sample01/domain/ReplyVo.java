package com.kh.sample01.domain;

import java.sql.Timestamp;

public class ReplyVo {
	private int rno;
	private int bno;
	private String reply_text;
	private String replyer;
	private Timestamp reg_date;
	private Timestamp update_date;
	
	public int getRno() {
		return rno;
	}
	public void setRno(int rno) {
		this.rno = rno;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getReply_text() {
		return reply_text;
	}
	public void setReply_text(String reply_text) {
		this.reply_text = reply_text;
	}
	public String getReplyer() {
		return replyer;
	}
	public void setReplyer(String replyer) {
		this.replyer = replyer;
	}
	public Timestamp getReg_date() {
		return reg_date;
	}
	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}
	public Timestamp getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(Timestamp update_date) {
		this.update_date = update_date;
	}
	@Override
	public String toString() {
		return "ReplyVo [rno=" + rno + ", bno=" + bno + ", reply_text=" + reply_text + ", replyer=" + replyer
				+ ", reg_date=" + reg_date + ", update_date=" + update_date + "]";
	}
	
	
	
}
