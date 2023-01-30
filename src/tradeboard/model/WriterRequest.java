package tradeboard.model;


public class WriterRequest {

	private String title;
	private String content;
	private int userNo;
	private String userName;
	public WriterRequest(String title, String content, int userNo,
			String userName) {
		this.title = title;
		this.content = content;
		this.userNo = userNo;
		this.userName = userName;
	}
	public String getTitle() {
		return title;
	}
	public String getContent() {
		return content;
	}
	public int getUserNo() {
		return userNo;
	}
	public String getUserName() {
		return userName;
	}
	
	
}
