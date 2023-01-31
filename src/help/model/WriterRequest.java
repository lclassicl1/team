package help.model;

import java.util.Date;

public class WriterRequest {

	private int articleNo;
	private String articleCategory;
	private int userNo;
	private String helpTitle;
	private String helpContent;
	private String userName;
	private String helpCategory;
	public WriterRequest(int articleNo, String articleCategory, int userNo, String helpTitle, String helpContent,
			String userName, String helpCategory) {
		this.articleNo = articleNo;
		this.articleCategory = articleCategory;
		this.userNo = userNo;
		this.helpTitle = helpTitle;
		this.helpContent = helpContent;
		this.userName = userName;
		this.helpCategory = helpCategory;
	}
	public int getArticleNo() {
		return articleNo;
	}
	public String getArticleCategory() {
		return articleCategory;
	}
	public int getUserNo() {
		return userNo;
	}
	public String getHelpTitle() {
		return helpTitle;
	}
	public String getHelpContent() {
		return helpContent;
	}
	public String getUserName() {
		return userName;
	}
	public String getHelpCategory() {
		return helpCategory;
	}
	
}
