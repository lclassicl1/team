package tradecomment.model;

import java.util.Date;

public class Comment {

	private int commNo;
	private String commContent;
	private Date commCreDate;
	private Date comm_update;
	private String userId;
	private String isshow;
	private int tradeNo;
	public Comment(int commNo, String commContent, Date commCreDate,Date comm_update, String userId, String isshow,
			int tradeNo) {
		this.commNo = commNo;
		this.commContent = commContent;
		this.commCreDate = commCreDate;
		this.comm_update = comm_update;
		this.userId = userId;
		this.isshow = isshow;
		this.tradeNo = tradeNo;
		
	}
	public int getCommNo() {
		return commNo;
	}
	public String getCommContent() {
		return commContent;
	}
	public Date getCommCreDate() {
		return commCreDate;
	}
	public Date getComm_update() {
		return comm_update;
	}
	public String getUserId() {
		return userId;
	}
	public String getIsshow() {
		return isshow;
	}
	public int getTradeNo() {
		return tradeNo;
	}
	
	
}
