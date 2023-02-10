package reviewComment.model;

import java.util.Date;

public class Comment {

	private int commNo;
	private String commContent;
	private Date commCreDate;
	private String userId;
	private String isshow;
	private int commVolt;
	private int articleNo;
	public Comment(int commNo, String commContent, Date commCreDate, String userId, String isshow,
			int commVolt, int articleNo) {
		this.commNo = commNo;
		this.commContent = commContent;
		this.commCreDate = commCreDate;
		this.userId = userId;
		this.isshow = isshow;
		this.commVolt = commVolt;
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
	public String getIsshow() {
		return isshow;
	}
	public int getCommVolt() {
		return commVolt;
	}
	public int getArticleNo() {
		return articleNo;
	}
	
	
}
