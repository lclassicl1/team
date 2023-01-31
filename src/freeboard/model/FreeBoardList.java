package freeboard.model;

import java.util.Date;

public class FreeBoardList {
	private int article_no;
	private String article_category;
	private String free_title;
	private String free_content;
	private Date free_credate;
	private Date free_update;
	private int free_readcnt;
	private String user_id;
	private String isshow;
	private String free_category;
	private int user_no;
	
	
	public FreeBoardList(int article_no,String article_category, String free_title, String free_content, Date free_credate,
			Date free_update, int free_readcnt, String user_id, String isshow, String free_category,
			int user_no) {
		this.article_no = article_no;
		this.article_category = article_category;
		this.free_title = free_title;
		this.free_content = free_content;
		this.free_credate = free_credate;
		this.free_update = free_update;
		this.free_readcnt = free_readcnt;
		this.user_id = user_id;
		this.isshow = isshow;
		this.free_category = free_category;
		this.user_no = user_no;
	}
	
	
	public int getArticle_no() {
		return article_no;
	}
	
	public String getArticle_category() {
		return article_category;
	}

	public String getFree_title() {
		return free_title;
	}

	public String getFree_content() {
		return free_content;
	}

	public Date getFree_createdate() {
		return free_credate;
	}

	public Date getFree_updatedate() {
		return free_update;
	}

	public int getFree_readcnt() {
		return free_readcnt;
	}

	public String getUser_id() {
		return user_id;
	}

	public String getIsshow() {
		return isshow;
	}

	public String getFree_category() {
		return free_category;
	}

	public int getUser_no() {
		return user_no;
	}


	@Override
	public String toString() {
		return "FreeBoardList [article_no=" + article_no + ", article_category=" + article_category + ", free_title="
				+ free_title + ", free_content=" + free_content + ", free_credate=" + free_credate + ", free_update="
				+ free_update + ", free_readcnt=" + free_readcnt + ", user_id=" + user_id + ", isshow=" + isshow
				+ ", free_category=" + free_category + ", user_no=" + user_no + "]";
	}


	
	
	
	
}
