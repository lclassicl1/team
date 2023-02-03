package master.model;

import java.util.List;

import auth.model.User;


public class UserPage {

	private int total; 
	private int currentPage;
	private List<User> userList;
	private int totalPage;
	private int startPage;
	private int endPage;
	public UserPage(int total, int currentPage, int size,List<User> userList) {
		this.total = total;
		this.currentPage = currentPage;
		this.userList = userList;
		
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
	
	public boolean hasNoUser() {
		return total == 0;
	}
	public boolean hasUser() {
		return total >0;
	}
	public int getTotal() {
		return total;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public List<User> getUserList() {
		return userList;
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
