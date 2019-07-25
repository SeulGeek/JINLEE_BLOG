package post.model;

import java.util.Date;

import board.cate.model.Board;
import board.cate.model.Category;

public class Post {

	private Integer postId;
	private Board board;
	private Category cate;
	private Writer writer;
	private String postImage;
	private String postTitle;
	private String postContent;
	private boolean postVisibility;
	private Date postWriteDate; // 사용자가 일력한 작성 일시
	private Date postEditedDate; // 글 등록, 수정한 작성 일시
	private int postViewNum;

	public Post(Integer postId, Board board, Category cate, Writer writer, String postImage, String postTitle,
			String postContent, boolean postVisibility, Date postWriteDate, Date postEditedDate,
			int postViewNum) {
		this.postId = postId;
		this.board = board;
		this.cate = cate;
		this.writer = writer;
		this.postImage = postImage;
		this.postTitle = postTitle;
		this.postContent = postContent;
		this.postVisibility = postVisibility;
		this.postWriteDate = postWriteDate;
		this.postEditedDate = postEditedDate;
		this.postViewNum = postViewNum;
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

	public Writer getWriter() {
		return writer;
	}

	public void setWriter(Writer writer) {
		this.writer = writer;
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

	public boolean getPostVisibility() {
		return postVisibility;
	}

	public void setPostVisibility(boolean postVisibility) {
		this.postVisibility = postVisibility;
	}

	public Date getPostWriteDate() {
		return postWriteDate;
	}

	public void setPostWriteDate(Date postWriteDate) {
		this.postWriteDate = postWriteDate;
	}

	public Date getPostEditedDate() {
		return postEditedDate;
	}

	public void setPostEditedDate(Date postEditedDate) {
		this.postEditedDate = postEditedDate;
	}

	public int getPostViewNum() {
		return postViewNum;
	}

	public void setPostViewNum(int postViewNum) {
		this.postViewNum = postViewNum;
	}

}