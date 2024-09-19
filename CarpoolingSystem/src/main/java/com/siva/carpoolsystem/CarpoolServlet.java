package com.siva.carpoolsystem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Servlet implementation class CarpoolServlet
 */

public class CarpoolServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    private static final Logger logData = Logger.getLogger(CarpoolServlet.class.getName());

	 private List<String> acceptedRides;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CarpoolServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void init() throws ServletException {
        super.init();
        // Initialize the list of available carpool rides
        acceptedRides = new ArrayList<>();
        acceptedRides.add("Ride 1: Ontario to Montreal");
        acceptedRides.add("Ride 2: Hyderabad to Delhi");
        logData.info("CarpoolServlet has been initialized with available rides as followes ::  " + acceptedRides);
    }

    
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Log the incoming request type and URL
        String incomeRequestType = request.getMethod();
        String incomeRequestUrl = request.getRequestURL().toString();
        logData.info("These are the Incoming request: " + incomeRequestType + " " + incomeRequestUrl);
        
        super.service(request, response);
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        // Using StringBuilder to build the HTML response
        StringBuilder responseBody = new StringBuilder();
        responseBody.append("<html>");
        responseBody.append("<head><title>Carpooling System</title></head>");
        responseBody.append("<body>");
        responseBody.append("<P>Welcome to the Carpooling System</p>");
        responseBody.append("</body>");
        responseBody.append("</html>");

        // Sending the response
        response.getWriter().write(responseBody.toString());
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	@Override
    public void destroy() {
        // Log a message indicating the servlet is being terminated
        logData.info("The Created CarpoolServlet has been  destroyed.");
        super.destroy();
    }

}
