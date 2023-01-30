package helper.model;

import java.util.Map;

public class ModifyRequest {

	private int userNo;
	private int helperNo;
	private String modTitle;
	private String modContent;
	private String modCategory;
	public ModifyRequest(int userNo, int helperNo, String modTitle, String modContent, String modCategory) {
		this.userNo = userNo;
		this.helperNo = helperNo;
		this.modTitle = modTitle;
		this.modContent = modContent;
		this.modCategory = modCategory;
	}
	public int getUserNo() {
		return userNo;
	}
	public int getHelperNo() {
		return helperNo;
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
