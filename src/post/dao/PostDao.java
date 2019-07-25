package post.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import board.cate.model.Board;
import board.cate.model.Category;
import post.model.Writer;
import jdbc.JdbcUtil;
import post.model.Post;
import post.model.Tag;

public class PostDao {

	// 게시글 등록하기
	public Post insert(Connection conn, Post post) throws SQLException {
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("insert into post "
					+ "(board_id,board_name,cate_id,cate_name,writer_id,writer_nick,post_image,post_title,post_content,post_visibility,post_write_date,post_edited_date,post_view_num)"
					+ "values (?,?,?,?,?,?,?,?,?,?,?,?,0)");
			pstmt.setInt(1, post.getBoard().getBoardId());
			pstmt.setString(2, post.getBoard().getBoardName());
			pstmt.setInt(3, post.getCate().getCateId());
			pstmt.setString(4, post.getCate().getCateName());
			pstmt.setInt(5, post.getWriter().getWriterId());
			pstmt.setString(6, post.getWriter().getWriterNick());
			pstmt.setString(7, post.getPostImage());
			pstmt.setString(8, post.getPostTitle());
			pstmt.setString(9, post.getPostContent());
			pstmt.setBoolean(10, post.getPostVisibility());
			pstmt.setTimestamp(11, toTimestamp(post.getPostWriteDate()));
			pstmt.setTimestamp(12, toTimestamp(post.getPostEditedDate()));
			int insertedCount = pstmt.executeUpdate();

			if (insertedCount > 0) {
				stmt = conn.createStatement();

				// post테이블에 새롭게 추가된 행의 postId 값을 구한다.
				rs = stmt.executeQuery("select last_insert_id() from post");
				if (rs.next()) {
					Integer newNo = rs.getInt(1);
					// post 테이블에 추가한 데이터를 담은 post 객체를 리턴한다.
					// insert에 성공하면, post 객체를 리턴하고, 실패하면 null을 리턴한다.
					return new Post(newNo, post.getBoard(), post.getCate(), post.getWriter(), post.getPostImage(),
							post.getPostTitle(), post.getPostContent(), post.getPostVisibility(),
							toTimestamp(post.getPostWriteDate()), toTimestamp(post.getPostEditedDate()), 0);
				}
			}
			return null;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
			JdbcUtil.close(pstmt);
		}
	}

	private Timestamp toTimestamp(Date date) {
		return new Timestamp(date.getTime());
	}

	// 게시글 목록을 보여주려면, 다음 두 데이터가 필요하다.
	// 1. 페이지 개수를 구하기 위한 전체 개시글 개수
	// 2. 지정한 행 번호에 해당하는 게시글 목록

	// 1. 페이지 개수를 구하기 위한 전체 게시글 개수 (post 테이블의 전체 레코드 수를 리턴한다.)
	public int selectCount(Connection conn, String boardName, String cateName) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select count(*) from post where board_name = ? && cate_name =?");
			pstmt.setString(1, boardName);
			pstmt.setString(2, cateName);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public int selectCount(Connection conn, String boardName) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select count(*) from post where board_name = ?");
			pstmt.setString(1, boardName);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public int selectCount(Connection conn) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select count(*) from post");

			if (rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}

	// 1-1. 검색된 전체 게시글 개수 with text
	public int selectSearchCount(Connection conn, String title, String content, String text) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		String textLike = '%' + text + '%';

		/*
		 * System.out.println("post_dao - postTitle : " + title);
		 * System.out.println("post_dao - postContent : " + content);
		 * System.out.println("post_dao - postSearchText : " + text);
		 */

		try {
			stmt = conn.createStatement();

			if (text == null) {
				return 0;
			}

			if (title != null && !title.isEmpty() && content != null && !content.isEmpty()) {
				rs = stmt.executeQuery("select count(*) from post where post_title like '" + textLike
						+ "' or post_content like '" + textLike + "'");
				System.out.println("PostDao : title, content 둘 다 있음");
			} else {
				if (title != null && !title.isEmpty()) {
					rs = stmt.executeQuery("select count(*) from post where post_title like '" + textLike + "'");
					System.out.println("PostDao : title 하나만");
				} else {
					rs = stmt.executeQuery("select count(*) from post where post_content like '" + textLike + "'");
					System.out.println("PostDao : content 하나만");
				}
			}

			if (rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}

	// 1-2. 검색된 전체 게시글 개수 with tag
	public int selectSearchCount(Connection conn, String tagName) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PreparedStatement pstmtCount = null;
		ResultSet rsCount = null;

		// System.out.println("post_dao - tagName : " + tagName);

		try {
			// 태그 명으로 태그 id 알아내기
			pstmt = conn.prepareStatement("select tag_id from tag where tag_name = ?");
			pstmt.setString(1, tagName);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				int tagId = rs.getInt(1);
				// System.out.println("post_dao - tagId : " + tagId);
				// 태그 id로 postId 알아내기
				pstmtCount = conn.prepareStatement(
						"select count(*) from post where post_id in (select post_id from post_tag where tag_id = ?)");
				pstmtCount.setInt(1, tagId);
				rsCount = pstmtCount.executeQuery();
				if (rsCount.next()) {
					// postId로 post 데이터 가져와서 List에 넣기
					return rsCount.getInt(1);
				}
				return 0;

			}

			if (tagName == null) {
				return 0;
			}

		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return 0;
	}

	// 2. 지정한 행 번호에 해당하는 게시글 목록 ()
	public List<Post> select(Connection conn, int startRow, int size) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// limit : 쿼리 실행 결과 중 일부 레코드만 조회할 때 사용
			// limit 첫 번째 행 번호, 읽어올 레코드 개수
			pstmt = conn.prepareStatement(
					"select * from post " + "order by post_id desc limit ?, ?");
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, size);
			rs = pstmt.executeQuery();
			List<Post> result = new ArrayList<>();
			while (rs.next()) {
				// resultSet에서 데이터를 읽어와 Post 객체를 생성한다.
				result.add(convertArticle(rs));
			}
			return result;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public List<Post> select(Connection conn, int startRow, int size, String boardName) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// limit : 쿼리 실행 결과 중 일부 레코드만 조회할 때 사용
			// limit 첫 번째 행 번호, 읽어올 레코드 개수
			pstmt = conn.prepareStatement(
					"select * from post " +"where board_name=?"+ "order by post_id desc limit ?, ?");
			pstmt.setString(1, boardName);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, size);
			rs = pstmt.executeQuery();
			List<Post> result = new ArrayList<>();
			while (rs.next()) {
				// resultSet에서 데이터를 읽어와 Post 객체를 생성한다.
				result.add(convertArticle(rs));
			}
			return result;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public List<Post> select(Connection conn, int startRow, int size, String boardName, String cateName) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// limit : 쿼리 실행 결과 중 일부 레코드만 조회할 때 사용
			// limit 첫 번째 행 번호, 읽어올 레코드 개수
			pstmt = conn.prepareStatement(
					"select * from post " +"where board_name=?"+ "&& cate_name =?"+"order by post_id desc limit ?, ?");
			pstmt.setString(1, boardName);
			pstmt.setString(2, cateName);
			pstmt.setInt(3, startRow);
			pstmt.setInt(4, size);
			rs = pstmt.executeQuery();
			List<Post> result = new ArrayList<>();
			while (rs.next()) {
				// resultSet에서 데이터를 읽어와 Post 객체를 생성한다.
				result.add(convertArticle(rs));
			}
			return result;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	// 2-1. 지정한 행 번호에 해당, 검색된 게시글 목록 ()
	public List<Post> selectSearchedPost(Connection conn, int startRow, int size, String title, String content,
			String text) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// limit : 쿼리 실행 결과 중 일부 레코드만 조회할 때 사용
			// limit 첫 번째 행 번호, 읽어올 레코드 개수
			if (title != null && !title.isEmpty() && content != null && !content.isEmpty()) {
				pstmt = conn.prepareStatement(
						"select * from post where post_title like ? or post_content like ? order by post_id desc limit ?, ?");

				pstmt.setString(1, '%' + text + '%');
				pstmt.setString(2, '%' + text + '%');
				pstmt.setInt(3, startRow);
				pstmt.setInt(4, size);
			} else {

				if (title != null && !title.isEmpty()) {
					pstmt = conn.prepareStatement(
							"select * from post where post_title like ? order by post_id desc limit ?, ?");

				} else {
					pstmt = conn.prepareStatement(
							"select * from post where post_content like ? order by post_id desc limit ?, ?");

				}
				pstmt.setString(1, '%' + text + '%');
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, size);
			}
			rs = pstmt.executeQuery();
			List<Post> result = new ArrayList<>();
			while (rs.next()) {
				// resultSet에서 데이터를 읽어와 Post 객체를 생성한다.
				result.add(convertArticle(rs));
			}
			return result;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	// 2-2. 지정한 행 번호에 해당, 태그로 검색된 게시글 목록 ()
	public List<Post> selectSearchedPost(Connection conn, int startRow, int size, String tagName) throws SQLException {
		PreparedStatement pstmt = null;
		PreparedStatement pstmtgetPost = null;
		ResultSet rs = null;
		ResultSet rsgetPost = null;
		int tagId;
		List<Post> result = new ArrayList<>();

		try {
			// tagName으로 tagId가져오기
			System.out.println("postDao - selectSearchedPost - tagName : " + tagName);
			pstmt = conn.prepareStatement("select tag_id from tag where tag_name = ?");
			pstmt.setString(1, tagName);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				tagId = rs.getInt(1);
				// tagId로 postId 검색, postId에 해당하는 post 데이터 가져와서 list에 add하기
				pstmtgetPost = conn.prepareStatement(
						"select * from post where post_id in (select post_id from post_tag where tag_id = ?) order by post_id desc limit ?, ?");
				pstmtgetPost.setInt(1, tagId);
				pstmtgetPost.setInt(2, startRow);
				pstmtgetPost.setInt(3, size);
				rsgetPost = pstmtgetPost.executeQuery();
				while (rsgetPost.next()) {
					// resultSet에서 데이터를 읽어와 Post 객체를 생성한다.
					result.add(convertArticle(rsgetPost));
					 System.out.println("convertArticle(rs_get_post) :"+convertArticle(rsgetPost).getPostId());
				}
				return result;
			}
			return result;
		} catch (Exception e) {
			return null;

		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	private Post convertArticle(ResultSet rs) throws SQLException {

		return new Post(rs.getInt("post_id"), new Board(rs.getInt("board_id"), rs.getString("board_name")),
				new Category(rs.getInt("cate_id"), rs.getInt("board_id"), rs.getString("cate_name")),
				new Writer(rs.getInt("writer_id"), rs.getString("writer_nick")), rs.getString("post_image"),
				rs.getString("post_title"), rs.getString("post_content"), rs.getBoolean("post_visibility"),
				rs.getDate("post_write_date"), rs.getDate("post_edited_date"), rs.getInt("post_view_num"));
	}

	// 게시글 내용을 조회하려면, 다음 두 데이터가 필요하다.
	// 1. 특정 번호에 해당하는 게시글 데이터 읽기
	// 2. 특정 번호에 해당하는 게시글 데이터의 조회수 증가하기

	// 1. 특정 번호에 해당하는 게시글 데이터 읽기
	public Post selectById(Connection conn, int no) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select * from post where post_id = ?");
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			Post post = null;
			if (rs.next()) {
				post = convertArticle(rs);
			}
			return post;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	// 2. 특정 번호에 해당하는 게시글 데이터의 조회수 증가하기
	public void increaseReadCount(Connection conn, int no) throws SQLException {
		try (PreparedStatement pstmt = conn
				.prepareStatement("update post set post_view_num = post_view_num + 1 " + "where post_id = ?")) {
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
		}
	}

	// 게시글 수정 기능
	public int update(Connection conn, Integer no, Integer boardId, String boardName, Integer cateId,
			String cateName, String postImage, String postTitle, String postContent, boolean postVisibility)
			throws SQLException {
		try (PreparedStatement pstmt = conn.prepareStatement("update post set board_id = ?, board_name = ?,"
				+ "cate_id = ?, cate_name = ?, post_image = ?, post_title = ?,"
				+ "post_content = ?, post_visibility = ?," + "post_edited_date = now() " + "where post_id = ?")) {
			pstmt.setInt(1, boardId);
			pstmt.setString(2, boardName);
			pstmt.setInt(3, cateId);
			pstmt.setString(4, cateName);
			pstmt.setString(5, postImage);
			pstmt.setString(6, postTitle);
			pstmt.setString(7, postContent);
			pstmt.setBoolean(8, postVisibility);
			pstmt.setInt(9, no);
			return pstmt.executeUpdate();
		}
	}

	// 게시글 삭제 기능
	public int delete(Connection conn, int postId) throws SQLException {
		try (PreparedStatement pstmt = conn.prepareStatement("delete from post where post_id = ?")) {
			pstmt.setInt(1, postId);
			return pstmt.executeUpdate();
		}
	}

	// 게시글 TOP5 조회 기능
	public List<Post> selectTop5(Connection conn, int size) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select * from post order by post_view_num desc limit ?");
			pstmt.setInt(1, size);
			rs = pstmt.executeQuery();
			List<Post> result = new ArrayList<>();
			while (rs.next()) {
				// resultSet에서 데이터를 읽어와 Post 객체를 생성한다.
				result.add(convertArticle(rs));
			}
			return result;

		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
}
