package com.epam.goods.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.epam.goods.builder.RequestGoodBuilder;
import com.epam.goods.document.DocumentConstants;
import com.epam.goods.document.DocumentWriter;
import com.epam.goods.exception.BuildException;
import com.epam.goods.model.Good;

public class CreateCommand extends Command {

	private String filename;
	private final String SUBMIT_PARAMTER = "submit";
	private final RequestGoodBuilder requestGoodBuilder = new RequestGoodBuilder();
	private final Logger LOGGER = LoggerFactory.getLogger(CreateCommand.class);
	
	public CreateCommand(String filename) {
		super();
		this.filename = filename;
	}

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		if (request.getParameter(SUBMIT_PARAMTER) != null) {
			try {

				// Create a good from request data
				Good good = requestGoodBuilder.build(request);

				// Get category and subcategory names
				String categoryName = request.getParameter(DocumentConstants.TAG_CATEGORY);
				String subCategoryName = request.getParameter(DocumentConstants.TAG_SUBCATEGORY);
				
				// Create document builder
				DocumentWriter builder = new DocumentWriter(filename);
				
				// Add new good to document
				builder.write(categoryName, subCategoryName, good);
				
				redirect(request.getContextPath() + "/controller?command=data", response);
				
			} catch (BuildException e) {
				//If there was a build exception show it on the page
				request.setAttribute("error", e.getMessage());
				LOGGER.error("Build error: {}", e);
			} 

		}

		return "create.jsp";
	}
}
