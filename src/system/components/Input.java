package system.components;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
public class Input
{
	private final HttpServletRequest request;

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
		String queryString = request.getQueryString();
		return this.parseQueryString(queryString);
	}

	/**
	 * Get a given HTTP GET query string key's value
	 * @param key The key to look for
	 * @return Value if found, otherwise empty string
	 */
	public String getInputGET(String key)
	{
		String queryString = request.getQueryString();
		Map<String, String> map = parseQueryString(queryString);

		try
		{
			return map.get(key);
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
		if(urlStringRaw == null)
		{
			return new HashMap<>();
		}

		String[] params = urlStringRaw.split("&");
		Map<String, String> map = new HashMap<>();
		for (String param : params)
		{
			String name = param.split("=")[0];
			try
			{
				String value = param.split("=")[1];
				map.put(name, value);
			}
			catch (ArrayIndexOutOfBoundsException e)
			{
				String value = "";
				map.put(name, value);
			}
		}
		return map;
	}
}
