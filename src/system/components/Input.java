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
		if(queryStringCache.isEmpty())
		{
			queryStringCache = parseQueryString(request.getQueryString());
		}

		// Return key if found, otherwise null
		try
		{
			return queryStringCache.get(key);
		}
		catch (NullPointerException e)
		{
			return null;
		}
	}

	/**
	 * Get the HTTP POST data as a map
	 * @return A filled or empty map depending on data existence
	 */
	public Map<String, String[]> getInputPOST()
	{
		// Return POST map if existent, otherwise empty map
		try
		{
			return request.getParameterMap();
		}
		catch (NullPointerException e)
		{
			return new HashMap<>();
		}
	}

	/**
	 * Get a given HTTP POST data key's value
	 * @param key The key to look for
	 * @return Value if found, otherwise empty string array
	 */
	public String[] getInputPost(String key)
	{
		// Return string array with values, if non-existent return empty array
		try
		{
			return request.getParameterMap().get(key);
		}
		catch (NullPointerException e)
		{
			return new String[] {""};
		}
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
