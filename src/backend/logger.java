package backend;

import javax.servlet.http.HttpServletRequest;

public class logger {

  public void logRequest(HttpServletRequest request) throws ClassNotFoundException {
    Class.forName("com.mysql.cj.jdbc.Driver");  
    
  }
}
