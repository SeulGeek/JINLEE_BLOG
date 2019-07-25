package post.service;

import java.util.List;

import post.model.Post;

public class PostPage {

	private int total; // 전체 게시글 개수
	private int currentPage; // 사용자가 요청한 페이지 번호
	private List<Post> content; // 화면에 출력할 게시글 목록
	private int totalPages; // 전체 페이지 개수
	private int startPage; // 페이지 하단 페이징 시작 번호
	private int endPage; // 페이징 끝 번호

	public PostPage(int total, int currentPage, int size, List<Post> content) {
		this.total = total;
		this.currentPage = currentPage;
		this.content = content;
		if (total == 0) {
			totalPages = 0;
			startPage = 0;
			endPage = 0;
		} else {
			totalPages = total / size;
			if (total % size > 0) {
				totalPages++;
			}
			
			// 페이징 번호를 5개씩 보여줌
			// 현재 페이지 번호가 3이면, 시작 페이지 번호는 1
			// 현재 페이지 번호가 8이면, 시작 페이지 번호는 6
			int modVal = currentPage % 5;
			startPage = currentPage / 5 * 5 + 1;
			if (modVal == 0) startPage -= 5;
			
			// 페이징 번호 5개 보여주기
			endPage = startPage + 4;
			if (endPage > totalPages) endPage = totalPages;
		}
	}

	public int getTotal() {
		return total;
	}

	public boolean hasNoArticles() {
		return total == 0;
	}

	public boolean hasArticles() {
		return total > 0;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public List<Post> getContent() {
		return content;
	}

	public int getStartPage() {
		return startPage;
	}
	
	public int getEndPage() {
		return endPage;
	}
}
