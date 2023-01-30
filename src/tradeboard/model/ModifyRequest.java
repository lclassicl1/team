package tradeboard.model;

import java.util.Map;

public class ModifyRequest {

	private int userNo;
	private int tradeNo;
	private String modTitle;
	private String modContent;
	public ModifyRequest(int userNo, int tradeNo, String modTitle, String modContent) {
		this.userNo = userNo;
		this.tradeNo = tradeNo;
		this.modTitle = modTitle;
		this.modContent = modContent;
	}
	public int getUserNo() {
		return userNo;
	}
	public int getTradeNo() {
		return tradeNo;
	}
	public String getModTitle() {
		return modTitle;
	}
	public String getModContent() {
		return modContent;
	}
	
	public void validdate(Map<String,Boolean> errors) {
		if(modTitle == null || modTitle.trim().isEmpty()) {
			errors.put("modTitleEmpty",Boolean.TRUE);
		}
	}
}
