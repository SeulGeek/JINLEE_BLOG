package board.cate.dao;

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

public class CategoryDao {

	public List<Category> select(Connection conn, int boardId) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// limit : 쿼리 실행 결과 중 일부 레코드만 조회할 때 사용
			// limit 첫 번째 행 번호, 읽어올 레코드 개수
			pstmt = conn.prepareStatement("select * from category where board_id=? order by cate_id asc");
			pstmt.setInt(1, boardId);
			rs = pstmt.executeQuery();
			List<Category> result = new ArrayList<>();
			while (rs.next()) {
				// resultSet에서 데이터를 읽어와 Category객체를 생성한다.
				result.add(convertArticle(rs));
			}
			return result;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	private Category convertArticle(ResultSet rs) throws SQLException {
		return new Category(rs.getInt("cate_id"), rs.getInt("board_id"), rs.getString("cate_name"));
	}

}