package board.cate.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import board.cate.dao.BoardDao;
import board.cate.dao.CategoryDao;
import board.cate.model.Board;
import board.cate.model.Category;
import jdbc.connection.ConnectionProvider;
import post.dao.PostDao;
import post.model.Post;

public class ListCategoryService {

	private CategoryDao cateDao = new CategoryDao();

	public List<Category> getCate(int boardId) {
		try (Connection conn = ConnectionProvider.getConnection()) {

			List<Category> content = cateDao.select(conn,boardId);
			
			return content;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
