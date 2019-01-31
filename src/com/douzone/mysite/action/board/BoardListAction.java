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
import com.douzone.mysite.vo.PageVo;

public class BoardListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//		int page = Integer.parseInt(request.getParameter("page"));

		PageVo pageVo = null;

		if (request.getParameter("page") == null || request.getParameter("page") == "" || Integer.parseInt(request.getParameter("page")) < 0) {
			System.out.println("page : " + request.getParameter("page"));
			int totalCount = new BoardDao().totalPage();
			pageVo = new PageVo();

			int pageSize = 0;
			if (totalCount % 10 > 0)
				pageSize = totalCount / 10 + 1;
			else {
				pageSize = totalCount / 10;
			}
			pageVo.setTotalCount(totalCount);
			pageVo.setPageSize(pageSize);
			
			pageVo.setPageNo(1);
			pageVo.setNextPageNo(1);
			pageVo.setPrevPageNo(1);

			request.setAttribute("pageVo", pageVo);
		} else {
			System.out.println("page : " + request.getParameter("page"));
			int pageNo = Integer.parseInt(request.getParameter("page"));
			int totalCount = new BoardDao().totalPage();
			pageVo = new PageVo();

			int pageSize = 0;
			if (totalCount % 10 > 0)
				pageSize = totalCount / 10 + 1;
			else {
				pageSize = totalCount / 10;
			}
			pageVo.setTotalCount(totalCount);
			pageVo.setPageSize(pageSize);
			pageVo.setPageNo(pageNo);
			pageVo.setPrevPageNo(pageNo);
			pageVo.setNextPageNo(pageNo);
			
			request.setAttribute("pageVo", pageVo);
		}
		System.out.println("pageNo : " + pageVo.getPageNo());
		System.out.println("pageSize : " + pageVo.getPageSize());
		System.out.println("nextPageNo : " + pageVo.getNextPageNo());
		/*
		 * PageVo pageVo = new PageVo(); pageVo.setPageNo(page); pageSetting(pageVo);
		 * if(page == 1) { pageVo.setPageNo(1);
		 * 
		 * }
		 */

		String kwd = request.getParameter("kwd");
		
		if (kwd != null && kwd != "") {
			List<BoardVo> list = new BoardDao().search(kwd, pageVo.getPageNo());
			request.setAttribute("list", list);
			WebUtils.forward(request, response, "/WEB-INF/views/board/list.jsp");
			return;
		}
		
		List<BoardVo> list = new BoardDao().getList(pageVo.getPageNo());
		System.out.println("pageNo : " + pageVo.getPageNo());
		request.setAttribute("list", list);

		WebUtils.forward(request, response, "/WEB-INF/views/board/list.jsp");
	}

}
