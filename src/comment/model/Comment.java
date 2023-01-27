package comment.model;

import java.util.List;

public class Comment {
	private List<CommentList> commentList;

	
	
	public Comment(List<CommentList> commentList) {
		this.commentList = commentList;
	}

	
	
	
	public List<CommentList> getCommentList() {
		return commentList;
	}

	@Override
	public String toString() {
		return "Comment [commentList=" + commentList + "]";
	}
	
	
}
