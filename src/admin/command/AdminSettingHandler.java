package admin.command;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import admin.model.Admin;
import admin.service.AdminSettingRequest;
import admin.service.AdminSettingService;
import mvc.command.CommandHandler;
import post.service.ArticleNotFoundException;
import post.service.PermissionDeniedException;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class AdminSettingHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/adminSetting.jsp";

	private AdminSettingService adminSettingService = new AdminSettingService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {
			res.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		try {
			String adminIdVal = req.getParameter("no");

			Integer adminId = Integer.parseInt(adminIdVal);
			Admin admin = adminSettingService.getAdminSetting(adminId);

			User authUser = (User) req.getSession().getAttribute("authUser");
			if (!canModify(authUser, admin)) {
				res.sendError(HttpServletResponse.SC_FORBIDDEN);
				return null;
			}

			// 참고
			// public AdminSettingRequest(String adminEmail, String adminGithub, String
			// adminNaver, String adminGoogle,
			// String addminProfileUrl) {
			
			// 서버에 저장된 이미지 보이기

			
			AdminSettingRequest adminSettingReq = new AdminSettingRequest(authUser.getId(), admin.getAdminGithub(),
					admin.getAdminNaver(), admin.getAdminGoogle(), admin.getAdminProfileUrl());
			
//			File file = new File();
			req.setAttribute("adminSetting", adminSettingReq);

			return FORM_VIEW;
		} catch (ArticleNotFoundException e) {
			try {
				res.sendError(HttpServletResponse.SC_NOT_FOUND);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	private boolean canModify(User user, Admin admin) {
		int adminId = admin.getAdminId();
		return user.getId().equals(adminId);
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {

		req.setCharacterEncoding("UTF-8");

		// 파일 업로드를 하기 위해서 cos.jar 추가 및 객체 생성
		MultipartRequest multi = null;
		/*System.out.println("request getContentType :" + req.getContentType());*/

		// 업로드 될 파일의 최대 사이즈 (10메가)
		int sizeLimit = 10 * 1024 * 1024;

		// 파일이 업로드될 실제 tomcat 폴더의 경로 (WebContent 기준)
		System.out.println("ContextPath:"+req.getContextPath());
		/*String savePath = req.getSession().getServletContext().getRealPath("/upload");*/
		
		// 로컬인 경우
		String savePath = req.getSession().getServletContext().getRealPath("/upload");
		
		// 객체 생성
		try {
			if (-1 < req.getContentType().indexOf("multipart/form-data")) {

				multi = new MultipartRequest(req, savePath, sizeLimit, "UTF-8", new DefaultFileRenamePolicy());
				// multi = new MultipartRequest(req, savePath);
				System.out.println("작동되려나1");
				System.out.println("savePath:"+savePath);
				System.out.println("ContextPath:"+req.getContextPath());
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("작동되려나2");
		}

		String adminIdVal = multi.getParameter("adminId");
		Integer adminId = Integer.parseInt(adminIdVal);

		// 파일 이름 저장
		String fileName = multi.getFilesystemName("adminProfileUrl");

		// 프로필 사진 url
/*		String adminProfileUrl = savePath + "\\" + filename;
		System.out.println("adminProfileUrl:" + adminProfileUrl);*/

		AdminSettingRequest adminSettingReq = new AdminSettingRequest(adminId, multi.getParameter("adminGithub"),
				multi.getParameter("adminNaver"), multi.getParameter("adminGoogle"), fileName);

		// '파일이름'을 통해 실제 파일 객체를 가져온다.
		// File file = multi.getFile("adminProfileUrl");

		req.setAttribute("adminSetting", adminSettingReq);

		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);

		if (!errors.isEmpty()) {
			return FORM_VIEW;
		}

		try {
			adminSettingService.modifyAdminSetting(adminSettingReq);
			return "/WEB-INF/view/adminSettingSuccess.jsp";
		} catch (ArticleNotFoundException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		} catch (PermissionDeniedException e) {
			res.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}
	}
}
