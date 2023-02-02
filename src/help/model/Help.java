package help.model;


public class Help {

	private int articleNo;
	private String articleCategory;
	private int userNo;
	private String helpCategory;
	
	public Help(int articleNo, String articleCategory, int userNo, String helpCategory) {
		this.articleNo = articleNo;
		this.articleCategory = articleCategory;
		this.userNo = userNo;
		this.helpCategory = helpCategory;
	}
	public int getArticleNo() {
		return articleNo;
	}
	public String getArticleCategory() {
		return articleCategory;
	}
	public int getUserNo() {
		return userNo;
	}
	public String getHelpCategory() {
		return helpCategory;
	}
	
	
	}
