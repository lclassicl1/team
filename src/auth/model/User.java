package auth.model;

import java.util.Date;

public class User {

	private int userNo;
	private String userId;
	private String userPwd;
	private String userName;
	private String userHp;
	private Date userRegdate;
	private String userAddress;
	private int userGrade;
	private String userEmail;
	private String userGender;
	private String userSkill;
	private String userSchool;
	private String userBirth;
	
	public User(int userNo, String userId, String userPwd, String userName, String userHp, Date userRegdate,String userAddress,
			int userGrade, String userEmail, String userGender, String userSkill, String userSchool, String userBirth) {
		this.userNo = userNo;
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.userHp = userHp;
		this.userRegdate = userRegdate;
		this.userAddress = userAddress;
		this.userGrade = userGrade;
		this.userEmail = userEmail;
		this.userGender = userGender;
		this.userSkill = userSkill;
		this.userSchool = userSchool;
		this.userBirth = userBirth;
	}
	
	public int getUserNo() {
		return userNo;
	}
	public String getUserId() {
		return userId;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public String getUserName() {
		return userName;
	}
	public String getUserHp() {
		return userHp;
	}
	public Date getUserRegdate() {
		return userRegdate;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public int getUserGrade() {
			return userGrade;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public String getUserGender() {
		return userGender;
	}
	public String getUserSkill() {
		if(userSkill==null) {
			return "없음";
		}else {
			return userSkill;
		}
	}
	public String getUserSchool() {
		if(userSchool==null) {
			return "없음";
		}else {
			return userSchool;
		}
	}
	public String getUserBirth() {
		return userBirth;
	}

	@Override
	public String toString() {
		return "User [userNo=" + userNo + ", userId=" + userId + ", userPwd=" + userPwd + ", userName=" + userName
				+ ", userHp=" + userHp + ", userRegdate=" + userRegdate + ", userAddress=" + userAddress
				+ ", userGrade=" + userGrade + ", userEmail=" + userEmail + ", userGender=" + userGender
				+ ", userSkill=" + userSkill + ", userSchool=" + userSchool + ", userBirth=" + userBirth + "]";
	}
	
	
	
	
}
