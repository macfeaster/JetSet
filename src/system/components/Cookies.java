/*
 * This file is part of JetSet, a lightweight Java Enterprise Web MVC framework.
 * Modified as of 2/24/14 4:06 PM
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

package system.components;

import system.JetSetModel;
import system.JetSetRequest;

import javax.servlet.http.Cookie;

/**
 * These methods all access the raw HTTP cookies
 * provided by the servlet. It is STRONGLY recommended
 * that you use the JetSet Session class instead
 * whenever possible, as it encrypts and validates
 * user sessions.
 */
@SuppressWarnings("unused")
public class Cookies extends JetSetModel
{
	JetSetRequest jsr;
	Cookie[] cookies;

	public Cookies(JetSetRequest jsr)
	{
		this.jsr = jsr;
		this.cookies = jsr.request.getCookies();
	}

	/**
	 * Get a given cookie object
	 * @param cookieName Cookie name to search for
	 * @return Cookie object is found, otherwise null cookie
	 */
	public Cookie getCookie(String cookieName)
	{
		Cookie returnCookie = null;

		for(Cookie cookie : this.cookies)
		{
			if(cookie.getName().equals(cookieName))
			{
				returnCookie = cookie;
				break;
			}
		}

		if(returnCookie == null)
		{
			return null;
		}

		return returnCookie;
	}

	public void setCookie(String name, String value)
	{
		// Create and set cookie
		Cookie cookie = new Cookie(name, value);
		jsr.response.addCookie(cookie);
	}
}
