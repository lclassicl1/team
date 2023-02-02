package notice.model;

public class SearchNotice {

	private int startRow;
	private int size;
	private String input;
	public SearchNotice(int startRow, int size, String input) {
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
