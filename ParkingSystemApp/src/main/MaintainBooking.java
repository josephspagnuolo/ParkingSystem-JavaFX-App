package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MaintainBooking {
	
	public ArrayList<Booking> bookings = new ArrayList<Booking>();
	
	public void load(String path) throws Exception{
		ArrayList<String> eachBooking = new ArrayList<String>();
		FileInputStream str = new FileInputStream(path);
		BufferedReader br = new BufferedReader(new InputStreamReader(str));
		String strline;
		
		while((strline = br.readLine()) != null) {
			eachBooking.add(strline);
		}
		
		for(int i = 0; i < eachBooking.size(); i+=5) {
			Booking booking = new Booking();
			booking.setBookingID(eachBooking.get(i));
			booking.setEmail(eachBooking.get(i+1));
			booking.setParkingSpaceNumber(eachBooking.get(i+2));
			booking.setExpiryTime(eachBooking.get(i+3));
			booking.setPaymentStatus(eachBooking.get(i+4));
			bookings.add(booking);
		}
		
		str.close();
	}
	
	public void update(String path) throws Exception{
		try {		
			BufferedWriter br = new BufferedWriter(new FileWriter(path));
				for(Booking b: bookings){
					br.write(b.getBookingID());
					br.newLine();
					br.write(b.getEmail());
					br.newLine();
					br.write(b.getParkingSpaceNumber());
					br.newLine();
					br.write(b.getExpiryTime());
					br.newLine();
					br.write(b.getPaymentStatus());
					br.newLine();
				}
				br.close();
			
			}catch (Exception e) {
				e.printStackTrace();
			}
	}

}
