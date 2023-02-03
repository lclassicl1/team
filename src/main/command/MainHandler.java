package main.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;

public class MainHandler implements CommandHandler{
	private final static String MAINVIEW = "view/main/main.jsp";
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return MAINVIEW;
	}

}
