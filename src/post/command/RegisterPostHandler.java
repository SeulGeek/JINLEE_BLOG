package post.command;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import post.model.Writer;
import board.cate.model.Board;
import board.cate.model.BoardCategory;
import board.cate.model.Category;
import board.cate.service.ListBoardCateService;
import board.cate.service.ListBoardService;
import board.cate.service.ListCategoryService;
import auth.service.User;
import mvc.command.CommandHandler;
import post.service.ModifyPostService;
import post.service.RegisterPostRequest;
import post.service.RegisterPostService;
import tag.service.RegisterTagService;

public class RegisterPostHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/postRegister.jsp";

	private RegisterPostService registerPostService = new RegisterPostService();
	private ListBoardService listBoardService = new ListBoardService();
	private ListBoardCateService listBoardCateService = new ListBoardCateService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) {
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);

		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);

		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processForm(HttpServletRequest req, HttpServletResponse res) {

		List<Board> boardList = listBoardService.getBoard();
		req.setAttribute("boardList", boardList);
		
		List<BoardCategory> boardCate = listBoardCateService.getBoardCategory(boardList);
		req.setAttribute("boardCate", boardCate);

		return FORM_VIEW;
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);

		MultipartRequest multi = null;
		int sizeLimit = 10 * 1024 * 1024;
		String savePath = req.getSession().getServletContext().getRealPath("/upload");
		try {
			if (-1 < req.getContentType().indexOf("multipart/form-data")) {

				multi = new MultipartRequest(req, savePath, sizeLimit, "UTF-8", new DefaultFileRenamePolicy());
				// multi = new MultipartRequest(req, savePath);
				System.out.println("savePath:" + savePath);
				System.out.println("ContextPath:" + req.getContextPath());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		User user = (User) req.getSession(false).getAttribute("authUser");
		String filename = multi.getFilesystemName("postImage");
		RegisterPostRequest registerReq = createRegisterRequest(user, multi, filename);
		registerReq.validate(errors);

		if (!errors.isEmpty()) {
			return FORM_VIEW;
		}

		// registerService 를 이용해서 게시글을 등록하고, 등록된 게시글의 ID를 리턴받는다.
		// registerService에서 태그도 등록한다.
		Integer newPostId = registerPostService.register(registerReq);

		if (newPostId != null)
			req.setAttribute("success", true);
		else
			req.setAttribute("success", false);

		return "/WEB-INF/view/postRegisterSuccess.jsp";
	}

	// 참고
	// public RegisterPostRequest(Board board, Category cate, Writer writer, String
	// postImage, String postTitle,
	// String postContent, boolean postVisibility, Date
	// postWriteDate)

	private RegisterPostRequest createRegisterRequest(User user, MultipartRequest multi, String filename) {
		String postTag = multi.getParameter("postTag");
		
		System.out.println("postVisibility : "+Boolean.valueOf(multi.getParameter("postVisibility")));
		return new RegisterPostRequest(new Board(Integer.valueOf(multi.getParameter("boardId")), multi.getParameter("boardName")), new Category(Integer.valueOf(multi.getParameter("cateId")), Integer.valueOf(multi.getParameter("boardId")), multi.getParameter("cateName")), new Writer(user.getId(), user.getName()), filename, multi.getParameter("postTitle"), multi.getParameter("postContent"), Boolean.valueOf(multi.getParameter("postVisibility")), postTag);
	}

}
