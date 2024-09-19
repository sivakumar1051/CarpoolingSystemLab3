package com.siva.carpoolsystem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

@WebServlet(name = "CarpoolServlet", urlPatterns = "/Carpool")
public class CarpoolServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logData = Logger.getLogger(CarpoolServlet.class.getName());

    // List to store available rides
    private List<CarpoolRide> ridesAvailable = Collections.synchronizedList(new ArrayList<>());
    private int maxRides; // Max rides limit

    @Override
    public void init() throws ServletException {
        super.init();
        // Read maxRides from initialization parameters
        String maxRidesParam = getServletConfig().getInitParameter("maxRides");
        maxRides = Integer.parseInt(maxRidesParam);
        logData.info("CarpoolServlet initialized with max rides: " + maxRides);
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.service(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        InputStream inputStream = getServletContext().getResourceAsStream("/carpoolIndex.html");
        String htmlContent = new Scanner(inputStream, "UTF-8").useDelimiter("\\A").next();
        inputStream.close();

        String availableRidesData = generateRideListHtml();
        String finalOutput = htmlContent.replace("<!-- Available rides will be populated here -->", availableRidesData);
        response.getWriter().write(finalOutput);
    }

    private String generateRideListHtml() {
        StringBuilder rideDataBuilder = new StringBuilder();
        synchronized (ridesAvailable) {
            if (ridesAvailable.isEmpty()) {
                rideDataBuilder.append("<tr><td colspan='3'>No available rides at the moment.</td></tr>");
            } else {
                for (CarpoolRide ride : ridesAvailable) {
                    rideDataBuilder.append("<tr>")
                        .append("<td>").append(ride.getRideStarLocation()).append("</td>")
                        .append("<td>").append(ride.getRideDestination()).append("</td>")
                        .append("<td>").append(ride.getNoOfSeats()).append("</td>")
                        .append("</tr>");
                }
            }
        }
        return rideDataBuilder.toString();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String startLocation = request.getParameter("startLocation");
        String destination = request.getParameter("destination");
        int seatsAvailable = Integer.parseInt(request.getParameter("seatsAvailable"));

        // Create a new ride and add it to the list only if it doesn't exceed maxRides
        synchronized (ridesAvailable) {
            if (ridesAvailable.size() < maxRides) {
                CarpoolRide newCarpoolRide = new CarpoolRide(startLocation, destination, seatsAvailable);
                ridesAvailable.add(newCarpoolRide);
                logData.info("New ride offered: " + newCarpoolRide);
            } else {
                logData.warning("Max rides limit reached. Cannot add new ride.");
                // Optionally, you could handle this by informing the user in the response.
            }
        }

        response.sendRedirect(request.getContextPath() + "/Carpool");
    }

    @Override
    public void destroy() {
        logData.info("CarpoolServlet destroyed.");
        super.destroy();
    }
}
