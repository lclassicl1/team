package review.comment.model;

//review_Content 글 번호, 내용
//integer을 사용한 이유 : null값을 주거나 객체로 사용하기 위함
public class Review_comment_Content {
	
	private Integer review_comment_number;//댓글 번호
	private String review_comment_content;//댓글 내용
	
	public Review_comment_Content(Integer review_comment_number, String review_comment_content) {
		this.review_comment_number = review_comment_number;
		this.review_comment_content = review_comment_content;
	}

	public Integer getReview_comment_number() {
		return review_comment_number;
	}

	public String getReview_comment_content() {
		return review_comment_content;
	}

	@Override
	public String toString() {
		return "Review_comment_Content [review_comment_number=" + review_comment_number + ", review_comment_content="
				+ review_comment_content + "]";
	}
	
	

	
	
}
