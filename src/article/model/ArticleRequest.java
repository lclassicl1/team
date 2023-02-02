package article.model;

public class ArticleRequest {
	
	private String articleCategory;
	private String articleTitle;
	private String userName;
	private String articleContent;
	private int userNo;
	
	public ArticleRequest(String articleCategory,String articleTitle, String userName, String articleContent, int userNo) {
		this.articleCategory = articleCategory;
		this.articleTitle = articleTitle;
		this.userName = userName;
		this.articleContent = articleContent;
		this.userNo = userNo;
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
	public int getUserNo() {
		return userNo;
	}

	
	
}
