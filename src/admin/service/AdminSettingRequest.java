package admin.service;

public class AdminSettingRequest {

	private Integer adminId;
	private String adminGithub;
	private String adminNaver;
	private String adminGoogle;
	private String adminProfileUrl;

	public AdminSettingRequest(Integer adminId, String adminGithub, String adminNaver, String adminGoogle,
			String adminProfileUrl) {
		this.adminId = adminId;
		this.adminGithub = adminGithub;
		this.adminNaver = adminNaver;
		this.adminGoogle = adminGoogle;
		this.adminProfileUrl = adminProfileUrl;
	}

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminEmail(Integer adminId) {
		this.adminId = adminId;
	}

	public String getAdminGithub() {
		return adminGithub;
	}

	public void setAdminGithub(String adminGithub) {
		this.adminGithub = adminGithub;
	}

	public String getAdminNaver() {
		return adminNaver;
	}

	public void setAdminNaver(String adminNaver) {
		this.adminNaver = adminNaver;
	}

	public String getAdminGoogle() {
		return adminGoogle;
	}

	public void setAdminGoogle(String adminGoogle) {
		this.adminGoogle = adminGoogle;
	}

	public String getAdminProfileUrl() {
		return adminProfileUrl;
	}

	public void setAdminProfileUrl(String adminProfileUrl) {
		this.adminProfileUrl = adminProfileUrl;
	}

}
