package tradecomment.model;

public class WriteCommentRequest {

	private int tradeNo; //게시글 번호 
	private String loginId;//로그인 한 아이디 
	private String content;//댓글 내용 
	
	public WriteCommentRequest(int tradeNo, String loginId, String content) {
		this.tradeNo = tradeNo;
		this.loginId = loginId;
		this.content = content;
	}
	public int getTradeNo() {
		return tradeNo;
	}
	public String getLoginId() {
		return loginId;
	}
	public String getContent() {
		return content;
	}
	
}
