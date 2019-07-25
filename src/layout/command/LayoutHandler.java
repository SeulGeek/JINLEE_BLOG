package layout.command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mvc.command.CommandHandler;

public class LayoutHandler implements CommandHandler {

	private static final String FORM_VIEW = "/WEB-INF/view/userLayout.jsp";
	/*private static final String FORM_VIEW = "home.tiles";*/

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) {
		return FORM_VIEW;
	}
}