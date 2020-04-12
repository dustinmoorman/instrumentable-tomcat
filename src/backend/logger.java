package backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;

public class logger {
  private static final String MYSQL_CONNECTION_STRING = "jdbc:mysql://root:appdynam1cs@localhost:3306/instrumentdata?serverTimezone=America/Chicago&useJDBCCompliantTimezoneShift=true";

  public logger() throws ClassNotFoundException, SQLException {
    Class.forName("com.mysql.cj.jdbc.Driver");

    Connection connection = DriverManager.getConnection(MYSQL_CONNECTION_STRING);
    
    Statement createTableStatement = connection.createStatement();
    String createTableDML = "CREATE TABLE IF NOT EXISTS `request_logs` ("
      + "id INT(10) UNSIGNED AUTO_INCREMENT PRIMARY KEY, "
      + "remote_host_name VARCHAR(255), "
      + "remote_host_ip VARCHAR(255) NOT NULL, "
      + "target_url VARCHAR(255) NOT NULL, "
      + "date TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
      + ");";
    
    createTableStatement.executeUpdate(createTableDML);
    
    connection.close();
  }
  
  public void logRequest(HttpServletRequest request) throws ClassNotFoundException, SQLException {
    
    Class.forName("com.mysql.cj.jdbc.Driver");  
    Connection connection = DriverManager.getConnection(MYSQL_CONNECTION_STRING);
    
    connection.close();
  }
}
