package notice.model;

import article.model.Article;
import auth.model.User;

public class UserInfoNoticeInfo {

	private User user;
	private Notice notice;
	private Article article;
	
	public UserInfoNoticeInfo(User user,Article article, Notice notice) {
		this.user = user;
		this.article = article;
		this.notice = notice;
	}
	
	public User getUser() {
		return user;
	}
	public Article getArticle() {
		return article;
	}
	public Notice getNotice() {
		return notice;
	}
	
	
}
