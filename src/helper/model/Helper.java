package helper.model;

import java.util.Date;

public class Helper {

	private Integer helperNo;//게시글 번호 
	private int userNo;//작성자 회원 번호 
	private String helperTitle;//게시글 제목 
	private String helperContent;//게시글 내용 
	private Date createDate;// 게시글 작성시간 
	private Date update;
	private String category;//게시글 카테고리  
	private int readCnt;//게시글 조회수 
	private String userName;//작성자 이름 
	private String isshow;
	public Helper(Integer helperNo, int userNo, String helperTitle, String helperContent, Date createDate,Date update, String category,
			int readCnt, String userName,String isshow) {
		this.helperNo = helperNo;
		this.userNo = userNo;
		this.helperTitle = helperTitle;
		this.helperContent = helperContent;
		this.createDate = createDate;
		this.update = update;
		this.category = category;
		this.readCnt = readCnt;
		this.userName = userName;
		this.isshow = isshow;
	}
	public Integer getHelperNo() {
		return helperNo;
	}
	public int getUserNo() {
		return userNo;
	}
	public String getHelperTitle() {
		return helperTitle;
	}
	public Date getUpdate() {
		return update;
	}
	public String getHelperContent() {
		return helperContent;
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
