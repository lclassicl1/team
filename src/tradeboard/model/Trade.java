package tradeboard.model;

import java.util.Date;

public class Trade {

	private Integer tradeNo;//게시글 번호 
	private int userNo;//작성자 회원 번호 
	private String tradeTitle;//게시글 제목 
	private String tradeContent;//게시글 내용 
	private Date createDate;// 게시글 작성시간 
	private Date update;
	private int readCnt;//게시글 조회수 
	private String userName;//작성자 이름 
	private String isshow;
	public Trade(Integer tradeNo, int userNo, String tradeTitle, String tradeContent, Date createDate,Date update,
			int readCnt, String userName,String isshow) {
		this.tradeNo = tradeNo;
		this.userNo = userNo;
		this.tradeTitle = tradeTitle;
		this.tradeContent = tradeContent;
		this.createDate = createDate;
		this.update = update;
		this.readCnt = readCnt;
		this.userName = userName;
		this.isshow = isshow;
	}
	public Integer getTradeNo() {
		return tradeNo;
	}
	public int getUserNo() {
		return userNo;
	}
	public String getTradeTitle() {
		return tradeTitle;
	}
	public Date getUpdate() {
		return update;
	}
	public String getTradeContent() {
		return tradeContent;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public int getReadCnt() {
		return readCnt;
	}
	public String getUserName() {
		return userName;
	}
	public String getIsshow() {
		return isshow;
	}
	
}
