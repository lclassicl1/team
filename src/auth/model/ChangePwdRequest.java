package auth.model;

public class ChangePwdRequest {

	private String loginId;
	private String nowPwd;
	private String newPwd;
	
	public ChangePwdRequest(String loginId, String nowPwd, String newPwd) {
		this.loginId = loginId;
		this.nowPwd = nowPwd;
		this.newPwd = newPwd;
	}
	public String getLoginId() {
		return loginId;
	}
	public String getNowPwd() {
		return nowPwd;
	}
	public String getNewPwd() {
		return newPwd;
	}
	
	
}
