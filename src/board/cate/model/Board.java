package board.cate.model;


public class Board {

	private Integer boardId;
	private String boardName;
	private Integer boardType;

	public Board(Integer boardId, String boardName, Integer boardType) {
		this.boardId = boardId;
		this.boardName = boardName;
		this.boardType = boardType;
	}

	public Board(Integer boardId, String boardName) {
		this.boardId = boardId;
		this.boardName = boardName;
	}

	public Integer getBoardId() {
		return boardId;
	}

	public void setBoardId(Integer boardId) {
		this.boardId = boardId;
	}

	public String getBoardName() {
		return boardName;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}

	public Integer getBoardType() {
		return boardType;
	}

	public void setBoardType(Integer boardType) {
		this.boardType = boardType;
	}

}
