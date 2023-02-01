package review.model;

//작성자명 데이터 관리 클래스(예정)
public class Review_Writer {

	private String review_writer_name;
	private String review_writer_id;
	private int review_writer_no;
	
	public Review_Writer(String review_writer_name, String review_writer_id, int review_writer_no) {
		this.review_writer_name = review_writer_name;
		this.review_writer_id = review_writer_id;
		this.review_writer_no = review_writer_no;
	}
	
	

	public Review_Writer(String review_writer_name, String review_writer_id) {
		this.review_writer_name = review_writer_name;
		this.review_writer_id = review_writer_id;
	}



	public Review_Writer(String review_writer_name) {
		this.review_writer_name = review_writer_name;
	}



	public String getReview_writer_name() {
		return review_writer_name;
	}

	public String getReview_writer_id() {
		return review_writer_id;
	}

	public int getReview_writer_no() {
		return review_writer_no;
	}

	@Override
	public String toString() {
		return "Review_Writer [review_writer_name=" + review_writer_name + ", review_writer_id=" + review_writer_id
				+ ", review_writer_no=" + review_writer_no + "]";
	}

	
	
	
	
}
