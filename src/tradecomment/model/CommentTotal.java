package tradecomment.model;

import java.util.List;

public class CommentTotal {

	private List<Comment> commentList;
	private int total;
	public CommentTotal(List<Comment> commentList, int total) {
		this.commentList = commentList;
		this.total = total;
	}

	public boolean hasNoComm() {
		return total==0;
	}
	public boolean hasComm() {
		return total>0;
	}
	public List<Comment> getComment() {
		return commentList;
	}
	public int getTotal() {
		return total;
	}
	
	
}
