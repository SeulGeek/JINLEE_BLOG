package auth.service;

import java.sql.Connection;
import java.sql.SQLException;

import admin.dao.AdminDao;
import admin.model.Admin;
import jdbc.connection.ConnectionProvider;

public class LoginService {

	private AdminDao adminDao = new AdminDao();

	// 로그인에 성공하면, 유저id와 이름 정보가 있는 user객체를 리턴한다.
	// 이 객체는 세션에 저장한다.
	public User login(String email, String password) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			Admin admin = adminDao.loginByEmail(conn, email);
			if (admin == null) {
				throw new LoginFailException();
			}
			if (!admin.matchPassword(password)) {
				throw new LoginFailException();
			}
			return new User(admin.getAdminId(), admin.getAdminEmail(), admin.getAdminNick());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
