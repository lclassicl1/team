package help.model;

import java.util.Date;

public class Help {

	private Integer helpNo;//게시글 번호 
	private int userNo;//작성자 회원 번호 
	private String helpTitle;//게시글 제목 
	private String helpContent;//게시글 내용 
	private Date createDate;// 게시글 작성시간 
	private Date update;
	private String category;//게시글 카테고리  
	private int readCnt;//게시글 조회수 
	private String userName;//작성자 이름 
	private String isshow;
	public Help(Integer helpNo, int userNo, String helpTitle, String helpContent, Date createDate,Date update, String category,
			int readCnt, String userName,String isshow) {
		this.helpNo = helpNo;
		this.userNo = userNo;
		this.helpTitle = helpTitle;
		this.helpContent = helpContent;
		this.createDate = createDate;
		this.update = update;
		this.category = category;
		this.readCnt = readCnt;
		this.userName = userName;
		this.isshow = isshow;
	}
	public Integer getHelpNo() {
		return helpNo;
	}
	public int getUserNo() {
		return userNo;
	}
	public String getHelpTitle() {
		return helpTitle;
	}
	public Date getUpdate() {
		return update;
	}
	public String getHelpContent() {
		return helpContent;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public String getCategory() {
		return category;
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
