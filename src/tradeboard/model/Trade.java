package tradeboard.model;


public class Trade {

	private int articleNo;
	private String articleCategory;
	private int userNo;
	private String tradeCategory;
	
	public Trade(int articleNo, String articleCategory, int userNo, String tradeCategory) {
		this.articleNo = articleNo;
		this.articleCategory = articleCategory;
		this.userNo = userNo;
		this.tradeCategory = tradeCategory;
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
	public String getTradeCategory() {
		return tradeCategory;
	}
	
	
	}
