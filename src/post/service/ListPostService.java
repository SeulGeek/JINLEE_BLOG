package post.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import post.dao.PostDao;
import post.model.Post;

public class ListPostService {

	private PostDao postDao = new PostDao();
	private int size = 10;

	public PostPage getPostPage(int pageNum, String boardName, String cateName) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			
			// 전체 게시글의 개수 리턴
			int total = postDao.selectCount(conn, boardName, cateName);
		
			List<Post> content = postDao.select(
					conn, (pageNum - 1) * size, size, boardName, cateName);
			return new PostPage(total, pageNum, size, content);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public PostPage getPostPage(int pageNum, String boardName) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			
			// 전체 게시글의 개수 리턴
			int total = postDao.selectCount(conn, boardName);
		
			List<Post> content = postDao.select(
					conn, (pageNum - 1) * size, size, boardName);
			return new PostPage(total, pageNum, size, content);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public PostPage getPostPage(int pageNum) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			
			// 전체 게시글의 개수 리턴
			int total = postDao.selectCount(conn);
		
			List<Post> content = postDao.select(
					conn, (pageNum - 1) * size, size);
			return new PostPage(total, pageNum, size, content);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
