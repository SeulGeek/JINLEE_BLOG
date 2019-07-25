package admin.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import admin.dao.AdminDao;
import admin.model.Admin;

public class AdminSettingService {

	private AdminDao adminDao = new AdminDao();

	public Admin getAdminSetting(Integer adminId) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			Admin admin = adminDao.selectById(conn, adminId);
			if (admin == null) {
				throw new AdminNotFoundException();
			}
			return admin;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void modifyAdminSetting(AdminSettingRequest adminSettingReq) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			Admin admin = adminDao.selectById(conn, adminSettingReq.getAdminId());

			if (admin == null) {
				throw new MemberNotFoundException();
			}

			if (!canModify(adminSettingReq.getAdminId(), admin)) {
				throw new PermissionDeniedException();
			}

			admin.modifyAdminSetting(adminSettingReq.getAdminGithub(), adminSettingReq.getAdminNaver(),
					adminSettingReq.getAdminGoogle(), adminSettingReq.getAdminProfileUrl());
			adminDao.update(conn, admin);
			conn.commit();

		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} catch (PermissionDeniedException e) {
			JdbcUtil.rollback(conn);
			throw e;
		} finally {
			JdbcUtil.close(conn);
		}
	}

	private boolean canModify(Integer modfyingUserId, Admin admin) {
		return admin.getAdminId().equals(modfyingUserId);
	}
}
