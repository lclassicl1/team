package helpComment.model;

public class WriteCommentRequest {

	private int helpNo; //게시글 번호 
	private String loginId;//로그인 한 아이디 
	private String content;//댓글 내용 
	
	public WriteCommentRequest(int helpNo, String loginId, String content) {
		this.helpNo = helpNo;
		this.loginId = loginId;
		this.content = content;
	}
	public int getHelpNo() {
		return helpNo;
	}
	public String getLoginId() {
		return loginId;
	}
	public String getContent() {
		return content;
	}
	
}
