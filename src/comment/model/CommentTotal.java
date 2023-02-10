package comment.model;


public class CommentTotal {

	private Comment comment;
	private int total;
	public CommentTotal(Comment comment, int total) {
		this.comment = comment;
		this.total = total;
	}

	public boolean hasNoComm() {
		return total==0;
	}
	public boolean hasComm() {
		return total>0;
	}
	public Comment getComment() {
		return comment;
	}
	public int getTotal() {
		return total;
	}
	
	
}
