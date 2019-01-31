package com.douzone.mvc.util;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebUtils {
	
	// 현재 요청이 db 처리가 필요한 부분이라서 현재 콘텍스에서 다시 접근 할 수 없으니 다시 url로 접근
	public static void redirect(HttpServletRequest request, HttpServletResponse response, String url)
			throws ServletException, IOException {
		response.sendRedirect(url);
	}
	
	// 현재 콘텍스에서 내부 view로 보내주는 메소드 ( db 처리가 다 끝나고 화면단을 보여줄 때 )
	public static void forward(HttpServletRequest request, HttpServletResponse response, String location)
			throws ServletException, IOException {
		// forwarding
		// 내부접근이라 WEB-INF에 접근가능하다!!!
		RequestDispatcher rd = request.getRequestDispatcher(location);
		rd.forward(request, response);
	}
}
