package tradeboard.model;

import java.util.Date;

public class Trade {

	private int articleNo;
	private String articleCategory;
	private int userNo;
	private String userName;
	private String tradeTitle;
	private String tradeContent;
	private Date tradeCredate;
	private Date tradeUpdate;
	private int tradeReadCnt;
	private String isshow;
	public Trade(int articleNo, String articleCategory, int userNo, String userName, String tradeTitle,
			String tradeContent, Date tradeCredate, Date tradeUpdate, int tradeReadCnt, String isshow) {
		this.articleNo = articleNo;
		this.articleCategory = articleCategory;
		this.userNo = userNo;
		this.userName = userName;
		this.tradeTitle = tradeTitle;
		this.tradeContent = tradeContent;
		this.tradeCredate = tradeCredate;
		this.tradeUpdate = tradeUpdate;
		this.tradeReadCnt = tradeReadCnt;
		this.isshow = isshow;
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
	public String getUserName() {
		return userName;
	}
	public String getTradeTitle() {
		return tradeTitle;
	}
	public String getTradeContent() {
		return tradeContent;
	}
	public Date getTradeCredate() {
		return tradeCredate;
	}
	public Date getTradeUpdate() {
		return tradeUpdate;
	}
	public int getTradeReadCnt() {
		return tradeReadCnt;
	}
	public String getIsshow() {
		return isshow;
	}
	
	
}
