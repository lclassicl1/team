package article.model;

import java.util.List;


public class ArticlePage {

	private int total; 
	private int currentPage;
	private List<Article> articleList;
	private int totalPage;
	private int startPage;
	private int endPage;
	public ArticlePage(int total, int currentPage, int size,List<Article> articleList) {
		this.total = total;
		this.currentPage = currentPage;
		this.articleList = articleList;
		
		if(total == 0) {
			totalPage = 0;
			startPage = 0;
			endPage = 0;
		}else {
			totalPage = total/size;
			if(total%size>0) {
				totalPage++;
			}
			int Modval = currentPage %5;
			startPage = currentPage/5*5+1;
			if(Modval == 0) {
				startPage -=5;
			}
			endPage = startPage+4;
			if(endPage>totalPage) {
				endPage = totalPage;
			}
		}
	}
	
	public boolean hasNoArticle() {
		return total == 0;
	}
	public boolean hasArticle() {
		return total >0;
	}
	public int getTotal() {
		return total;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public List<Article> getArticleList() {
		return articleList;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public int getStartPage() {
		return startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	
	
}
