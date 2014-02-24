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

package application.config;

/**
 * JetSet Configuration
 */
public class JetSetConfig
{
	public static final String DEFAULT_CONTROLLER = "Default"; // "Controller" will be appended to this
    public static final String DEFAULT_METHOD = "index";
    public static final String DEFAULT_METHOD_PARAMETER = "process";

	public static final String SQL_SERVER = "localhost";
	public static final int SQL_SERVER_PORT = 3306;
	public static final String SQL_USERNAME = "root";
	public static final String SQL_PASSWORD = "alpine";
	public static final String SQL_DATABASE = "jetset";

	public static final String SITE_NAME = "JetSet Site";

	/**
	 * Below is the encryption key used for your JetSet installation.
	 * Do NOT share it with anybody. The key HAS to be a 32 character
	 * encryption key like the one below. Any key generated online or
	 * by Java that is 32 characters in length and ASCII will work.
	 */
	public static final String ENCRYPTION_KEY = "MWr/AEsRhAw2j8JZfGq74+NmVNyd=opC";
}
