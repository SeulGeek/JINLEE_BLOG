package post.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mvc.command.CommandHandler;
import post.model.Post;
import post.service.TopListPostService;

public class TopListPostHandler implements CommandHandler {

	private TopListPostService toplistService = new TopListPostService();
	private static final String FORM_VIEW = "/WEB-INF/view/dashBoard.jsp";

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) {

		// 1. 게시글 TOP10
		List<Post> post = toplistService.getPostList(10);
		req.setAttribute("post", post);

		return FORM_VIEW;
	}
}