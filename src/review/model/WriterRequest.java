package review.model;


public class WriterRequest {

	private int articleNo;
	private String articleCategory;
	private int userNo;
	private String reviewCategory;
	public WriterRequest(int articleNo, String articleCategory, int userNo, String reviewCategory) {
		this.articleNo = articleNo;
		this.articleCategory = articleCategory;
		this.userNo = userNo;
		this.reviewCategory = reviewCategory;
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
	public String getReviewCategory() {
		return reviewCategory;
	}
		
}
