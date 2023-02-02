package helper.model;

import java.util.List;

public class HelperPage {

	private int total; 
	private int currentPage;
	private List<HelperList> helperList;
	private int totalPage;
	private int startPage;
	private int endPage;
	public HelperPage(int total, int currentPage, int size,List<HelperList> helperList) {
		this.total = total;
		this.currentPage = currentPage;
		this.helperList = helperList;
		
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
	
	public boolean hasNoHelper() {
		return total == 0;
	}
	public boolean hasHelper() {
		return total >0;
	}
	public int getTotal() {
		return total;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public List<HelperList> getHelperList() {
		return helperList;
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
