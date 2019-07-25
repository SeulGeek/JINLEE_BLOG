package post.command;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import auth.service.User;
import board.cate.model.Board;
import board.cate.model.BoardCategory;
import board.cate.model.Category;
import board.cate.service.ListBoardCateService;
import board.cate.service.ListBoardService;
import jdbc.connection.ConnectionProvider;
import mvc.command.CommandHandler;
import post.model.Tag;
import post.service.ArticleNotFoundException;
import post.service.ModifyPostRequest;
import post.service.ModifyPostService;
import post.service.PermissionDeniedException;
import post.service.PostData;
import post.service.ReadPostService;
import tag.dao.TagDao;
import tag.service.ListTagService;
import tag.service.ModifyTagService;

public class ModifyPostHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/postModify.jsp";

	private ReadPostService readService = new ReadPostService();
	private ModifyPostService modifyPostService = new ModifyPostService();
	private ModifyTagService modifyTagService = new ModifyTagService();
	private ListBoardService listBoardService = new ListBoardService();
	private ListTagService listTagService = new ListTagService();
	private ListBoardCateService listBoardCateService = new ListBoardCateService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
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
		try {
			String noVal = req.getParameter("no");
			Integer postId = Integer.parseInt(noVal);
			PostData postData = readService.getPost(postId, false);
			User authUser = (User) req.getSession().getAttribute("authUser");
			if (!canModify(authUser, postData)) {
				res.sendError(HttpServletResponse.SC_FORBIDDEN);
				return null;
			}

			// 게시판 리스트 가져오기
			List<Board> boardList = listBoardService.getBoard();

			if (boardList != null)
				req.setAttribute("boardList", boardList);

			// 참고
			// public ModifyPostRequest(Integer userId, Integer postId, Board board,
			// Category cate, Writer writer,
			// String postImage, String postTitle, String postContent, boolean
			// postVisibility)

			// 태그 리스트 가져오기
			List<Tag> tagList = listTagService.getTagListOfPost(postId);
			// System.out.println("tagList : "+tagList);
			// 가져온 태그 '#'로 구분해서 하나로 합치기
			String tag = tagListJoin(tagList);

			ModifyPostRequest modReq = new ModifyPostRequest(authUser.getId(), postId, postData.getPost().getBoard(),
					postData.getPost().getCate(), postData.getPost().getPostImage(),
					postData.getPost().getPostTitle(), postData.getPost().getPostContent(),
					postData.getPost().getPostVisibility(), tag);

			List<BoardCategory> boardCate = listBoardCateService.getBoardCategory(boardList);
			req.setAttribute("boardCate", boardCate);
			
			req.setAttribute("modReq", modReq);

			return FORM_VIEW;

		} catch (ArticleNotFoundException e) {
			try {
				res.sendError(HttpServletResponse.SC_NOT_FOUND);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	// 가져온 태그 '#'로 구분해서 하나로 합치기
	private String tagListJoin(List<Tag> tagList) {
		String tag = "";
		for (int i = 0; i < tagList.size(); i++) {
			tag = tag + "#" + tagList.get(i).getTagName();
		}
		/* System.out.println("tag : "+tag); */
		return tag;
	}

	private boolean canModify(User authUser, PostData postData) {
		int writerId = postData.getPost().getWriter().getWriterId();
		return authUser.getId().equals(writerId);
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		User authUser = (User) req.getSession().getAttribute("authUser");

		MultipartRequest multi = null;
		int sizeLimit = 10 * 1024 * 1024;
		String savePath = req.getSession().getServletContext().getRealPath("/upload");
		try {
			if (-1 < req.getContentType().indexOf("multipart/form-data")) {

				multi = new MultipartRequest(req, savePath, sizeLimit, "UTF-8", new DefaultFileRenamePolicy());
				// multi = new MultipartRequest(req, savePath);
				System.out.println("작동되려나1");
				System.out.println("savePath:" + savePath);
				System.out.println("ContextPath:" + req.getContextPath());
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("작동되려나2");
		}

		String noVal = multi.getParameter("no");
		int postId = Integer.parseInt(noVal);

		String filename = multi.getFilesystemName("postImage");

		// 참고
		// public ModifyPostRequest(Integer userId, Integer postId, Board board,
		// Category cate,
		// String postImage, String postTitle, String postContent, boolean
		// postVisibility)

		ModifyPostRequest modReq = new ModifyPostRequest(authUser.getId(), postId,
				new Board(Integer.valueOf(multi.getParameter("boardId")), multi.getParameter("boardName")),
				new Category(Integer.valueOf(multi.getParameter("cateId")),
						Integer.valueOf(multi.getParameter("boardId")), multi.getParameter("cateName")),
				filename, multi.getParameter("postTitle"), multi.getParameter("postContent"),
				Boolean.valueOf(multi.getParameter("postVisibility")), multi.getParameter("postTag"));

		// req.setAttribute("modReq", modReq);

		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);

		modReq.validate(errors);

		if (!errors.isEmpty()) {
			return FORM_VIEW;
		}
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();

			// 트랜잭션을 시작한다.
			conn.setAutoCommit(false);

			// 게시글 수정하기
			boolean isSuccess = modifyPostService.modify(modReq);

			// 해당 게시글의 태그 수정하기
			modifyTagService.modify(conn, postId, modReq.getPostTag());

			if (isSuccess)
				req.setAttribute("success", true);
			else
				req.setAttribute("success", false);

			return "/WEB-INF/view/postModifySuccess.jsp";

		} catch (ArticleNotFoundException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		} catch (PermissionDeniedException e) {
			res.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}
	}

}
