package gui;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class debug
 */
@WebServlet("/debug")
public class debug extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public debug() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		Date time = new Date();
		out.println("<html>");
    out.println("<script charset='UTF-8'> window['adrum-start-time'] = new Date().getTime(); (function(config){ config.appKey = 'EUM-AAB-AUM'; config.adrumExtUrlHttp = 'http://cdn.appdynamics.com';  config.adrumExtUrlHttps = 'https://cdn.appdynamics.com';  config.beaconUrlHttp = 'http://dmoormandevelopmentlab-kfk90btr.appd-sales.com:7001';    config.beaconUrlHttps = 'https://dmoormandevelopmentlab-kfk90btr.appd-sales.com:7002';    config.xd = {enable : true}; })(window['adrum-config'] || (window['adrum-config'] = {})); </script><script src='//cdn.appdynamics.com/adrum/adrum-4.5.17.2890.js'></script>");
		out.println("<b>Debugging at " + time + "</b>");
    out.println("<div>Embed tag </div><embed width='480' height='500' src='https://www.youtube.com/embed/il_t1WVLNxk'>");
    out.println("<div>iframe</div><iframe width='560' height='315' src='https://www.youtube.com/embed/XD8kvGWhkPo' frameborder='0' allow='accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture' allowfullscreen></iframe>");
		out.println("</html>");
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
