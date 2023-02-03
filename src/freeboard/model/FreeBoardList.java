package freeboard.model;

import java.util.Date;

public class FreeBoardList {
	private int articleNo;
	private String articleCategory;
	private String articleTitle;
	private String userName;
	private String articleContent;
	private Date articleCredate;
	private Date articleUpdate;
	private int articleReadcnt;
	private String isshow;
	private int userNo;
	private String freeCategory;
	
	
	
	public FreeBoardList(int articleNo, String articleCategory, String articleTitle, String userName,
			String articleContent, Date articleCredate, Date articleUpdate, int articleReadcnt, String isshow,
			int userNo, String freeCategory) {
		this.articleNo = articleNo;
		this.articleCategory = articleCategory;
		this.articleTitle = articleTitle;
		this.userName = userName;
		this.articleContent = articleContent;
		this.articleCredate = articleCredate;
		this.articleUpdate = articleUpdate;
		this.articleReadcnt = articleReadcnt;
		this.isshow = isshow;
		this.userNo = userNo;
		this.freeCategory = freeCategory;
	}
	
	
	public int getArticleNo() {
		return articleNo;
	}
	public String getArticleCategory() {
		return articleCategory;
	}
	public String getArticleTitle() {
		return articleTitle;
	}
	public String getUserName() {
		return userName;
	}
	public String getArticleContent() {
		return articleContent;
	}
	public Date getArticleCredate() {
		return articleCredate;
	}
	public Date getArticleUpdate() {
		return articleUpdate;
	}
	public int getArticleReadcnt() {
		return articleReadcnt;
	}
	public String getIsshow() {
		return isshow;
	}
	public int getUserNo() {
		return userNo;
	}
	public String getFreeCategory() {
		return freeCategory;
	}


	@Override
	public String toString() {
		return "FreeBoardList [articleNo=" + articleNo + ", articleCategory=" + articleCategory + ", articleTitle="
				+ articleTitle + ", userName=" + userName + ", articleContent=" + articleContent + ", articleCredate="
				+ articleCredate + ", articleUpdate=" + articleUpdate + ", articleReadcnt=" + articleReadcnt
				+ ", isshow=" + isshow + ", userNo=" + userNo + ", freeCartegory=" + freeCategory + "]";
	}
	
	
	
	
}
