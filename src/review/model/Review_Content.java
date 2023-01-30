package review.model;

//review_Content 글 번호, 내용
//integer을 사용한 이유 : null값을 주거나 객체로 사용하기 위함
public class Review_Content {
	
	private Integer review_number;//글 번호
	private String review_content;//내용
	
	
	public Review_Content(Integer review_number, String review_content) {
		super();
		this.review_number = review_number;
		this.review_content = review_content;
	}

	public Integer getReview_number() {
		return review_number;
	}

	public String getReview_content() {
		return review_content;
	}

	@Override
	public String toString() {
		return "Review_Content [review_number=" + review_number + ", review_content=" + review_content + "]";
	}
	
	
}
