package post.service;

import post.model.Post;

public class PostData {

	// 한 객체에 다 넣기 위함
	private Post post;

	public PostData(Post post) {
		this.post = post;
	}

	public Post getPost() {
		return post;
	}
}
