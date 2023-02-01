package review.comment.service;

import java.util.Map;

import review.comment.model.Reveiw_conmment_Writer;

//댓글 관련 service클래스
public class Review_comment_Writer_Request {

	private Reveiw_conmment_Writer comment_wrtier;//작성자 정보
	private String conmment_content;//내용

	public Review_comment_Writer_Request(Reveiw_conmment_Writer wrtier, String reveiw_conmment_content) {
		this.comment_wrtier = wrtier;
		conmment_content = reveiw_conmment_content;
	}
	
	public Review_comment_Writer_Request(String userId, int no, String writer_name, String content) {
	}

	public Reveiw_conmment_Writer getcomment_Wrtier() {
		return comment_wrtier;
	}
	public String getReveiw_conmment_content() {
		return conmment_content;
	}
	
	//메서드
		//유효성검사-필수입력체크
		public void validate(Map<String,Boolean> errors) {
			if( conmment_content==null || conmment_content.isEmpty()){
				errors.put("content",Boolean.TRUE);
			}
		}

		@Override
		public String toString() {
			return "Review_comment_Request [wrtier=" + comment_wrtier + ", conmment_content=" + conmment_content + "]";
		}

	
	
}
