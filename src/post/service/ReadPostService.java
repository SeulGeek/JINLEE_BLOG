package post.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import post.dao.PostDao;
import post.model.Post;
import post.model.Tag;
import tag.dao.TagDao;

public class ReadPostService {

	private PostDao postDao = new PostDao();

	public PostData getPost(int postId, boolean increaseReadCount) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			Post post = postDao.selectById(conn, postId);
			if (post == null) {
				throw new ArticleNotFoundException();
			}
			if (increaseReadCount) {
				postDao.increaseReadCount(conn, postId);
			}
			return new PostData(post);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
