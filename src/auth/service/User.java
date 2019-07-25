package auth.service;

// 인증에 성공한 사용자 정보 담는다. 이 객체를 세션에 저장한다.
public class User {

	private Integer id;
	private String email;
	private String name;

	public User(Integer id, String email, String name) {
		this.id = id;
		this.email = email;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getName() {
		return name;
	}

}
