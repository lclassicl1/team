package index.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;

public class IndexHandler implements CommandHandler {
	private final static String INDEX_VIEW = "/index.jsp";
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("index 핸들러 진입");
		return INDEX_VIEW;
	}

}
