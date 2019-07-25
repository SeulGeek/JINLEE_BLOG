package board.cate.model;

import java.util.List;

import post.model.Post;

public class BoardCategory {

	private int boardId;// 게시판 id
	private String boardName;// 게시판 명
	private List<Category> cateList = null; // 카테고리 리스트
	
	public BoardCategory(int boardId, String boardName, List<Category> cateList) {
		this.boardId = boardId;
		this.boardName = boardName;
		this.cateList = cateList;
	}

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	public String getBoardName() {
		return boardName;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}

	public List<Category> getCateList() {
		return cateList;
	}

	public void setCateList(List<Category> cateList) {
		this.cateList = cateList;
	}

}
