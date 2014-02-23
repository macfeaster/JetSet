package system;

import application.config.JetSetConfig;
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
		JetSetConfig jetSetConfig = new JetSetConfig();
		Connection connection;

		String sqlServerUrl = "jdbc:mysql://" + jetSetConfig.sqlServer + ":" + jetSetConfig.sqlServerPort + "/" + jetSetConfig.sqlDatabase;

		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(sqlServerUrl, jetSetConfig.sqlUsername, jetSetConfig.sqlPassword);
			jetSetDb = DSL.using(connection, SQLDialect.MYSQL);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
