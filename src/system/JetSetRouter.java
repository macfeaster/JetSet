package system;

import application.config.JetSetConfig;
import org.eclipse.jetty.http.HttpURI;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class JetSetRouter
{
	/**
	 * Method for processing incoming servlet requests
	 * @param jsr The JetSetRequest with request/response data
	 */
	@SuppressWarnings({"unchecked", "ConstantConditions"})
	public void process(JetSetRequest jsr)
	{
		// Set up system variables
		ClassLoader classLoader = JetSetRouter.class.getClassLoader();
		HttpURI httpURI = new HttpURI(jsr.request.getRequestURI());
		String[] jetSetURI = httpURI.toString().substring(1).split("/");
		JetSetConfig jetSetConfig = new JetSetConfig();
		JetSetError jetSetError = new JetSetError();

		// Set up routing variables
		String controllerName = jetSetConfig.defaultController;
		String methodName = jetSetConfig.defaultMethod;
		String methodParameter = jetSetConfig.defaultMethodParameter;
		int methodId = 0;
		int methodTargetId = 0;

		/**
		 * @block JetSetRouter/Router
		 * This block routes incoming requests to the right controllers with data
		 * JetSet URL:s _always_ use the same format:
		 *   web.com/controller/method/methodParameter/methodID/methodTargetID
		 *
		 * -- ControllerClass is loaded and constructed dynamically, defaults to jsConfig.defaultController
		 * -- ControllerMethod is invoked when requested, defaults to jsConfig.defaultMethod
		 * -- MethodParameter specifies method action (e.g. run, edit), defaults to jsConfig.defaultMethodParameter
		 * -- MethodID specifies content ID to be modified, defaults to 0
		 * -- MethodTargetID specifies a target ID, e.g. a parent ID or similar, defaults to 0
		 */
		// Determine requested controller
		if(jetSetURI.length > 0)
		{
			if(!jetSetURI[0].isEmpty())
			{
				controllerName = jetSetURI[0].substring(0, 1).toUpperCase() + jetSetURI[0].substring(1);
			}
		}
		// Determine requested method
		if(jetSetURI.length > 1)
		{
			methodName = (!jetSetURI[1].isEmpty()) ? jetSetURI[1] : jetSetConfig.defaultMethod;
		}
		// Determine method parameter
		if(jetSetURI.length > 2)
		{
			methodParameter = (!jetSetURI[2].isEmpty()) ? jetSetURI[2] : jetSetConfig.defaultMethodParameter;
		}
		// Determine method ID
		if(jetSetURI.length > 3)
		{
			try
			{
				methodId = Integer.parseInt(jetSetURI[3]);
			}
			catch (NumberFormatException e)
			{
				methodId = 0;
			}
		}
		// Determine method target ID
		if(jetSetURI.length > 4)
		{
			try
			{
				methodTargetId = Integer.parseInt(jetSetURI[4]);
			}
			catch (NumberFormatException e)
			{
				methodTargetId = 0;
			}
		}

		/**
		 * @block JetSetRouter/Runner
		 * This block loads and invokes classes and methods dynamically based on the above given request data
		 * Controllers are stored in project package/path {$PROJECT_ROOT}/application/controllers
		 * A request to /products
		 */
		try
		{
			Class controllerClass = classLoader.loadClass("application.controllers." + controllerName + "Controller");

			try
			{
				// Create instance
				Constructor constructor = controllerClass.getConstructor(
						JetSetRequest.class,
						String.class,
						int.class,
						int.class
				);
				Object instance = constructor.newInstance(
						jsr,
						methodParameter,
						methodId,
						methodTargetId
				);

				// Invoke method
				Method method = controllerClass.getMethod(methodName);
				method.invoke(instance);
			}
			catch (Exception e)
			{
				jetSetError.handleError(jsr, e);
			}
		}
		catch (ClassNotFoundException e)
		{
			jetSetError.handleError(jsr, e);
		}
	}
}