package review.model;

import java.util.Date;


//review 테이블 데이터 관리
//integer을 사용한 이유 : null값을 주거나 객체로 사용하기 위함
public class Review {
	private Integer review_number; //글번호 review_number
	private Review_Writer review_writer; //작성자정보(review_writer_id, review_writer_name;//작성자명)
	private String review_title;//제목review_title
	private Date review_credate;//최초작성일  review_credate
	private Date review_update;//마지막수정일 review_update
	private int review_read_cnt; //조회수 review_read_cnt
	private String review_isshow;//노출(삭제)여부.기본Y. Y(노출),N(미노출,삭제된 데이터)
	
	public Review(Integer review_number, Review_Writer review_writer, String review_title, Date review_credate,
			Date review_update, int review_read_cnt, String review_isshow) {
		super();
		this.review_number = review_number;
		this.review_writer = review_writer;
		this.review_title = review_title;
		this.review_credate = review_credate;
		this.review_update = review_update;
		this.review_read_cnt = review_read_cnt;
		this.review_isshow = review_isshow;
	}

	public Integer getReview_number() {
		return review_number;
	}

	public Review_Writer getReview_writer() {
		return review_writer;
	}

	public String getReview_title() {
		return review_title;
	}

	public Date getReview_credate() {
		return review_credate;
	}

	public Date getReview_update() {
		return review_update;
	}

	public int getReview_read_cnt() {
		return review_read_cnt;
	}

	public String getReview_isshow() {
		return review_isshow;
	}

	@Override
	public String toString() {
		return "Review [review_number=" + review_number + ", review_writer=" + review_writer + ", review_title="
				+ review_title + ", review_credate=" + review_credate + ", review_update=" + review_update
				+ ", review_read_cnt=" + review_read_cnt + ", review_isshow=" + review_isshow + "]";
	}

	
	
	
	
}
