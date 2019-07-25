package post.command;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.cate.model.Board;
import board.cate.service.ListBoardService;
import mvc.command.CommandHandler;
import post.model.Tag;
import post.service.ArticleContentNotFoundException;
import post.service.ArticleNotFoundException;
import post.service.PostData;
import post.service.ReadPostService;
import tag.service.ListTagService;

public class ReadPostHandler implements CommandHandler {

	private ReadPostService readService = new ReadPostService();
	private ListTagService listTagService = new ListTagService();

	// 헤더
	private ListBoardService listBoardService = new ListBoardService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String noVal = req.getParameter("no");
		int postId = Integer.parseInt(noVal);
		try {
			// 해당 게시글 내용
			PostData postData = readService.getPost(postId, true);
			req.setAttribute("postData", postData);

			// 해당 게시글의 태그 리스트
			List<Tag> tag = listTagService.getTagListOfPost(postId);
			req.setAttribute("tag", tag);

			// 헤더. 게시판 리스트
			List<Board> boardList = listBoardService.getBoard();
			req.setAttribute("boardList", boardList);

			return "/WEB-INF/view/postRead.jsp";
		} catch (ArticleNotFoundException | ArticleContentNotFoundException e) {
			req.getServletContext().log("no post", e);
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}

}
