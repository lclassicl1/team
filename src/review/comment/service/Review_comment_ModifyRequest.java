package review.comment.service;

import java.util.Map;

//p666
//글 수정을 위한    수정하는 사용자id,수정할 글번호,수정할 제목,수정할 내용
// 유효성검사기능을 제공하는 클래스 
public class Review_comment_ModifyRequest {

	private String comment_userId; //수정하는 사용자id
	private int comment_reviewNumber; //수정할 글번호
	private String comment_writer_name; //작성자name
	private String comment_content;//수정할 내용
	
	public Review_comment_ModifyRequest(String comment_userId, int comment_reviewNumber, String comment_writer_name,
			String comment_content) {
		this.comment_userId = comment_userId;
		this.comment_reviewNumber = comment_reviewNumber;
		this.comment_writer_name = comment_writer_name;
		this.comment_content = comment_content;
	}


	public String getComment_userId() {
		return comment_userId;
	}




	public int getComment_reviewNumber() {
		return comment_reviewNumber;
	}




	public String getComment_writer_name() {
		return comment_writer_name;
	}




	public String getComment_content() {
		return comment_content;
	}

		


	@Override
	public String toString() {
		return "Review_comment_ModifyRequest [comment_userId=" + comment_userId + ", comment_reviewNumber="
				+ comment_reviewNumber + ", comment_writer_name=" + comment_writer_name + ", comment_content="
				+ comment_content + "]";
	}

	
	
	
	
	
	
}
