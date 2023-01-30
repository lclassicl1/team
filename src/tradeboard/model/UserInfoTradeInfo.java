package tradeboard.model;

import auth.model.User;

public class UserInfoTradeInfo {

	private User user;
	private Trade trade;
	
	public UserInfoTradeInfo(User user, Trade trade) {
		this.user = user;
		this.trade = trade;
	}
	
	public User getUser() {
		return user;
	}
	public Trade getTrade() {
		return trade;
	}
	
	
}
