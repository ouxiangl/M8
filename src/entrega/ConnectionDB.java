package entrega;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

public class ConnectionDB {
	
	static String url = "jdbc:hsqldb:file:C:\\Users\\LIN\\eclipse-workspace\\M8\\WebContent\\WEB-INF\\db\\srv_db";
	public static Connection getConnection() {
		
		Properties prop = Proper.getProp();
		Connection conn = null;
		try {
			Class.forName(prop.getProperty("driver.ddbb"));
			conn = DriverManager.getConnection(prop.getProperty("bbdd.direccion"),prop.getProperty("bbdd.user"),prop.getProperty("bbdd.pass"));
			Logger.getLogger("conn: "+conn);
		}catch(ClassNotFoundException | SQLException e) {
			Logger.getLogger("Error en connectionDB");
			Logger.getLogger("Error: "+e);
		}
		return conn;
	}
	private ConnectionDB() {
	    throw new IllegalStateException("Utility class");
	  }
}
