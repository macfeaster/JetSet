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

import system.components.View;

import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class JetSetError
{
	/**
	 * Error handler that displays a detailed page with request and exception data
	 * @param jsr JetSetRequest from servlet
	 * @param e The exception thrown
	 */
	public void handleError(final JetSetRequest jsr, Exception e)
	{
		View jetSetErrorView = new View(jsr);
		StringBuilder stackTrace = new StringBuilder();
		StringBuilder stackTraceCause = new StringBuilder();

		jetSetErrorView.templateResolver.setPrefix("/WEB-INF/");
		jsr.response.setStatus(200);

		if(e != null)
		{
			for(StackTraceElement stack : e.getStackTrace())
			{
				String stackItem = stack.toString();

				if(stackItem.contains("system.") || stackItem.contains("application."))
				{
					stackTrace.append("<span class=\"stack_jetset\">at ");
					stackTrace.append(stackItem);
					stackTrace.append("</span><br />");
				}
				else if(!stackItem.contains("org.") && !stackItem.contains("java."))
				{
					stackTrace.append("<span class=\"stack\">at ");
					stackTrace.append(stackItem);
					stackTrace.append("</span><br />");
				}

				stackTrace.append(System.getProperty("line.separator"));
			}

			jetSetErrorView.assignData("ExceptionMessage", e.toString());
			jetSetErrorView.assignData("ExceptionStackTrace", stackTrace);

			if(e.getCause() != null)
			{
				jetSetErrorView.assignData("ExceptionCause", e.getCause().toString());
				jetSetErrorView.assignData("ExceptionCauseMessage", e.getCause().getMessage());

				for(StackTraceElement stack : e.getCause().getStackTrace())
				{
					String stackItem = stack.toString();

					if(stackItem.contains("system.") || stackItem.contains("application."))
					{
						stackTraceCause.append("<span class=\"stack_jetset\">at ");
						stackTraceCause.append(stackItem);
						stackTraceCause.append("</span><br />");
					}
					else if(!stackItem.contains("org.") && !stackItem.contains("java."))
					{
						stackTraceCause.append("<span class=\"stack\">at ");
						stackTraceCause.append(stackItem);
						stackTraceCause.append("</span><br />");
					}

					stackTraceCause.append(System.getProperty("line.separator"));
				}

				jetSetErrorView.assignData("ExceptionCauseStackTrace", stackTraceCause);
			}
		}

		jetSetErrorView.assignData("RequestURL", jsr.request.getRequestURI());
		jetSetErrorView.assignData("RequestGET", jsr.request.getQueryString());
		jetSetErrorView.assignData("RequestPOST", jsr.request.getParameterMap().toString());

		jetSetErrorView.assignData("RequestServer", new HashMap<String, String>()
		{{
			put("authType",             jsr.request.getAuthType());
			put("method",               jsr.request.getMethod());
			put("pathTranslated",       jsr.request.getPathTranslated());
			put("requestURI",           jsr.request.getRequestURI());
			put("servletPath",          jsr.request.getServletPath());
		}});

		Map<String, String> requestHeader = new HashMap<>();
		Enumeration<String> headerEnum = jsr.request.getHeaderNames();

		while(headerEnum.hasMoreElements())
		{
			String headerItem = headerEnum.nextElement();
			requestHeader.put(
					headerItem,
					jsr.request.getHeader(headerItem)
			);
		}

		jetSetErrorView.assignData("RequestHeader", requestHeader);

		try
		{
			jetSetErrorView.loadView("jetSetError");

			assert e != null;
			e.printStackTrace();
		}
		catch (Exception ee)
		{
			System.out.println("Failed handling error. You should probably review your server configuration...");
			ee.printStackTrace();
		}
	}

}
