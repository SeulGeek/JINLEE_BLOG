package board.cate.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import board.cate.model.Board;
import board.cate.model.BoardCategory;
import jdbc.connection.ConnectionProvider;

public class ListBoardCateService {

	ListCategoryService listCate = new ListCategoryService();
	private List<BoardCategory> boardCategoryList = null;

	public List<BoardCategory> getBoardCategory(List<Board> boardList) {
		try (Connection conn = ConnectionProvider.getConnection()) {

			BoardCategory boardCate;
			int boardId;

			boardCategoryList = new ArrayList<>();

			for (int i = 0; i < boardList.size(); i++) {
				boardId = boardList.get((boardList.size() - 1) - i).getBoardId();
				/*System.out.println("boardId : " + boardId);
				System.out.println("boardName : " + boardList.get(i).getBoardName());*/

				boardCate = new BoardCategory(boardId, boardList.get((boardList.size() - 1) - i).getBoardName(), listCate.getCate(boardId));
				boardCategoryList.add(boardCate);

				/*for (int j = 0; j < boardCategoryList.size(); j++) {
					System.out.println("boardCategoryList[" + j + "]_boardName : " + boardCategoryList.get(j).getBoardName());
					for (int p = 0; p < boardCategoryList.get(j).getCateList().size(); p++) {
						System.out.println("categoryList[" + p + "]_cateName : " + boardCategoryList.get(j).getCateList().get(p).getCateName());
					}
				}*/
			}

			return boardCategoryList;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
