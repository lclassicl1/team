package help.model;


public class WriterRequest {

	private String title;
	private String content;
	private String category;
	private int userNo;
	private String userName;
	public WriterRequest(String title, String content, String category, int userNo,
			String userName) {
		this.title = title;
		this.content = content;
		this.category = category;
		this.userNo = userNo;
		this.userName = userName;
	}
	public String getTitle() {
		return title;
	}
	public String getContent() {
		return content;
	}
	public String getCategory() {
		return category;
	}
	public int getUserNo() {
		return userNo;
	}
	public String getUserName() {
		return userName;
	}
	
	
}
