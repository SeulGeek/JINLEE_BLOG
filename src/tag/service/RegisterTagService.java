package tag.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import post.service.RegisterPostRequest;
import tag.dao.TagDao;

public class RegisterTagService {

	private TagDao tagDao = new TagDao();

	// register 메서드는 RegisterPostRequest 타입의 req 파라미터를 이용해서 게시글을 등록하고, 결과로 게시글 번호를
	// 리턴한다.

	public boolean register(Connection conn, String postTag, int postId) {
		try {

			Boolean isSavedTag = tagDao.insert(conn, postTag, postId);

			if (isSavedTag == false) {
				throw new RuntimeException("fail to insert teq");
			}

			conn.commit();

			return true;

			// 익셉션이 발생하면, 트랜잭션을 롤백한다.
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} catch (RuntimeException e) {
			JdbcUtil.rollback(conn);
			throw e;
		} finally {
			JdbcUtil.close(conn);
		}
	}
}
