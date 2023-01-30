package help.model;

public class SearchHelp {

	private int startRow;
	private int size;
	private String category;
	private String input;
	public SearchHelp(int startRow, int size, String category, String input) {
		this.startRow = startRow;
		this.size = size;
		this.category = category;
		this.input = input;
	}
	public int getStartRow() {
		return startRow;
	}
	public int getSize() {
		return size;
	}
	public String getCategory() {
		return category;
	}
	public String getInput() {
		return input;
	}
	
	
}
