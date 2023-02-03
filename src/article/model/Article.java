package article.model;

import java.util.Date;

public class Article {

	private int articleNo;
	private String articleCategory;
	private String articleTitle;
	private String userName;
	private String articleContent;
	private Date articleCredate;
	private Date articleUpdate;
	private int articleReadCnt;
	private String isshow;
	private int userNo;
	
	public Article(int articleNo, String articleCategory, String articleTitle, String userName, String articleContent,
			Date articleCredate, Date articleUpdate, int articleReadCnt, String isshow, int userNo) {
		this.articleNo = articleNo;
		this.articleCategory = articleCategory;
		this.articleTitle = articleTitle;
		this.userName = userName;
		this.articleContent = articleContent;
		this.articleCredate = articleCredate;
		this.articleUpdate = articleUpdate;
		this.articleReadCnt = articleReadCnt;
		this.isshow = isshow;
		this.userNo = userNo;
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
	public int getArticleReadCnt() {
		return articleReadCnt;
	}
	public String getIsshow() {
		return isshow;
	}
	public int getUserNo() {
		return userNo;
	}

	@Override
	public String toString() {
		return "Article [articleNo=" + articleNo + ", articleCategory=" + articleCategory + ", articleTitle="
				+ articleTitle + ", userName=" + userName + ", articleContent=" + articleContent + ", articleCredate="
				+ articleCredate + ", articleUpdate=" + articleUpdate + ", articleReadCnt=" + articleReadCnt
				+ ", isshow=" + isshow + ", userNo=" + userNo + "]";
	}
	
	
	
}
