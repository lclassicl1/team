package freeboard.model;

import java.util.List;

public class FreePage {

	private int total; 
	private int currentPage;
	private List<FreeBoardList> freeBoardList;
	private int totalPage;
	private int startPage;
	private int endPage;
	public FreePage(int total, int currentPage, int size,List<FreeBoardList> freeBoardList) {
		this.total = total;
		this.currentPage = currentPage;
		this.freeBoardList = freeBoardList;
		
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
	
	public FreePage(List<FreeBoardList> freeBoardList) {
		this.freeBoardList = freeBoardList;
	}

	public boolean hasNoFreeBoard() {
		return total == 0;
	}
	public boolean hasFreeBoard() {
		return total >0;
	}
	public int getTotal() {
		return total;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public List<FreeBoardList> getFreeBoardList() {
		return freeBoardList;
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

	@Override
	public String toString() {
		return "FreePage [total=" + total + ", currentPage=" + currentPage + ", freeBoardList=" + freeBoardList
				+ ", totalPage=" + totalPage + ", startPage=" + startPage + ", endPage=" + endPage + "]";
	}
	
	
}
