package system;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JetSetRequest
{
	public final HttpServletRequest request;
	public final HttpServletResponse response;
	public final ServletContext servletContext;

	/**
	 * Wrapper for a generic JetSet request
	 * @param request HttpServletRequest from client
	 * @param response HttpServletResponse to client
	 * @param servletContext Servlet context data
	 */
	public JetSetRequest(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext)
	{
		this.request = request;
		this.response = response;
		this.servletContext = servletContext;
	}
}
