package com.douzone.mysite.action.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mvc.action.Action;
import com.douzone.mysite.repository.UserDao;
import com.douzone.mysite.vo.UserVo;

import net.sf.json.JSONObject;

public class AjaxCheckEmailAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		
		UserDao dao = new UserDao();
		UserVo vo = dao.get(email); // = null; ( tdd1 )
		
		boolean bExist = vo != null;	// vo가 not null -> bExist=true
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("exist", bExist);
		
		// 이처럼 통신용어로써 vo 보다는 dto라고 사용해서 보낸다.
		JSONObject jsonObject = JSONObject.fromObject(map);
		String jsonString = jsonObject.toString(); // map -> json로 바뀐다.
		
		response.setContentType("application/json; charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.println(jsonString);

	}

}
