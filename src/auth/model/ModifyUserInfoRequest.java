package auth.model;

public class ModifyUserInfoRequest {

	private int loginNo;
	private String userHp;
	private String userAddress;
	private String userEmail;
	private String userSkill;
	private String userSchool;
	
	public ModifyUserInfoRequest(int loginNo, String userHp, String userAddress,String userEmail, String userSkill, String userSchool) {
		this.loginNo = loginNo;
		this.userHp = userHp;
		this.userAddress = userAddress;
		this.userEmail = userEmail;
		this.userSkill = userSkill;
		this.userSchool = userSchool;
	}
	
	public int getLoginNo() {
		return loginNo;
	}
	public String getUserHp() {
		return userHp;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public String getUserSkill() {
		return userSkill;
	}
	public String getUserSchool() {
		return userSchool;
	}
	
	
}
