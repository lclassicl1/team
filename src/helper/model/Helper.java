package helper.model;

import java.util.Date;

public class Helper {

	private int articleNo;
	private String articleCategory;
	private int userNo;
	private String helperTitle;
	private String helperContent;
	private String userName;
	private Date helperCredate;
	private Date helperUpdate;
	private int helperReadCnt;
	private String isshow;
	private String helperCategory;
	
	public Helper(int articleNo, String articleCategory, int userNo, String helperTitle, String helperContent,
			String userName, Date helperCredate, Date helperUpdate, int helperReadCnt, String isshow,
			String helperCategory) {
		this.articleNo = articleNo;
		this.articleCategory = articleCategory;
		this.userNo = userNo;
		this.helperTitle = helperTitle;
		this.helperContent = helperContent;
		this.userName = userName;
		this.helperCredate = helperCredate;
		this.helperUpdate = helperUpdate;
		this.helperReadCnt = helperReadCnt;
		this.isshow = isshow;
		this.helperCategory = helperCategory;
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
	public String getHelperTitle() {
		return helperTitle;
	}
	public String getHelperContent() {
		return helperContent;
	}
	public String getUserName() {
		return userName;
	}
	public Date getHelperCredate() {
		return helperCredate;
	}
	public Date getHelperUpdate() {
		return helperUpdate;
	}
	public int getHelperReadCnt() {
		return helperReadCnt;
	}
	public String getIsshow() {
		return isshow;
	}
	public String getHelperCategory() {
		return helperCategory;
	}
	
	
}
