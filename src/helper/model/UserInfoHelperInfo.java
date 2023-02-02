package helper.model;

import article.model.Article;
import auth.model.User;

public class UserInfoHelperInfo {

	private User user;
	private Helper helper;
	private Article article;
	
	public UserInfoHelperInfo(User user,Article article, Helper helper) {
		this.user = user;
		this.article = article;
		this.helper = helper;
	}
	
	public User getUser() {
		return user;
	}
	public Article getArticle() {
		return article;
	}
	public Helper getHelper() {
		return helper;
	}
	
	
}
