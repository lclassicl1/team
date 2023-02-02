package help.model;

import java.util.List;

public class HelpPage {

	private int total; 
	private int currentPage;
	private List<HelpList> helpList;
	private int totalPage;
	private int startPage;
	private int endPage;
	public HelpPage(int total, int currentPage, int size,List<HelpList> helpList) {
		this.total = total;
		this.currentPage = currentPage;
		this.helpList = helpList;
		
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
	
	public boolean hasNoHelp() {
		return total == 0;
	}
	public boolean hasHelp() {
		return total >0;
	}
	public int getTotal() {
		return total;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public List<HelpList> getHelpList() {
		return helpList;
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
