package review.service;


import review.model.Review;
import review.model.Review_Content;

//p657
//DB의 article테이블과 article_content테이블 관련 데이터를 저장,제공하는 클래스
public class Review_Data {
	//필드
	private Review review;//리뷰
	private Review_Content content;//내용
	
	//생성자
	public Review_Data(Review review, Review_Content content) {
		super();
		this.review = review;
		this.content = content;
	}

	public Review getReview() {
		return review;
	}

	public Review_Content getContent() {
		return content;
	}


	@Override
	public String toString() {
		return "Review_Data [review=" + review + ", content=" + content + "]";
	}



	
}












