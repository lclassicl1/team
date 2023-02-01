package article.model;

public class Article {

	private int articleNo;
	private String articleCategory;
	private int userNo;
	public Article(int articleNo, String articleCategory, int userNo) {
		this.articleNo = articleNo;
		this.articleCategory = articleCategory;
		this.userNo = userNo;
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
	
	
}
