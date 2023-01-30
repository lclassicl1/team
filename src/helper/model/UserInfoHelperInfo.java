package helper.model;

import auth.model.User;

public class UserInfoHelperInfo {

	private User user;
	private Helper helper;
	
	public UserInfoHelperInfo(User user, Helper helper) {
		this.user = user;
		this.helper = helper;
	}
	
	public User getUser() {
		return user;
	}
	public Helper getHelper() {
		return helper;
	}
	
	
}
