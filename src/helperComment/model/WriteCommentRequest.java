package helperComment.model;

public class WriteCommentRequest {

	private int articleNo; //게시글 번호 
	private String loginId;//로그인 한 아이디 
	private String content;//댓글 내용 
	
	public WriteCommentRequest(int articleNo, String loginId, String content) {
		this.articleNo = articleNo;
		this.loginId = loginId;
		this.content = content;
	}
	public int getArticleNo() {
		return articleNo;
	}
	public String getLoginId() {
		return loginId;
	}
	public String getContent() {
		return content;
	}
	
}
