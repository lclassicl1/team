package review.model;

import article.model.Article;
import auth.model.User;

public class UserInfoReviewInfo {

	private User user;
	private Review review;
	private Article article;
	
	public UserInfoReviewInfo(User user,Article article, Review review) {
		this.user = user;
		this.article = article;
		this.review = review;
	}
	
	public User getUser() {
		return user;
	}
	public Article getArticle() {
		return article;
	}
	public Review getReview() {
		return review;
	}
	
	
}
