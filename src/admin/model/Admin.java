package admin.model;


public class Admin {

	private Integer adminId;
	private String adminEmail;
	private String adminPass;
	private String adminNick;
	private String adminProfileUrl;
	private String adminGithub;
	private String adminGoogle;
	private String adminNaver;

	public Admin(Integer adminId, String adminEmail, String adminPass, String adminNick, String adminProfileUrl,
			String adminGithub, String adminGoogle, String adminNaver) {
		this.adminId = adminId;
		this.adminEmail = adminEmail;
		this.adminPass = adminPass;
		this.adminNick = adminNick;
		this.adminProfileUrl = adminProfileUrl;
		this.adminGithub = adminGithub;
		this.adminGoogle = adminGoogle;
		this.adminNaver = adminNaver;
	}

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public String getAdminEmail() {
		return adminEmail;
	}

	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}

	public String getAdminPass() {
		return adminPass;
	}

	public void setAdminPass(String adminPass) {
		this.adminPass = adminPass;
	}

	public String getAdminNick() {
		return adminNick;
	}

	public void setAdminNick(String adminNick) {
		this.adminNick = adminNick;
	}

	public String getAdminProfileUrl() {
		return adminProfileUrl;
	}

	public void setAdminProfileUrl(String adminProfileUrl) {
		this.adminProfileUrl = adminProfileUrl;
	}

	public String getAdminGithub() {
		return adminGithub;
	}

	public void setAdminGithub(String adminGithub) {
		this.adminGithub = adminGithub;
	}

	public String getAdminGoogle() {
		return adminGoogle;
	}

	public void setAdminGoogle(String adminGoogle) {
		this.adminGoogle = adminGoogle;
	}

	public String getAdminNaver() {
		return adminNaver;
	}

	public void setAdminNaver(String adminNaver) {
		this.adminNaver = adminNaver;
	}

	public boolean matchPassword(String pwd) {
		return adminPass.equals(pwd);
	}

	public void modifyAdminSetting(String adminGithub, String adminNaver, String adminGoogle,
			String adminProfileUrl) {
		this.adminGithub = adminGithub;
		this.adminNaver = adminNaver;
		this.adminGoogle = adminGoogle;
		this.adminProfileUrl = adminProfileUrl;
	}
}
