package help.model;

import article.model.Article;
import auth.model.User;

public class UserInfoHelpInfo {

	private User user;
	private Help help;
	private Article article;
	
	public UserInfoHelpInfo(User user,Article article, Help help) {
		this.user = user;
		this.article = article;
		this.help = help;
	}
	
	public User getUser() {
		return user;
	}
	public Article getArticle() {
		return article;
	}
	public Help getHelp() {
		return help;
	}
	
	
}
