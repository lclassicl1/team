package help.model;

import auth.model.User;

public class UserInfoHelpInfo {

	private User user;
	private Help help;
	
	public UserInfoHelpInfo(User user, Help help) {
		this.user = user;
		this.help = help;
	}
	
	public User getUser() {
		return user;
	}
	public Help getHelp() {
		return help;
	}
	
	
}
