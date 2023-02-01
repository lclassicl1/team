package review.comment.service;


import java.util.List;

import review.model.Review;

public class ReviewCommentPage {
	
	private int total;			 //전체게시물수
	private int currentPage;	 //현재 페이지(요청페이지)
	private List<Review> content;//게시글목록정보(글번호,제목,작성자,조회수,최초작성일,마지막수정일,노출여부) 
	private int totalPages;		//총페이지수
	private int startPage;		//시작페이지번호
	private int endPage;		//끝페이지번호
	
	public ReviewCommentPage(int total, int currentPage, 
			int size, List<Review> content) {
		this.total = total;
		this.currentPage = currentPage;
		this.content = content;
		if (total == 0) { //게시물이 존재하지 않는 경우
			totalPages = 0;
			startPage = 0;
			endPage = 0;
		} else { //게시물이 존재하는 경우
			totalPages = total/size; //총페이지수=전체게시물수/1page당 보여줄 게시물수
			
			if (total%size>0) {      //나머지가 0보다 크면
				totalPages++;        //전체페이수를 1씩증가
			}
			int modVal=currentPage%5; 

			startPage =currentPage/5*5 + 1;
			
			//요청 페이지 수
			if (modVal==0) startPage-=5;
			
			endPage = startPage + 4;
			//endPage가 전체페이수보다크면  endPage값을 전체페이수로 적용           
			if(endPage>totalPages) endPage=totalPages;
		}
	}
	
	
	
	public int getTotal() {
		return total;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public List<Review> getContent() {
		return content;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public int getStartPage() {
		return startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public boolean hasNoReview() {
		return total == 0;
	}
	public boolean hasReview() {
		return total > 0;
	}
	
	@Override
	public String toString() {
		return "reviewPage [total=" + total + ", currentPage=" + currentPage + ", content=" + content + ", totalPages="
				+ totalPages + ", startPage=" + startPage + ", endPage=" + endPage + "]";
	}

	
	
}//class의 끝.
