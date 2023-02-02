package tradeboard.model;

import article.model.Article;
import auth.model.User;

public class UserInfoTradeInfo {

	private User user;
	private Article article;
	private Trade trade;
	
	public UserInfoTradeInfo(User user,Article article, Trade trade) {
		this.user = user;
		this.article = article;
		this.trade = trade;
	}
	
	public User getUser() {
		return user;
	}
	public Article getArticle() {
		return article;
	}
	public Trade getTrade() {
		return trade;
	}
	
	
}
