package helpComment.model;

import java.util.Date;

public class Comment {

	private int commNo;
	private String commContent;
	private Date commCreDate;
	private String userId;
	private String commConn;
	private String isshow;
	private int articleNo;
	public Comment(int commNo, String commContent, Date commCreDate, String userId, String commConn, String isshow,
			int articleNo) {
		this.commNo = commNo;
		this.commContent = commContent;
		this.commCreDate = commCreDate;
		this.userId = userId;
		this.commConn = commConn;
		this.isshow = isshow;
		this.articleNo = articleNo;
		
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
	public String getUserId() {
		return userId;
	}
	public String getCommConn() {
		return commConn;
	}
	public String getIsshow() {
		return isshow;
	}
	public int getArticleNo() {
		return articleNo;
	}
	
	
}
