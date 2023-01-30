package comment.model;

import java.util.Date;

public class CommentList {
	
	private int comm_no;
	private String comm_content;
	private Date comm_credate;
	private String user_id;
	private String isshow;
	private int comm_volt;
	private int free_no;
	
	
	
	
	
	public CommentList(int comm_no, String comm_content, Date comm_credate, String user_id, String isshow,
			int comm_volt, int free_no) {
		this.comm_no = comm_no;
		this.comm_content = comm_content;
		this.comm_credate = comm_credate;
		this.user_id = user_id;
		this.isshow = isshow;
		this.comm_volt = comm_volt;
		this.free_no = free_no;
	}


	public int getComm_no() {
		return comm_no;
	}





	public String getComm_content() {
		return comm_content;
	}





	public Date getComm_credate() {
		return comm_credate;
	}





	public String getUser_id() {
		return user_id;
	}





	public String getIsshow() {
		return isshow;
	}





	public int getComm_volt() {
		return comm_volt;
	}





	public int getFree_no() {
		return free_no;
	}





	@Override
	public String toString() {
		return "CommentList [comm_no=" + comm_no + ", comm_content=" + comm_content + ", comm_credate=" + comm_credate
				+ ", user_Id=" + user_id + ", isshow=" + isshow + ", comm_volt=" + comm_volt + ", free_no="
				+ free_no + "]";
	}
	
	
	
	
}
