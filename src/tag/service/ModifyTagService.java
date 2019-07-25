package tag.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import post.dao.PostDao;
import post.model.Post;
import post.service.ModifyPostRequest;
import post.service.PermissionDeniedException;
import post.service.RegisterPostRequest;
import tag.dao.TagDao;

public class ModifyTagService {

	RegisterTagService registerTagService = new RegisterTagService();
	DeleteTagService deleteTagService = new DeleteTagService();

	public boolean modify(Connection conn, int postId, String postTag) {
		try {
			// 태그 삭제하기
			deleteTagService.delete(conn, postId);
			
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			// 태그 등록하기
			registerTagService.register(conn, postTag, postId);
			
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
}
