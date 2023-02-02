package helper.model;


public class Helper {

	private int articleNo;
	private String articleCategory;
	private int userNo;
	private String helperCategory;
	
	public Helper(int articleNo, String articleCategory, int userNo, String helperCategory) {
		this.articleNo = articleNo;
		this.articleCategory = articleCategory;
		this.userNo = userNo;
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
	public String getHelperCategory() {
		return helperCategory;
	}
		
}
