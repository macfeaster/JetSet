package system;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JetServlet extends HttpServlet
{
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		JetSetRouter jetSetRouter = new JetSetRouter();
		jetSetRouter.process(new JetSetRequest(request, response, getServletContext()));
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		JetSetRouter jetSetRouter = new JetSetRouter();
		jetSetRouter.process(new JetSetRequest(request, response, getServletContext()));
	}
}
