/*
 * This file is part of JetSet, a lightweight Java Enterprise Web MVC framework.
 * Modified as of 2/24/14 4:06 PM
 *
 * JetSet is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JetSet is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with JetSet.  If not, see <http://www.gnu.org/licenses/>.
 */

package system.components;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import system.JetSetRequest;

import java.io.IOException;

public class View
{
	private final JetSetRequest jsr;
	public final ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver();
	private final TemplateEngine templateEngine = new TemplateEngine();
	private final WebContext webContext;

	public View(JetSetRequest jsr)
	{
		// Setup view
		this.jsr = jsr;
		webContext = new WebContext(jsr.request, jsr.response, jsr.servletContext);
		templateResolver.setTemplateMode("XHTML");
		templateResolver.setPrefix("/WEB-INF/views/");
		templateResolver.setSuffix(".html");
		templateResolver.setCacheTTLMs(3600000L);
		templateEngine.setTemplateResolver(this.templateResolver);
	}

	public void assignData(String viewDataKey, Object viewDataValue)
	{
		webContext.setVariable(viewDataKey, viewDataValue);
	}

	public void loadView(String viewName) throws IOException
	{
		templateEngine.process(viewName, webContext, jsr.response.getWriter());
	}
}