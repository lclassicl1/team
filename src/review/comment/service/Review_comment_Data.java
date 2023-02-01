package review.comment.service;


import review.comment.model.Review_comment;
import review.comment.model.Review_comment_Content;

//p657
//DB의 article테이블과 article_content테이블 관련 데이터를 저장,제공하는 클래스
public class Review_comment_Data {
	//필드
	private Review_comment review;//리뷰
	private Review_comment_Content content;//내용
	
	//생성자
	public Review_comment_Data(Review_comment review, Review_comment_Content content) {
		this.review = review;
		this.content = content;
	}

	public Review_comment getReview() {
		return review;
	}
	
	
	public Review_comment_Content getContent() {
		return content;
	}

	@Override
	public String toString() {
		return "Review_comment_Data [review=" + review + ", content=" + content + "]";
	}

	


	
}












