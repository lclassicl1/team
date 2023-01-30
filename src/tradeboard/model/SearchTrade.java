package tradeboard.model;

public class SearchTrade {

	private int startRow;
	private int size;
	private String input;
	public SearchTrade(int startRow, int size, String input) {
		this.startRow = startRow;
		this.size = size;
		this.input = input;
	}
	public int getStartRow() {
		return startRow;
	}
	public int getSize() {
		return size;
	}
	public String getInput() {
		return input;
	}
	
	
}
