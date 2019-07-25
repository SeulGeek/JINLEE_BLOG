package post.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import post.service.ListPostService;
import post.service.PostPage;

public class ListPostHandler implements CommandHandler {

	private ListPostService listService = new ListPostService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) 
			throws Exception {
		
		//pageNo파라미터 값을 이용해서 읽어올 페이지 번호를 구한다.
		String pageNoVal = req.getParameter("pageNo");
		int pageNo = 1;
		if (pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}
		
		// listService를 이용해서 지정한 페이지 번호에 해당하는 게시글 데이터를 구한다.
		PostPage postPage = listService.getPostPage(pageNo);
		
		// postPage 객체를 JSP에서 사용할 수 있도록 request의 postPage 속성에 저장한다.
		req.setAttribute("postPage", postPage);
		return "/WEB-INF/view/postList.jsp";
	}

}
