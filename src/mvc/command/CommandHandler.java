package mvc.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 명령어 핸들러 인터페이스
// 모든 명령어 핸들러 클래스가 공통으로 구현해야 하는 메서드를 선언한다.
public interface CommandHandler {
	public String process(HttpServletRequest req, HttpServletResponse res)
	throws Exception;

}
