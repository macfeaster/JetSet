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

package system;

import static application.config.JetSetConfig.SQL_SERVER;
import static application.config.JetSetConfig.SQL_SERVER_PORT;
import static application.config.JetSetConfig.SQL_USERNAME;
import static application.config.JetSetConfig.SQL_PASSWORD;
import static application.config.JetSetConfig.SQL_DATABASE;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.DriverManager;

@SuppressWarnings("unused")
public class JetSetModel
{
	protected DSLContext jetSetDb;

	public JetSetModel()
	{
		Connection connection;
		String sqlServerUrl = "jdbc:mysql://" + SQL_SERVER + ":" + SQL_SERVER_PORT + "/" + SQL_DATABASE;

		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(sqlServerUrl, SQL_USERNAME, SQL_PASSWORD);
			jetSetDb = DSL.using(connection, SQLDialect.MYSQL);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
