package board.cate.model;

public class Category {

	private Integer cateId;
	private Integer boardId;
	private String cateName;

	public Category(Integer cateId, Integer boardId, String cateName) {
		this.cateId = cateId;
		this.boardId = boardId;
		this.cateName = cateName;
	}

	public Integer getCateId() {
		return cateId;
	}

	public void setCateId(Integer cateId) {
		this.cateId = cateId;
	}

	public Integer getBoardId() {
		return boardId;
	}

	public void setBoardId(Integer boardId) {
		this.boardId = boardId;
	}

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}
	
}
