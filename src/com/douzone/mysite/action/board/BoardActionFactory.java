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
			System.out.println("write");
			action = new WriteAction();
		} else if ("writeform".equals(actionName)) {
			System.out.println("writeform");
			action = new WriteFormAction();
		} else if ("delete".equals(actionName)) {
			System.out.println("delete");
			action = new DeleteAction();
		} else if ("deleteform".equals(actionName)) {
			System.out.println("deleteform");
			action = new DeleteFormAction();
		} else if ("view".equals(actionName)) {
			System.out.println("view");
			action = new ViewAction();
		} else if ("modifyview".equals(actionName)) {
			System.out.println("modifyview");
			action = new ModifyViewAction();
		} else if ("modify".equals(actionName)) {
			System.out.println("modify");
			action = new ModifyAction();
		}else if ("replyform".equals(actionName)) {
			System.out.println("replyform");
			action = new ReplyFormAction();
		} else if ("insertreply".equals(actionName)) {
			System.out.println("insertreply");
			action = new InsertReplyAction();
		} else {
			System.out.println("boardlist");
			action = new BoardListAction();
		}
		
		return action;
	}

}
