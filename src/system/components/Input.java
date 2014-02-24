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

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
public class Input
{
	private final HttpServletRequest request;
	private Map<String, String> queryStringCache;

	public Input(HttpServletRequest request)
	{
		this.request = request;
	}

	/**
	 * Get the HTTP GET query string as a map
	 * @return Key value map
	 */
	public Map<String, String> getInputGET()
	{
		// Build query string cache map if such does not exist
		if(queryStringCache.isEmpty())
		{
			queryStringCache = parseQueryString(request.getQueryString());
		}

		// Return the query string cache map
		return queryStringCache;
	}

	/**
	 * Get a given HTTP GET query string key's value
	 * @param key The key to look for
	 * @return Value if found, otherwise empty string
	 */
	public String getInputGET(String key)
	{
		// Build GET parameter map if no such exists
		if(queryStringCache == null)
		{
			queryStringCache = parseQueryString(request.getQueryString());
		}

		// Return key if found, otherwise null
		if(queryStringCache.get(key) == null)
		{
			return null;
		}

		return queryStringCache.get(key);
	}

	/**
	 * Get the HTTP POST data as a map
	 * @return A filled or empty map depending on data existence
	 */
	public Map<String, String[]> getInputPOST()
	{
		// Return map of POST parameters
		return request.getParameterMap();
	}

	/**
	 * Get a given HTTP POST data key's value
	 * @param key The key to look for
	 * @return Value if found, otherwise empty string array
	 */
	public String[] getInputPost(String key)
	{
		// Assign given parameter map to array
		String[] value = request.getParameterMap().get(key);

		// Return string array with values, if non-existent return empty array
		if(value == null)
		{
			return new String[0];
		}

		return value;
	}

	/**
	 * Parse a query or hash string to a map
	 * @param urlStringRaw Hash or query string
	 * @return Key-value map of the contents
	 */
	Map<String, String> parseQueryString(String urlStringRaw)
	{
		// If the Query String is empty, return an empty map
		if(urlStringRaw == null)
		{
			return new HashMap<>();
		}

		// Set up variables
		String[] params = urlStringRaw.split("&");
		Map<String, String> map = new HashMap<>();

		// Build map through iteration
		for (String param : params)
		{
			// Split to key-value pair and set key
			String[] pair = param.split("=");
			String name = pair[0];

			// Put key-value to map if value exists, otherwise key-null
			if(pair.length > 1)
			{
				map.put(name, pair[1]);
			}
			else
			{
				map.put(name, null);
			}
		}

		// Return result
		return map;
	}
}
