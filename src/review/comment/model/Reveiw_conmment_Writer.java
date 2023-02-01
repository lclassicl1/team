package review.comment.model;

public class Reveiw_conmment_Writer {

	private String review_comment_writer_name;
	private String review_comment_writer_id;
	
	
	public Reveiw_conmment_Writer(String review_comment_writer_name, String review_comment_writer_id) {
		this.review_comment_writer_name = review_comment_writer_name;
		this.review_comment_writer_id = review_comment_writer_id;
	}
	
	public Reveiw_conmment_Writer(String review_comment_writer_name) {
			 this.review_comment_writer_name = review_comment_writer_name;
	}

	public String getReview_comment_writer_name() {
		return review_comment_writer_name;
	}
	public void setReview_comment_writer_name(String review_comment_writer_name) {
		this.review_comment_writer_name = review_comment_writer_name;
	}
	public String getReview_comment_writer_id() {
		return review_comment_writer_id;
	}
	public void setReview_comment_writer_id(String review_comment_writer_id) {
		this.review_comment_writer_id = review_comment_writer_id;
	}
	
	@Override
	public String toString() {
		return "Reveiw_conmment_Writer [review_comment_writer_name=" + review_comment_writer_name
				+ ", review_comment_writer_id=" + review_comment_writer_id + "]";
	}
	
	
	
}

