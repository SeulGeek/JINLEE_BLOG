package post.service;

import java.util.Date;
import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import post.dao.PostDao;
import post.model.Post;
import tag.dao.TagDao;
import tag.service.RegisterTagService;

public class RegisterPostService {

	private PostDao postDao = new PostDao();
	RegisterTagService registerTagService = new RegisterTagService();

	// register 메서드는 RegisterPostRequest 타입의 req 파라미터를 이용해서 게시글을 등록하고, 결과로 게시글 번호를
	// 리턴한다.
	public Integer register(RegisterPostRequest req) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();

			// 트랜잭션을 시작한다.
			conn.setAutoCommit(false);

			// RegisterPostRequest로부터 post 객체를 생성한다.
			// insert 쿼리를 실행해야 id를 알 수 있으므로 toPost 메서드에서 post 객체를 생성할 때, number 값으로 null을
			// 전달한다.
			Post post = toPost(req);

			// postDao의 insert 메서드를 실행하고, 그 결과를 savedPost에 할당한다.
			// 데이터를 삽입한 경우 savedArticle은 null이 아니고, post 테이블에 추가한 데이터의 주요 키값을 number 값으로
			// 갖는다.
			Post savedPost = postDao.insert(conn, post);
			conn.commit();
			
			// 태그를 등록한다.
			System.out.println("req.getPostTag() : "+req.getPostTag());
			registerTagService.register(conn, req.getPostTag(), savedPost.getPostId());

			// 새로 추가한 게시글 번호 리턴
			return savedPost.getPostId();

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

	// RegisterPostRequest로부터 post 객체를 생성한다.
	private Post toPost(RegisterPostRequest req) {
		Date now = new Date();

		// < post클래스 변수 참고!>
		// public class Post {
		// private Integer postId;
		// private Board board;
		// private Category cate;
		// private Writer writer;
		// private String postImage;
		// private String postTitle;
		// private String postContent;
		// private boolean postVisibility;
		// private Timestamp postWriteDate; // 사용자가 일력한 작성 일시
		// private Timestamp postEditedDate; // 글 등록, 수정한 작성 일시
		// private int postViewNum;

		return new Post(null, req.getBoard(), req.getCate(), req.getWriter(), req.getPostImage(), req.getPostTitle(),
				req.getPostContent(), req.isPostVisibility(), now, now, 0);

	}
}
