package admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import admin.model.Admin;
import jdbc.JdbcUtil;

public class AdminDao {

	public Admin loginByEmail(Connection conn, String adminEmail) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select * from admin where admin_email = ?");
			pstmt.setString(1, adminEmail);
			rs = pstmt.executeQuery();
			Admin admin = null;
			if (rs.next()) {
				admin = new Admin(rs.getInt("admin_id"), rs.getString("admin_email"), rs.getString("admin_pass"),
						rs.getString("admin_nick"), rs.getString("admin_profile_url"), rs.getString("admin_github"),
						rs.getString("admin_google"), rs.getString("admin_naver"));
			}
			return admin;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public Admin selectById(Connection conn, Integer adminId) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select * from admin where admin_id = ?");
			pstmt.setInt(1, adminId);
			rs = pstmt.executeQuery();
			Admin admin = null;
			if (rs.next()) {
				admin = new Admin(rs.getInt("admin_id"), rs.getString("admin_email"), rs.getString("admin_pass"),
						rs.getString("admin_nick"), rs.getString("admin_profile_url"), rs.getString("admin_github"),
						rs.getString("admin_google"), rs.getString("admin_naver"));
			}
			return admin;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	/*
	 * public void insert(Connection conn, Admin admin) throws SQLException { try
	 * (PreparedStatement pstmt =
	 * conn.prepareStatement("insert into member values(?,?,?)")) {
	 * pstmt.setString(1, admin.getAdminEmail()); pstmt.setString(2,
	 * admin.getAdminPass()); pstmt.setString(3, admin.getAdminNick());
	 * pstmt.executeUpdate(); } }
	 */

	// 개인정보수정
	public void update(Connection conn, Admin admin) throws SQLException {
		try (PreparedStatement pstmt = conn.prepareStatement(
				"update admin set admin_github = ? , admin_naver = ?, admin_google = ?, admin_profile_url = ? where admin_email = ?")) {
			pstmt.setString(1, admin.getAdminGithub());
			pstmt.setString(2, admin.getAdminNaver());
			pstmt.setString(3, admin.getAdminGoogle());
			pstmt.setString(4, admin.getAdminProfileUrl());
			pstmt.setString(5, admin.getAdminEmail());
			pstmt.executeUpdate();
		}
	}
}
