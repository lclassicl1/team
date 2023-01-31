package help.model;

import java.util.Map;

public class ModifyRequest {

	private int userNo;
	private int articleNo;
	private String modTitle;
	private String modContent;
	private String modCategory;
	public ModifyRequest(int userNo, int articleNo, String modTitle, String modContent, String modCategory) {
		this.userNo = userNo;
		this.articleNo = articleNo;
		this.modTitle = modTitle;
		this.modContent = modContent;
		this.modCategory = modCategory;
	}
	public int getUserNo() {
		return userNo;
	}
	public int getArticleNo() {
		return articleNo;
	}
	public String getModTitle() {
		return modTitle;
	}
	public String getModContent() {
		return modContent;
	}
	public String getModCategory() {
		return modCategory;
	}
	
	public void validdate(Map<String,Boolean> errors) {
		if(modTitle == null || modTitle.trim().isEmpty()) {
			errors.put("modTitleEmpty",Boolean.TRUE);
		}
	}
}
