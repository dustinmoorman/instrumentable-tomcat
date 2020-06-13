package backend;

import java.io.IOException;
import java.sql.Connection;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;

public class logger {
  
  private static final String MYSQL_CONNECTION_STRING = "jdbc:mysql://root:appdynam1cs@localhost:3306/instrumentdata?serverTimezone=America/Chicago&useJDBCCompliantTimezoneShift=true";
  private static final String MYSQL_JDBC_DRIVER_STRING = "com.mysql.cj.jdbc.Driver";
  
  private static Logger log = Logger.getLogger("log");
  
  public logger() throws ClassNotFoundException, SQLException, IOException {
	  
	FileHandler fileHandler = new FileHandler("/tmp/instrumentable-tomcat/runtime.log");
	log.addHandler(fileHandler);

	SimpleFormatter formatter = new SimpleFormatter();
	fileHandler.setFormatter(formatter);
	
	log.info("Logging ready, Checking database...");
	
    Class.forName(MYSQL_JDBC_DRIVER_STRING);

    Connection connection = DriverManager.getConnection(MYSQL_CONNECTION_STRING);

    Statement createTableStatement = connection.createStatement();
    String createTableDDL = "CREATE TABLE IF NOT EXISTS `request_logs` ("
      + "id INT(10) UNSIGNED AUTO_INCREMENT PRIMARY KEY, "
      + "remote_host_name VARCHAR(255), "
      + "remote_host_ip VARCHAR(255) NOT NULL, "
      + "target_url VARCHAR(255) NOT NULL, "
      + "date TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
      + ");";

    createTableStatement.executeUpdate(createTableDDL);
    
	log.info("Database ready, let's go!");

    connection.close();
  }

  public void logRequest(HttpServletRequest request, String targetUrl) throws ClassNotFoundException, SQLException {

    Class.forName(MYSQL_JDBC_DRIVER_STRING);
    Connection connection = DriverManager.getConnection(MYSQL_CONNECTION_STRING);
    
    log.info("Request initiated from " 
      + request.getRemoteHost()
      + " ("
      + request.getRemoteAddr()
      + ") against target: "
      + targetUrl);

    Statement insertRequestStatement = connection.createStatement();
    String insertRequestDQL = "INSERT INTO `request_logs` ("
      + "remote_host_name, remote_host_ip, target_url) "
      + "VALUES ( '"
      + request.getRemoteHost() + "', '"
      + request.getRemoteAddr() + "', '"
      + targetUrl + "');";

    insertRequestStatement.executeUpdate(insertRequestDQL);

    connection.close();
  }
}