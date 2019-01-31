package com.douzone.mysite.action.board;

import com.douzone.mvc.action.AbstractActionFactory;
import com.douzone.mvc.action.Action;
import com.douzone.mysite.action.guestbook.DeleteFormAction;
import com.douzone.mysite.action.guestbook.InsertAction;

public class BoardActionFactory extends AbstractActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;

		if ("write".equals(actionName)) {
			action = new WriteAction();
		} else if ("writeform".equals(actionName)) {
			action = new WriteFormAction();
		} else if ("delete".equals(actionName)) {
			action = new DeleteAction();
		} else if ("deleteform".equals(actionName)) {
			action = new DeleteFormAction();
		} else if ("view".equals(actionName)) {
			action = new ViewAction();
		} else if ("modifyview".equals(actionName)) {
			action = new ModifyViewAction();
		} else if ("modify".equals(actionName)) {
			action = new ModifyAction();
		}else if ("replyform".equals(actionName)) {
			action = new ReplyFormAction();
		} else if ("insertreply".equals(actionName)) {
			action = new InsertReplyAction();
		} else {
			action = new BoardListAction();
		}
		
		System.out.println(this.getClass().getSimpleName() + ":" + actionName);
		
		return action;
	}

}
