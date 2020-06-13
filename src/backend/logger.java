package backend;

import java.io.IOException;
import java.sql.Connection;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;

public class logger {
  private static final String MYSQL_CONNECTION_STRING = "jdbc:mysql://root:appdynam1cs@localhost:3306/instrumentdata?serverTimezone=America/Chicago&useJDBCCompliantTimezoneShift=true";
  private static Logger log = Logger.getLogger("log");
  
  public logger() throws ClassNotFoundException, SQLException, IOException {
	  
	FileHandler fileHandler = new FileHandler("/tmp/instrumentable-tomcat/runtime.log");
	log.addHandler(fileHandler);

    Class.forName("com.mysql.cj.jdbc.Driver");

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

    connection.close();
  }

  public void logRequest(HttpServletRequest request, String targetUrl) throws ClassNotFoundException, SQLException {

    Class.forName("com.mysql.cj.jdbc.Driver");
    Connection connection = DriverManager.getConnection(MYSQL_CONNECTION_STRING);

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