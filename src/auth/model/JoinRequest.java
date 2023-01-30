package auth.model;

import java.util.Map;

public class JoinRequest {

	private String userId;
	private String userPwd;
	private String userRePwd;
	private String userName;
	private String userHp;
	private String userAddress;
	private String userEmail;
	private String userGender;
	private String userSkill;
	private String userSchool;
	private String userBirth;
	
	public JoinRequest(String userId, String userPwd, String userRePwd, String userName, String userHp,
			String userAddress, String userEmail, String userGender, String userSkill, String userSchool,
			String userBirth) {
		this.userId = userId;
		this.userPwd = userPwd;
		this.userRePwd = userRePwd;
		this.userName = userName;
		this.userHp = userHp;
		this.userAddress = userAddress;
		this.userEmail = userEmail;
		this.userGender = userGender;
		this.userSkill = userSkill;
		this.userSchool = userSchool;
		this.userBirth = userBirth;
	}
	
	// 회원가입 폼에서 입력한 패스워드와 패스워드 확인이 일치한지 
	public boolean isPasswordEquals() {
		return userPwd !=null && userPwd.equals(userRePwd);
	}
	// 입력한 폼이 비었는지 한번에 검사한다. 
	// 기술과 학력사항은 제외하였다. 
	public void validdate(Map<String,Boolean> errors) {
		checkEmpty(errors,this.userId,"userId");
		checkEmpty(errors,this.userPwd,"userPwd");
		checkEmpty(errors,this.userRePwd,"userRePwd");
		checkEmpty(errors,this.userName,"userName");
		checkEmpty(errors,this.userHp,"userHp");
		checkEmpty(errors,this.userAddress,"userAddress");
		checkEmpty(errors,this.userEmail,"userEmail");
		checkEmpty(errors,this.userGender,"userGender");
		checkEmpty(errors,this.userBirth,"userBirth");
		
		
		if(!errors.containsKey("userRePwd")) {
			if(!isPasswordEquals()) {
				errors.put("notMatch",Boolean.TRUE);
			}
		}
	}
	// 입력한 폼이 비어있으면 에러에 넣는다 .
	private void checkEmpty(Map<String,Boolean> errors,String value,String fieldName) {
		if(value == null || value.isEmpty()) {
			errors.put(fieldName,Boolean.TRUE);
		}
	}
	public String getUserId() {
		return userId;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public String getUserRePwd() {
		return userRePwd;
	}
	public String getUserName() {
		return userName;
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
	public String getUserGender() {
		return userGender;
	}
	public String getUserSkill() {
		return userSkill;
	}
	public String getUserSchool() {
		return userSchool;
	}
	public String getUserBirth() {
		return userBirth;
	}
	
	
}
