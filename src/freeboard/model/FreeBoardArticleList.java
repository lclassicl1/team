package freeboard.model;

public class FreeBoardArticleList {
	private int article_no;
	private String  article_category;
	private int user_no;
	
	
	public FreeBoardArticleList(int article_no, String article_category, int user_no) {
		super();
		this.article_no = article_no;
		this.article_category = article_category;
		this.user_no = user_no;
	}
	
	
	public int getArticle_no() {
		return article_no;
	}
	public String getArticle_category() {
		return article_category;
	}
	public int getUser_no() {
		return user_no;
	}


	@Override
	public String toString() {
		return "FreeBoardArticleList [article_no=" + article_no + ", article_category=" + article_category
				+ ", user_no=" + user_no + "]";
	}
}
