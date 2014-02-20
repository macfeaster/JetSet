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