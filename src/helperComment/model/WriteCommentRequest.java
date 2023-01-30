package helperComment.model;

public class WriteCommentRequest {

	private int helperNo; //게시글 번호 
	private String loginId;//로그인 한 아이디 
	private String content;//댓글 내용 
	
	public WriteCommentRequest(int helperNo, String loginId, String content) {
		this.helperNo = helperNo;
		this.loginId = loginId;
		this.content = content;
	}
	public int getHelperNo() {
		return helperNo;
	}
	public String getLoginId() {
		return loginId;
	}
	public String getContent() {
		return content;
	}
	
}
