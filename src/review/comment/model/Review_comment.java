package review.comment.model;

import java.util.Date;

//리뷰 댓글 정보를 담고있는 클래스
public class Review_comment {
	private int comm_no; //댓글번호
	private String comm_content; //댓글내용
	private Reveiw_conmment_Writer comm_writer;// 작성자 정보
	private Date comm_credate; //만든날짜
	private Date comm_update; //수정날짜
	private String user_id; //유저 id
	private String isshow; //노출여부
	private int comm_volt; //좋아요
	private int review_no; //리뷰 글 번호
	


	public Review_comment(int comm_no, String comm_content, Reveiw_conmment_Writer comm_writer, Date comm_credate,
			Date comm_update, String user_id, String isshow, int comm_volt, int review_no) {
		this.comm_no = comm_no;
		this.comm_content = comm_content;
		this.comm_writer = comm_writer;
		this.comm_credate = comm_credate;
		this.comm_update = comm_update;
		this.user_id = user_id;
		this.isshow = isshow;
		this.comm_volt = comm_volt;
		this.review_no = review_no;
	}


	public Review_comment(Integer comm_no,Reveiw_conmment_Writer comment_Wrtier, String conmment_content,
			Date comm_credate, Date comm_update, String isshow) {
	}


	public Review_comment(int int1, Reveiw_conmment_Writer reveiw_conmment_Writer, Date date, Date date2,
			String string) {
		// TODO Auto-generated constructor stub
	}


	public Review_comment(Integer comm_no, Reveiw_conmment_Writer comm_writer, Date comm_credate, Date comm_update,
			String isshow) {
	}


	public int getComm_no() {
		return comm_no;
	}

	public void setComm_no(int comm_no) {
		this.comm_no = comm_no;
	}

	public String getComm_content() {
		return comm_content;
	}

	public void setComm_content(String comm_content) {
		this.comm_content = comm_content;
	}
	
	
	public Reveiw_conmment_Writer getComm_writer() {
		return comm_writer;
	}
	
	public void setComm_writer(Reveiw_conmment_Writer comm_writer) {
		this.comm_writer = comm_writer;
	}


	public Date getComm_credate() {
		return comm_credate;
	}

	public void setComm_credate(Date comm_credate) {
		this.comm_credate = comm_credate;
	}

	public Date getComm_update() {
		return comm_update;
	}

	public void setComm_update(Date comm_update) {
		this.comm_update = comm_update;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getIsshow() {
		return isshow;
	}

	public void setIsshow(String isshow) {
		this.isshow = isshow;
	}

	public int getComm_volt() {
		return comm_volt;
	}

	public void setComm_volt(int comm_volt) {
		this.comm_volt = comm_volt;
	}

	public int getReview_no() {
		return review_no;
	}

	public void setReview_no(int review_no) {
		this.review_no = review_no;
	}

	@Override
	public String toString() {
		return "Review_comment [comm_no=" + comm_no + ", comm_content=" + comm_content + ", comm_writer=" + comm_writer
				+ ", comm_credate=" + comm_credate + ", comm_update=" + comm_update + ", user_id=" + user_id
				+ ", isshow=" + isshow + ", comm_volt=" + comm_volt + ", review_no=" + review_no + "]";
	}
	

	
	
}
