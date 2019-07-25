package tag.dao;

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

public class TagDao {

	// 태그 등록하기
	public boolean insert(Connection conn, String stringTag, int postId) throws SQLException {

		/*System.out.println("stringTag : " + stringTag);*/
		// 받은 tag에서 #를 기준으로 자른다.
		String[] tag = stringTag.split("#");
		/*System.out.println("tag.length : " + tag.length);*/
		try {

			for (int i = 1; i < tag.length; i++) {
				// 각 데이터가 tag DB에 등록되어있는지 확인한다.
				/*System.out.println("tag[" + i + "] : " + tag[i]);*/
				int tagId = checkDb(conn, tag[i]);

				// 등록되어 있다면, 등록되어 있는 postId를 가져온다.
				if (tagId != 0) {
					// 이전에 등록이 되어 있는 경우, 가져온 postId로 postTag DB에도 등록한다. (postTag DB에 넣을 데이터 :
					// tagId, postId)
					insertPostTag(conn, postId, tagId);

				} else {
					// 등록되어 있지 않다면, 각 데이터를 tag DB에 등록한다. (tag DB에 넣을 데이터 : tagName, tagSearchNum)
					int tagIdAfterInsert = insertTag(conn, tag[i]);
					// tag DB에 방금 등록한 경우, postTag DB에도 등록한다. (postTag DB에 넣을 데이터 : tagId,
					// postId)
					insertPostTag(conn, postId, tagIdAfterInsert);
				}

			}
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}

	}

	// tag DB에 등록되어있는지 확인한다.
	private int checkDb(Connection conn, String tagName) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select tag_id from tag where tag_name = ?");
			pstmt.setString(1, tagName);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				// 등록되어 있지 않다면,
				if (rs.wasNull()) {
					return 0;

				} else {
					// 등록되어 있다면,
					System.out.println("rs.getInt(1):" + rs.getInt(1));
					return rs.getInt(1);
				}
			}

		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return 0;
	}

	// tag DB에 등록한다.
	private int insertTag(Connection conn, String tagName) throws SQLException {
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("insert into tag" + "(tag_name,tag_search_num)" + "values (?,0)");
			pstmt.setString(1, tagName);
			int insertedCount = pstmt.executeUpdate();

			if (insertedCount > 0) {
				stmt = conn.createStatement();

				// tag DB에 새롭게 추가된 행의 tagId 값을 구한다.
				rs = stmt.executeQuery("select last_insert_id() from tag");
				if (rs.next()) {
					Integer tagId = rs.getInt(1);
					// post 테이블에 추가한 데이터를 담은 post 객체를 리턴한다.
					// insert에 성공하면, post 객체를 리턴하고, 실패하면 null을 리턴한다.
					return tagId;
				}
			}
			return 0;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
			JdbcUtil.close(pstmt);
		}
	}

	// postTag DB에 등록한다.
	private void insertPostTag(Connection conn, int postId, int tagId) throws SQLException {
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("insert into post_tag" + "(post_id,tag_id)" + "values (?,?)");
			pstmt.setInt(1, postId);
			pstmt.setInt(2, tagId);
			pstmt.executeUpdate();
			/*
			 * if (insertedCount > 0) { return true; } else { return false; }
			 */

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
			JdbcUtil.close(pstmt);
		}
	}

	// 사용자 화면 우측 메뉴에 tag 목록 보여주기
	public List<Tag> selectAllTag(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// limit : 쿼리 실행 결과 중 일부 레코드만 조회할 때 사용
			// limit 첫 번째 행 번호, 읽어올 레코드 개수
			pstmt = conn.prepareStatement("select * from tag");
			rs = pstmt.executeQuery();
			List<Tag> result = new ArrayList<>();
			while (rs.next()) {
				// resultSet에서 데이터를 읽어와 Post 객체를 생성한다.
				result.add(convertTag(rs));
			}
			return result;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	// tag (tagId, tagName, tagSearchNum 으로 바꾸기)
	private Tag convertTag(ResultSet rs) throws SQLException {
		return new Tag(rs.getInt("tag_id"), rs.getString("tag_name"), rs.getInt("tag_search_num"));
	}

	// postId로 tag 목록 불러오기 - 게시판 수정, 게시판 상세보기 화면에서 쓰임
	public List<Tag> selectPostTag(Connection conn, int postId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// limit : 쿼리 실행 결과 중 일부 레코드만 조회할 때 사용
			// limit 첫 번째 행 번호, 읽어올 레코드 개수
			pstmt = conn.prepareStatement(
					"select * from tag where tag_id in (select tag_id from post_tag where post_id = ?)");
			pstmt.setInt(1, postId);
			rs = pstmt.executeQuery();
			List<Tag> result = new ArrayList<>();
			while (rs.next()) {
				// resultSet에서 데이터를 읽어와 Post 객체를 생성한다.
				result.add(convertTag(rs));
			}
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	// 태그 삭제하기
	public boolean deleteTag(Connection conn, int postId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		System.out.println("postId : "+postId);
		try {
			// postTag DB에서 해당 글 postId에 해당하는 각 tagId의 개수 조회하기
			pstmt = conn.prepareStatement(
					"select tag_id, count(tag_id) from post_tag group by tag_id having tag_id in (select tag_id from post_tag where post_id=?)");
			pstmt.setInt(1, postId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int tagId = rs.getInt(1);
				System.out.println("tagId : " + tagId);
				int tagIdCount = rs.getInt(2);
				System.out.println("tagIdCount : " + tagIdCount);

				if (tagIdCount == 1) {
					// postTag DB에서 해당 tagId count가 2개 미만이라면, tag DB에 해당 tagId도 지우고,
					// postTag DB에서도 해당 글 postId에 해당하는 tagId를 지운다.
					pstmt = conn.prepareStatement("delete from tag where tag_id = ?");
					pstmt.setInt(1, tagId);
					pstmt.executeUpdate();
				}
			}

			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

}
