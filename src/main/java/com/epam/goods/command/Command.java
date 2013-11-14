package com.epam.goods.command;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Command {
	private boolean redirected;
	
	public abstract String execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	protected final void redirect(String path, HttpServletResponse res) throws IOException{
		res.sendRedirect(path);
		redirected = true;
	}
	public boolean isRedirected() {
		return redirected;
	}
	
	public void reset(){
		redirected = false;
	}
}
