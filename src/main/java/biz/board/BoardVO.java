package biz.board;

import java.util.Date;

public class BoardVO {
	private int seq;
	private String title;
	private String content;
	private String writer;
	private Date regDate;
	private int hit;
	
	
	
	
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	
	
	public BoardVO() {
		super();
	}
	
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
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
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	
	
	@Override
	public String toString() {
		return "BoardVO [seq=" + seq + ", title=" + title + ", content=" + content + ", writer=" + writer + ", regDate="
				+ regDate + ", hit=" + hit + "]";
	}
	
	
	
	
	
	
}
