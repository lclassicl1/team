package review.service;

import java.util.Map;
import review.model.Review_Writer;

//p641
//글쓰기 요청시 사용하게 되는 클래스+유효성검사기능
public class Review_WriteRequest {

	//필드
	private Review_Writer writer;//작성자정보
	/*(String writer_id;//작성자id,
	 * String writer_name;//작성자명)*/
	 
	private String title;
	private String content;
	
	//생성자
	public Review_WriteRequest(Review_Writer writer,String title,String content) {
		this.writer=writer;
		this.title=title;
		this.content=content;
	}
	
	public Review_Writer getWriter() {
		return writer;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	//메서드
	//유효성검사-필수입력체크
	public void validate(Map<String,Boolean> errors) {
		if( title==null || title.isEmpty()){
			errors.put("title",Boolean.TRUE);
		}
		if( content==null || content.isEmpty()){
			errors.put("content",Boolean.TRUE);
		}
	}

	@Override
	public String toString() {
		return "review_WriteRequest [writer=" + writer + ", title=" + title + ", content=" + content + "]";
	}

	
	
	
}






