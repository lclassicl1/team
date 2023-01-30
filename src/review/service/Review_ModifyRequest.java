package review.service;

import java.util.Map;

//p666
//글 수정을 위한    수정하는 사용자id,수정할 글번호,수정할 제목,수정할 내용
// 유효성검사기능을 제공하는 클래스 
public class Review_ModifyRequest {

	private String userId; //수정하는 사용자id
	private int reviewNumber; //수정할 글번호
	private String writer_name; //작성자name
	private String title; //수정할 제목
	private String content;//수정할 내용
	
	public Review_ModifyRequest(String userId, int reviewNumber, String writer_name, String title, String content) {
		this.userId = userId;
		this.reviewNumber = reviewNumber;
		this.writer_name = writer_name;
		this.title = title;
		this.content = content;
	}

	public String getUserId() {
		return userId;
	}

	public int getReviewNumber() {
		return reviewNumber;
	}

	public String getWriter_name() {
		return writer_name;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	//유효성검사-p667 35라인
		public void validate(Map<String,Boolean> errors) {
			if(title==null || title.trim().isEmpty()) {
				errors.put("title",Boolean.TRUE);
			}
		}
	
	@Override
	public String toString() {
		return "review_ModifyRequest [userId=" + userId + ", reviewNumber=" + reviewNumber + ", writer_name="
				+ writer_name + ", title=" + title + ", content=" + content + "]";
	}

	
	
	
}
