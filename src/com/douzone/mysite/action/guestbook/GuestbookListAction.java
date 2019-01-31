package com.douzone.mysite.action.guestbook;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mvc.action.Action;
import com.douzone.mvc.util.WebUtils;
import com.douzone.mysite.repository.GuestbookDao;
import com.douzone.mysite.vo.GuestbookVo;

public class GuestbookListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<GuestbookVo> list = new GuestbookDao().getList();
		request.setAttribute("list", list);
		WebUtils.forward(request, response, "/WEB-INF/views/guestbook/list.jsp");
	}

}
