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

import static application.config.JetSetConfig.ENCRYPTION_KEY;

import system.components.Cookies;
import system.components.Encryption;
import system.components.Input;
import system.components.View;

@SuppressWarnings("unused")
public class JetSetController
{
	protected final JetSetRequest jsr;

	protected final View jsView;
	protected final Input jsInput;

	protected final String methodParameter;
	protected final int methodId;
	protected final int methodTargetId;

	protected Encryption jsEncryption;
	protected Cookies jsCookies;

	/**
	 * Super controller constructor
	 * @param jsr JetSetRequest for the controller
	 * @param methodParameter Method parameter (defaults to jsConfig.DEFAULT_METHOD_PARAMETER)
	 * @param methodId Method ID (defaults to 0)
	 * @param methodTargetId Method target ID (defaults to 0)
	 */
	protected JetSetController(JetSetRequest jsr, String methodParameter, int methodId, int methodTargetId)
	{
		// Set up variables
		this.jsr = jsr;
		this.methodParameter = methodParameter;
		this.methodId = methodId;
		this.methodTargetId = methodTargetId;

		// Initialize components
		this.jsInput = new Input(jsr.request);
		this.jsView = new View(jsr);

		// Send HTTP headers
		jsr.response.setStatus(200);
		jsr.response.setHeader("Content-Type", "text/html;charset=utf8");
		jsr.response.setHeader("Cache-Control", "no-cache");
	}

	/**
	 * Load Encryption component
	 */
	protected void loadEncryption()
	{
		this.jsEncryption = new Encryption(ENCRYPTION_KEY);
	}

	/**
	 * Load Cookies component
	 */
	protected void loadCookies()
	{
		this.jsCookies = new Cookies(jsr);
	}

}