package com.douzone.mysite.action.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mvc.action.Action;
import com.douzone.mvc.util.WebUtils;
import com.douzone.mysite.repository.BoardDao;
import com.douzone.mysite.vo.BoardVo;

public class ViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		long no = Long.parseLong(request.getParameter("no"));
		
		BoardVo vo = new BoardDao().getView(no);
		new BoardDao().upHit(no);
		//List<BoardVo> list = new BoardDao().getReplyList(no);
		request.setAttribute("vo", vo);
		//request.setAttribute("list", list);
		
		WebUtils.forward(request, response, "/WEB-INF/views/board/view.jsp");
		
	}

}
