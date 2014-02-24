/*
 * This file is part of JetSet, a lightweight Java Enterprise Web MVC framework.
 * Modified as of 2/24/14 4:05 PM
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
	transient JetSetRouter jetSetRouter = new JetSetRouter();

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