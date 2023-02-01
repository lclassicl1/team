package tradeboard.model;


public class WriterRequest {

	private int articleNo;
	private String articleCategory;
	private int userNo;
	private String tradeTitle;
	private String tradeContent;
	private String userName;
	public WriterRequest(int articleNo, String articleCategory, int userNo, String tradeTitle, String tradeContent,
			String userName) {
		this.articleNo = articleNo;
		this.articleCategory = articleCategory;
		this.userNo = userNo;
		this.tradeTitle = tradeTitle;
		this.tradeContent = tradeContent;
		this.userName = userName;
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
	public String getTradeTitle() {
		return tradeTitle;
	}
	public String getTradeContent() {
		return tradeContent;
	}
	public String getUserName() {
		return userName;
	}
		
}
