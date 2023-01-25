package mvc.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CommandHandler {
	//String 타입의 view를 리턴해주는 메서드
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
}
