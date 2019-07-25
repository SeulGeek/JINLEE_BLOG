package post.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import post.dao.PostDao;
import post.model.Post;

public class TopListPostService {

	private PostDao postDao = new PostDao();

	public List<Post> getPostList(int size){
		try (Connection conn = ConnectionProvider.getConnection()) {
			
			// 전체 게시글의 개수 리턴
			List<Post> post = postDao.selectTop5(conn, size);
			
			
			return post;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
