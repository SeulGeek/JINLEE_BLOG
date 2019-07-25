package search.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import post.dao.PostDao;
import post.model.Post;
import post.service.PostPage;

public class SearchPostWithTextService {

	private PostDao postDao = new PostDao();
	private int size = 10;

	public PostPage getSearchPostPage(int pageNum, String postTitle, String postContent, String postSearchText) {

		try (Connection conn = ConnectionProvider.getConnection()) {

			System.out.println("SearchPostService1");
			// 검색된 전체 게시글의 개수 리턴
			int total = postDao.selectSearchCount(conn, postTitle, postContent, postSearchText);
			
			System.out.println("SearchPostService2");
			List<Post> content = postDao.selectSearchedPost(conn, (pageNum - 1) * size, size, postTitle, postContent,
					postSearchText);
			System.out.println("SearchPostService3");
			
			return new PostPage(total, pageNum, size, content);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
