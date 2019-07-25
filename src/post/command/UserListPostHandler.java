package post.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.model.Admin;
import admin.service.AdminSettingService;
import board.cate.model.Board;
import board.cate.model.BoardCategory;
import board.cate.model.Category;
import board.cate.service.ListBoardCateService;
import board.cate.service.ListBoardService;
import board.cate.service.ListCategoryService;
import mvc.command.CommandHandler;
import post.model.Post;
import post.model.Tag;
import post.service.TopListPostService;
import post.service.ListPostService;
import post.service.PostPage;
import search.service.SearchPostWithTagService;
import search.service.SearchPostWithTextService;
import tag.service.ListTagService;

public class UserListPostHandler implements CommandHandler {

	String postTitle = "";
	String postContent = "";
	String postSearchText = "";
	String tagName = "";
	String boardName = "";
	String cateName = "";

	private ListPostService listService = new ListPostService();

	// 헤더
	private ListBoardService listBoardService = new ListBoardService();

	// 우측 메뉴
	private AdminSettingService adminSettingService = new AdminSettingService();
	private SearchPostWithTextService searchTextService = new SearchPostWithTextService();
	private SearchPostWithTagService searchTagService = new SearchPostWithTagService();
	private TopListPostService toplistService = new TopListPostService();
	private ListTagService listTagService = new ListTagService();
	private ListBoardCateService listBoardCateService = new ListBoardCateService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {

		// 게시판별로 리스트 나오게  하기
		boardName = req.getParameter("board");
		
		// 카테고리별로 리스트 나오게 하기
		cateName = req.getParameter("cate");
		System.out.println("cateName : "+cateName);

		// 검색창에 검색을 한 경우,
		postTitle = req.getParameter("postTitle");
		postContent = req.getParameter("postContent");
		postSearchText = req.getParameter("postSearchText");

		/*
		 * System.out.println("UserListPostHandler - postTitle : " + postTitle);
		 * System.out.println("UserListPostHandler - postContent : " + postContent);
		 * System.out.println("UserListPostHandler - postSearchText : " +
		 * postSearchText);
		 */

		// 태그로 검색을 한 경우,
		tagName = req.getParameter("tag");
		System.out.println("UserListPostHandler - tagName : " + tagName);

		// pageNo파라미터 값을 이용해서 읽어올 페이지 번호를 구한다.
		String pageNoVal = req.getParameter("pageNo");
		int pageNo = 1;
		if (pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}

		System.out.println("UserListPostHandler1");
		PostPage postPage = null;

		// 검색창에 검색을 한 경우,
		if (postTitle != null || postContent != null) {
			postPage = searchTextService.getSearchPostPage(pageNo, postTitle, postContent, postSearchText);
			/* System.out.println("UserListPostHandler2"); */
		} else if (tagName != null) {
			// 태그로 검색을 한 경우,
			postPage = searchTagService.getSearchPostPage(pageNo, tagName);
		} else if(cateName == null) {
			// 기본 리스트 출력인 경우,
			// listService를 이용해서 지정한 페이지 번호에 해당하는 게시글 데이터를 구한다.
			System.out.println("게시판");
			postPage = listService.getPostPage(pageNo, boardName);
			/* System.out.println("UserListPostHandler3"); */
		} else if(cateName !=null) {
			System.out.println("카테고리");
			postPage = listService.getPostPage(pageNo, boardName, cateName);
		}

		/* System.out.println("UserListPostHandler4"); */
		// postPage 객체를 JSP에서 사용할 수 있도록 request의 postPage 속성에 저장한다.
		req.setAttribute("postPage", postPage);

		// 헤더. 게시판 리스트
		List<Board> boardList = listBoardService.getBoard();
		req.setAttribute("boardList", boardList);

		// 우측 메뉴 내용 저장하기
		// 1. 관리자 정보 (프사, 메일 주소)
		Admin admin = adminSettingService.getAdminSetting(1);
		req.setAttribute("admin", admin);

		// 2. 카테고리 리스트
		List<BoardCategory> boardCate = listBoardCateService.getBoardCategory(boardList);
		req.setAttribute("boardCate", boardCate);
		
		// 3. 게시글 TOP5
		List<Post> post = toplistService.getPostList(5);
		req.setAttribute("postTop5", post);

		// 4. 태그 목록
		List<Tag> tag = listTagService.getAllTagList();
		req.setAttribute("tag", tag);

		return "/WEB-INF/view/userPostList.jsp";
		/* return "post/list.tiles"; */
	}

}