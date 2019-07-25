package post.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import post.dao.PostDao;
import post.model.Post;
import tag.dao.TagDao;
import tag.service.DeleteTagService;

public class DeletePostService {

	DeleteTagService deleteTagService = new DeleteTagService();
	private PostDao postDao = new PostDao();

	public boolean delete(int adminId, int postId) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			Post post = postDao.selectById(conn, postId);

			if (post == null) {
				throw new ArticleNotFoundException();
			}
			if (!canDelete(adminId, post)) {
				throw new PermissionDeniedException();
			}

			// 태그 삭제하기
			// postTag_DB에서 해당 postId로 검색했을 때,
			// 나오는 tagId가 postTag DB에서 1개만 검색이 될 때,
			// tag DB에서 해당 tagId의 행을 삭제한다.
			// 이 때, tagId를 외래키로 참조하고 있는 postTag DB의 행도 같이 삭제된다.
			deleteTagService.delete(conn, postId);
			
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			// 글 삭제하기
			// 이 때, postId를 외래키로 참조하고 있는 postTag DB의 행도 같이 삭제된다.
			postDao.delete(conn, postId);

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

	private boolean canDelete(Integer deletingUserId, Post post) {
		return post.getWriter().getWriterId().equals(deletingUserId);
	}
}
