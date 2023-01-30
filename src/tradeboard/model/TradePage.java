package tradeboard.model;

import java.util.List;

public class TradePage {

	private int total; 
	private int currentPage;
	private List<Trade> tradeList;
	private int totalPage;
	private int startPage;
	private int endPage;
	public TradePage(int total, int currentPage, int size,List<Trade> tradeList) {
		this.total = total;
		this.currentPage = currentPage;
		this.tradeList = tradeList;
		
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
	
	public boolean hasNoTrade() {
		return total == 0;
	}
	public boolean hasTrade() {
		return total >0;
	}
	public int getTotal() {
		return total;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public List<Trade> getTradeList() {
		return tradeList;
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
