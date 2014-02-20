package system;

import system.components.View;

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

		jetSetErrorView.templateResolver.setPrefix("/WEB-INF/");
		jsr.response.setStatus(200);

		if(e != null)
		{
			for(StackTraceElement stack : e.getStackTrace())
			{
				String stackItem = stack.toString();

				if(stackItem.contains("system.Jet") || stackItem.contains("application."))
				{
					stackTrace.append("<span class=\"stack_jetset\">at ");
					stackTrace.append(stackItem);
					stackTrace.append("</span><br />");
				}
				else
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

		Map<String, String> requestHeader = new HashMap<String, String>();
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
