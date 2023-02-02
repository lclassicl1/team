package notice.model;

import java.util.List;

import article.model.Article;

public class NoticePage {

	private int total; 
	private int currentPage;
	private List<Article> noticeList;
	private int totalPage;
	private int startPage;
	private int endPage;
	public NoticePage(int total, int currentPage, int size,List<Article> noticeList) {
		this.total = total;
		this.currentPage = currentPage;
		this.noticeList = noticeList;
		
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
	
	public boolean hasNoNotice() {
		return total == 0;
	}
	public boolean hasNotice() {
		return total >0;
	}
	public int getTotal() {
		return total;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public List<Article> getNoticeList() {
		return noticeList;
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
