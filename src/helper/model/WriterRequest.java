package helper.model;


public class WriterRequest {

	private int articleNo;
	private String articleCategory;
	private int userNo;
	private String helperTitle;
	private String helperContent;
	private String userName;
	private String helperCategory;
	public WriterRequest(int articleNo, String articleCategory, int userNo, String helperTitle, String helperContent,
			String userName, String helperCategory) {
		this.articleNo = articleNo;
		this.articleCategory = articleCategory;
		this.userNo = userNo;
		this.helperTitle = helperTitle;
		this.helperContent = helperContent;
		this.userName = userName;
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
	public String getHelperCategory() {
		return helperCategory;
	}	
	
	
}
