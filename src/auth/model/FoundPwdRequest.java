package auth.model;

public class FoundPwdRequest {

	private String userId;
	private String userName;
	private String userHp;
	private String userBirth;
	public FoundPwdRequest(String userId, String userName, String userHp, String userBirth) {
		this.userId = userId;
		this.userName = userName;
		this.userHp = userHp;
		this.userBirth = userBirth;
	}
	public String getUserId() {
		return userId;
	}
	public String getUserName() {
		return userName;
	}
	public String getUserHp() {
		return userHp;
	}
	public String getUserBirth() {
		return userBirth;
	}
	
	
}
