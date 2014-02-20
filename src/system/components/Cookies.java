package system.components;

import system.JetSetModel;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

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
	HttpServletRequest request;
	Cookie[] cookies;

	public Cookies(HttpServletRequest request)
	{
		this.request = request;
		this.cookies = this.request.getCookies();
	}

	/**
	 * Get all cookies as an array
	 * @return Cookie array of the HttpServletRequest
	 */
	public Cookie[] getRawCookies()
	{
		return this.cookies;
	}

	/**
	 * Get a given cookie object
	 * @param cookieName Cookie name to search for
	 * @return Cookie object is found, otherwise null cookie
	 */
	public Cookie getRawCookie(String cookieName)
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
			return new Cookie(null, null);
		}
		else
		{
			return returnCookie;
		}
	}

	// TODO: Create setter
}
