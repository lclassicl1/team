package freeboard.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import freeboard.model.FreePage;
import freeboard.service.SearchBoardService;
import mvc.command.CommandHandler;

public class SearchBoardHandler implements CommandHandler {

	
	SearchBoardService searchBoardService = new SearchBoardService();
	
	private static final String FORM_VIEW="/view/freeboard/freeBoardList.jsp";
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String categorySearch = request.getParameter("categorySearch");
		System.out.println("categorySearch=============="+categorySearch);
		String input = request.getParameter("input");
		System.out.println("input======"+input);
		
		String pageNoVal = request.getParameter("pageNo");
		int pageNum=1;
		if(pageNoVal!=null) {
			pageNum= Integer.parseInt(pageNoVal);
		}
		
		 FreePage freePage = searchBoardService.search(pageNum,categorySearch,input);
		 System.out.println(freePage);
		 request.setAttribute("freePage", freePage);
		
		
		
		
		return FORM_VIEW;
	}

}
