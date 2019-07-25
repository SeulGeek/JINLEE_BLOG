package tag.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import post.dao.PostDao;
import post.model.Post;
import post.model.Tag;
import tag.dao.TagDao;

public class ListTagService {

	private TagDao tagDao = new TagDao();

	public List<Tag> getAllTagList() {
		try (Connection conn = ConnectionProvider.getConnection()) {

			// 전체 태그 리스트 가져오기
			List<Tag> tag = tagDao.selectAllTag(conn);

			return tag;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Tag> getTagListOfPost(int postId) {
		try (Connection conn = ConnectionProvider.getConnection()) {

			// 해당 게시글의 태그 리스트 가져오기
			List<Tag> tag = tagDao.selectPostTag(conn, postId);

			return tag;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
