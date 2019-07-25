package post.service;

import java.util.Map;

import board.cate.model.Board;
import board.cate.model.Category;

// 필수 입력 사항 : 카테고리, 제목, 공개 여부
public class ModifyPostRequest {

	private Integer userId;
	private Integer postId;
	private Board board;
	private Category cate;
	private String postImage;
	private String postTitle;
	private String postContent;
	private boolean postVisibility;
	private String postTag;

	public ModifyPostRequest(Integer userId, Integer postId, Board board, Category cate, String postImage,
			String postTitle, String postContent, boolean postVisibility, String postTag) {
		this.userId = userId;
		this.postId = postId;
		this.board = board;
		this.cate = cate;
		this.postImage = postImage;
		this.postTitle = postTitle;
		this.postContent = postContent;
		this.postVisibility = postVisibility;
		this.postTag = postTag;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getPostId() {
		return postId;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public Category getCate() {
		return cate;
	}

	public void setCate(Category cate) {
		this.cate = cate;
	}

	public String getPostImage() {
		return postImage;
	}

	public void setPostImage(String postImage) {
		this.postImage = postImage;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public String getPostContent() {
		return postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	public boolean isPostVisibility() {
		return postVisibility;
	}

	public void setPostVisibility(boolean postVisibility) {
		this.postVisibility = postVisibility;
	}

	
	
	public String getPostTag() {
		return postTag;
	}

	public void setPostTag(String postTag) {
		this.postTag = postTag;
	}

	// 필수 입력 사항 : 카테고리, 제목, 공개 여부
	public void validate(Map<String, Boolean> errors) {
		if (cate.getCateName() == null || cate.getCateName().trim().isEmpty()) {
			errors.put("cate", Boolean.TRUE);

		} else if (postTitle == null || postTitle.trim().isEmpty()) {
			errors.put("postTitle", Boolean.TRUE);

		} else if (String.valueOf(postVisibility) == null || String.valueOf(postVisibility).isEmpty()) {
			errors.put("postVisibility", Boolean.TRUE);
		}
	}
}
