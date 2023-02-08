package freeboard.model;


public class FreeWriterRequest {

	private int articleNo;
	private String articleCategory;
	private int userNo;
	private String freeCategory;
	public FreeWriterRequest(int articleNo, String articleCategory, int userNo, String freeCategory) {
		this.articleNo = articleNo;
		this.articleCategory = articleCategory;
		this.userNo = userNo;
		this.freeCategory = freeCategory;
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
	public String getFreeCategory() {
		return freeCategory;
	}
		
}
