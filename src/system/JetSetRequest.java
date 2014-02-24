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
