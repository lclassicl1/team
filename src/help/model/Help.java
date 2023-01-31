package help.model;

import java.util.Date;

public class Help {

	private int articleNo;
	private String articleCategory;
	private int userNo;
	private String helpTitle;
	private String helpContent;
	private String userName;
	private Date helpCredate;
	private Date helpUpdate;
	private int helpReadCnt;
	private String isshow;
	private String helpCategory;
	
	public Help(int articleNo, String articleCategory, int userNo, String helpTitle, String helpContent,
			String userName, Date helpCredate, Date helpUpdate, int helpReadCnt, String isshow, String helpCategory) {
		this.articleNo = articleNo;
		this.articleCategory = articleCategory;
		this.userNo = userNo;
		this.helpTitle = helpTitle;
		this.helpContent = helpContent;
		this.userName = userName;
		this.helpCredate = helpCredate;
		this.helpUpdate = helpUpdate;
		this.helpReadCnt = helpReadCnt;
		this.isshow = isshow;
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
	public Date getHelpCredate() {
		return helpCredate;
	}
	public Date getHelpUpdate() {
		return helpUpdate;
	}
	public int getHelpReadCnt() {
		return helpReadCnt;
	}
	public String getIsshow() {
		return isshow;
	}
	public String getHelpCategory() {
		return helpCategory;
	}
	
	
	
}
