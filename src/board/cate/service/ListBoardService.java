package board.cate.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import board.cate.dao.BoardDao;
import board.cate.model.Board;
import board.cate.model.Category;
import jdbc.connection.ConnectionProvider;
import post.dao.PostDao;
import post.model.Post;

public class ListBoardService {

	private BoardDao boardDao = new BoardDao();

	public List<Board> getBoard() {
		try (Connection conn = ConnectionProvider.getConnection()) {

			List<Board> content = boardDao.select(conn);
			
			return content;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
