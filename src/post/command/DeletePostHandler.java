package post.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import mvc.command.CommandHandler;
import post.service.ArticleNotFoundException;
import post.service.DeletePostService;
import post.service.ModifyPostService;
import post.service.PermissionDeniedException;
import post.service.PostData;
import post.service.ReadPostService;

public class DeletePostHandler implements CommandHandler {

	private ReadPostService readService = new ReadPostService();
	private DeletePostService deleteService = new DeletePostService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String noVal = req.getParameter("no");
		int postId = Integer.parseInt(noVal);

		PostData postData = readService.getPost(postId, false);

		User authUser = (User) req.getSession().getAttribute("authUser");

		if (!canModify(authUser, postData)) {
			res.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}

		try {
			// 게시글을 삭제한다. 이때, 태그도 같이 삭제한다.
			boolean isSuccess = deleteService.delete(authUser.getId(),postId);
			if (isSuccess)
				req.setAttribute("success", true);
			else
				req.setAttribute("success", false);

			return "/WEB-INF/view/postDeleteSuccess.jsp";

		} catch (ArticleNotFoundException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		} catch (PermissionDeniedException e) {
			res.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}
	}

	private boolean canModify(User authUser, PostData postData) {
		int writerId = postData.getPost().getWriter().getWriterId();
		return authUser.getId().equals(writerId);
	}

}
