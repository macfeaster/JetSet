package system;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet to forward incoming requests to the JetSet router
 */
public class JetServlet extends HttpServlet
{
	private final JetSetRouter jetSetRouter = new JetSetRouter();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// Handle POST request
		jetSetRouter.process(
				new JetSetRequest(request, response, getServletContext())
		);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// Handle GET request
		jetSetRouter.process(
				new JetSetRequest(request, response, getServletContext())
		);
	}
}