package com.douzone.mysite.action.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mvc.action.Action;
import com.douzone.mvc.util.WebUtils;
import com.douzone.mysite.repository.GuestbookDao;
import com.douzone.mysite.vo.GuestbookVo;

public class InsertAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("name");
		String password = request.getParameter("pass");
		String message = request.getParameter("content");
		
		GuestbookVo vo = new GuestbookVo();
		
		vo.setName(name);
		vo.setPassword(password);
		vo.setMessage(message);
		
		boolean result = 1 == new GuestbookDao().insert(vo);
		System.out.println("1111");
		if(result) {
			System.out.println("방명록 추가 성공");
			WebUtils.redirect(request, response, request.getContextPath() + "/guestbook");
		} else {
			System.out.println("방명록 추가 실패");
		}
		
		
		
	}

}
