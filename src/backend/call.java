package backend;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.net.HttpURLConnection;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class call
 */
@WebServlet("/call")
public class call extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private static final String DEFAULT_URL = "http://scanme.nmap.org";
  private static final String[] HTTP_ACCEPTABLE_RESPONSE_CODES = {"200", "301", "302"};
  private static final String HTTP_GET = "GET";

  /**
   * @see HttpServlet#HttpServlet()
   */
  public call() {
    super();
  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    PrintWriter out = response.getWriter();
    String requestUrl = request.getParameter("url");
    logger requestLogger = new logger();
    
    if (requestUrl == null) {
    	out.println("Url not provided to servlet, running with the default");
    	requestUrl = DEFAULT_URL;
    } else {
      out.println("Url provided in request.");
    }
    out.println("Url: " + requestUrl);

    try {
      requestLogger.logRequest(request);
      URL url = new URL(requestUrl);
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod(HTTP_GET);
      connection.setRequestProperty("Accept", "application/json");

      if (Arrays.asList(HTTP_ACCEPTABLE_RESPONSE_CODES).contains(connection.getResponseCode())) {
        throw new RuntimeException("Downstream HTTP request failed with response code: "
            + connection.getResponseCode());
      } else {
        out.println(":: Connected successfully to " + requestUrl + "...");
        out.println("----------------------------------------------------------------");
      }
      
      BufferedReader buffer = new BufferedReader(
          new InputStreamReader(connection.getInputStream())
      );
      
      String pageOut;
      while ((pageOut = buffer.readLine()) != null) {
        out.println(pageOut);
      }
      
      connection.disconnect();
    } catch (Exception e) {
      out.println("An error has occurred in the web request: " + e.getMessage());
    }
}

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }
}
