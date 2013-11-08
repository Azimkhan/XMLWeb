package com.epam.goods.servlet;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.goods.command.Command;
import com.epam.goods.command.DataCommand;

public class Controller extends HttpServlet{

	private static final long serialVersionUID = -7069247876390736874L;
	private final HashMap<String, Command> commands = new HashMap<>();
	private final String PARAM = "command";
	
	@Override
	public void init() throws ServletException {
		String dataFilename = getServletConfig().getInitParameter("data-filename");
		if (dataFilename != null){
			DataCommand dataCommand = new DataCommand();
			
				dataCommand.setFilename(getServletContext().getRealPath(File.separator) + dataFilename);
				commands.put("data", dataCommand);
			
		} else{
			throw new ServletException("No config file set");
		}
		
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		execute(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		execute(req, resp);
	}
	
	private void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try{
			Command command = commands.get(request.getParameter(PARAM));
			if (command != null){
				request.getRequestDispatcher(command.execute(request, response)).forward(request, response);
			} else{
				response.getOutputStream().println("command not found");
			}
		} catch (Exception e){
			throw new ServletException(e);
			
		}
	}

	
}
