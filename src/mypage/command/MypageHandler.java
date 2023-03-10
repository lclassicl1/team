package mypage.command;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.fabric.Response;

import auth.model.User;
import mvc.command.CommandHandler;


//마이페이지 보여주는 핸들러
public class MypageHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		User user = (User)req.getSession(false).getAttribute("authUser");
		HttpSession ssesion=req.getSession();
		ssesion.setAttribute("userInfo", user);//로그인한 유저 정보
		
		
		res.sendRedirect(req.getContextPath()+"/view/mypage/mypage.jsp");
		return null;
	}
}
