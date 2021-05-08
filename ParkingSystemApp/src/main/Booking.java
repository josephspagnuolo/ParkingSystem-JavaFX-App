package main;

public class Booking {
	
	public String BookingID;
	public String email;
	public String parkingSpaceNumber;
	public String expiryTime;
	public String paymentStatus;
	
	public Booking(String BookingID, String email, String parkingSpaceNumber, String expiryTime, String paymentStatus) {
		super();
		this.BookingID = BookingID;
		this.email = email;
		this.parkingSpaceNumber = parkingSpaceNumber;
		this.expiryTime = expiryTime;
		this.paymentStatus = paymentStatus;
	}
	
	public Booking() {
		super();
	}

	public String getBookingID() {
		return BookingID;
	}

	public void setBookingID(String bookingID) {
		BookingID = bookingID;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getParkingSpaceNumber() {
		return parkingSpaceNumber;
	}

	public void setParkingSpaceNumber(String parkingSpaceNumber) {
		this.parkingSpaceNumber = parkingSpaceNumber;
	}

	public String getExpiryTime() {
		return expiryTime;
	}

	public void setExpiryTime(String expiryTime) {
		this.expiryTime = expiryTime;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	
	@Override
	public String toString() {
		return "Booking [ID=" + BookingID + "] under email=" + email + " for parking space number " + parkingSpaceNumber + " expires at " + expiryTime + " and is " + paymentStatus;
	}

}
