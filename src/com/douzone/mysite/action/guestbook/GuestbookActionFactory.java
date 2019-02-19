package com.douzone.mysite.action.guestbook;

import com.douzone.mvc.action.AbstractActionFactory;
import com.douzone.mvc.action.Action;
import com.douzone.mysite.action.main.IndexAction;
import com.douzone.mysite.action.user.JoinAction;
import com.douzone.mysite.action.user.JoinFormAction;
import com.douzone.mysite.action.user.JoinSuccessAction;
import com.douzone.mysite.action.user.LoginAction;
import com.douzone.mysite.action.user.LoginFormAction;

public class GuestbookActionFactory extends AbstractActionFactory {
	@Override
	public Action getAction(String actionName) {
		Action action = null;

		if ("insert".equals(actionName)) {
			System.out.println("insert");
			action = new InsertAction();
		} else if ("delete".equals(actionName)) {
			action = new DeleteAction();
		} else if ("deleteform".equals(actionName)) {
			action = new DeleteFormAction();
		} else if ("ajax".equals(actionName)) {
			action = new AjaxAction();
		} else if ("ajax-list".equals(actionName)) {
			action = new AjaxListAction();
		} else if ("ajax-insert".equals(actionName)) {
			action = new AjaxInsertAction();
		} else if ("ajax-delete".equals(actionName)) {
			action = new AjaxDeleteAction();
		} else {
			action = new GuestbookListAction();
		}

		return action;
	}
}
