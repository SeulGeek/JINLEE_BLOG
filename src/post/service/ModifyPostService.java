package post.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import post.dao.PostDao;
import post.model.Post;
import tag.dao.TagDao;

public class ModifyPostService {

	private PostDao postDao = new PostDao();
	private TagDao tagDao = new TagDao();

	public boolean modify(ModifyPostRequest modReq) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			Post post = postDao.selectById(conn, modReq.getPostId());

			if (post == null) {
				throw new ArticleNotFoundException();
			}
			if (!canModify(modReq.getUserId(), post)) {
				throw new PermissionDeniedException();
			}

			postDao.update(conn, modReq.getPostId(), modReq.getBoard().getBoardId(),
					modReq.getBoard().getBoardName(), modReq.getCate().getCateId(), modReq.getCate().getCateName(),
					modReq.getPostImage(), modReq.getPostTitle(), modReq.getPostContent(),
					modReq.isPostVisibility());

			conn.commit();
			return true;

		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);

		} catch (PermissionDeniedException e) {
			JdbcUtil.rollback(conn);
			throw e;
		} finally {
			JdbcUtil.close(conn);
		}
	}

	private boolean canModify(Integer modfyingUserId, Post post) {
		return post.getWriter().getWriterId().equals(modfyingUserId);
	}
}
