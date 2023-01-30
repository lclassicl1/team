package helpComment.model;

import java.util.Date;

public class Comment {

	private int commNo;
	private String commContent;
	private Date commCreDate;
	private Date comm_update;
	private String userId;
	private String commConn;
	private String isshow;
	private int helpNo;
	public Comment(int commNo, String commContent, Date commCreDate,Date comm_update, String userId, String commConn, String isshow,
			int helpNo) {
		this.commNo = commNo;
		this.commContent = commContent;
		this.commCreDate = commCreDate;
		this.comm_update = comm_update;
		this.userId = userId;
		this.commConn = commConn;
		this.isshow = isshow;
		this.helpNo = helpNo;
		
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
	public String getCommConn() {
		return commConn;
	}
	public String getIsshow() {
		return isshow;
	}
	public int getHelpNo() {
		return helpNo;
	}
	
	
}
