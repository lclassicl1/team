package helpComment.model;

public class ModifyCommRequest {

	private int commNo;
	private String content;
	private String loginId;
	public ModifyCommRequest(int commNo, String content, String loginId) {
		this.commNo = commNo;
		this.content = content;
		this.loginId = loginId;
	}
	public int getCommNo() {
		return commNo;
	}
	public String getContent() {
		return content;
	}
	public String getLoginId() {
		return loginId;
	}
	
	
	
}
