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

import static application.config.JetSetConfig.DEFAULT_CONTROLLER;
import static application.config.JetSetConfig.DEFAULT_METHOD;
import static application.config.JetSetConfig.DEFAULT_METHOD_PARAMETER;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Locale;

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
		String[] jetSetURI = jsr.request.getRequestURI().substring(1).split("/");
		JetSetError jetSetError = new JetSetError();

		// Set up routing variables
		String controllerName = DEFAULT_CONTROLLER;
		String methodName = DEFAULT_METHOD;
		String methodParameter = DEFAULT_METHOD_PARAMETER;
		int methodId = 0;
		int methodTargetId = 0;

		/**
		 * @block JetSetRouter/Router
		 * This block routes incoming requests to the right controllers with data
		 * JetSet URL:s _always_ use the same format:
		 *   web.com/controller/method/methodParameter/methodID/methodTargetID
		 *
		 * -- ControllerClass is loaded and constructed dynamically, defaults to jsConfig.DEFAULT_CONTROLLER
		 * -- ControllerMethod is invoked when requested, defaults to jsConfig.DEFAULT_METHOD
		 * -- MethodParameter specifies method action (e.g. run, edit), defaults to jsConfig.DEFAULT_METHOD_PARAMETER
		 * -- MethodID specifies content ID to be modified, defaults to 0
		 * -- MethodTargetID specifies a target ID, e.g. a parent ID or similar, defaults to 0
		 */
		// Determine requested controller
		if(jetSetURI.length > 0)
		{
			if(!jetSetURI[0].isEmpty())
			{
				controllerName = jetSetURI[0].substring(0, 1).toUpperCase(Locale.US) + jetSetURI[0].substring(1);
			}
		}
		// Determine requested method
		if(jetSetURI.length > 1)
		{
			methodName = (!jetSetURI[1].isEmpty()) ? jetSetURI[1] : DEFAULT_METHOD;
		}
		// Determine method parameter
		if(jetSetURI.length > 2)
		{
			methodParameter = (!jetSetURI[2].isEmpty()) ? jetSetURI[2] : DEFAULT_METHOD_PARAMETER;
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