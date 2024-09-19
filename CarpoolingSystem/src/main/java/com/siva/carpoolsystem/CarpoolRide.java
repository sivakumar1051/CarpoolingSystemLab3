package com.siva.carpoolsystem;


public class CarpoolRide {
	
	// Varaible declaration
    private String rideStartLocation;
    private String rideDestination;
    private int noOfSeats;
    
    // constructor initalization
	public CarpoolRide(String rideStartLocation, String rideDestination, int noOfSeats) {
		super();
		this.rideStartLocation = rideStartLocation;
		this.rideDestination = rideDestination;
		this.noOfSeats = noOfSeats;
	}
	
	//getters and setters generation
	public String getRideStarLocation() {
		return rideStartLocation;
	}
	public void setRideStarLocation(String rideStartLocation) {
		this.rideStartLocation = rideStartLocation;
	}
	public String getRideDestination() {
		return rideDestination;
	}
	public void setRideDestination(String rideDestination) {
		this.rideDestination = rideDestination;
	}
	public int getNoOfSeats() {
		return noOfSeats;
	}
	public void setNoOfSeats(int noOfSeats) {
		this.noOfSeats = noOfSeats;
	}

   
}
