package test;

import static org.junit.Assert.*;

import org.junit.Test;

import main.Booking;
import main.MaintainBooking;
import main.MaintainUser;
import main.ParkingLot;
import main.ParkingSpace;
import main.UniqueID;
import main.User;

public class ParkingSystemTest {

	@Test
	public void test1() throws Exception {
		String path = "C:\\Users\\Joseph\\Desktop\\test2.txt";
		MaintainUser maintain = new MaintainUser();
	
		maintain.load2(path);
		assertEquals(maintain.officers.size(), 1);
	}
	
	@Test
	public void test2() throws Exception {
		String path = "C:\\Users\\Joseph\\Desktop\\test.txt";
		MaintainUser maintain = new MaintainUser();
	
		maintain.load(path);
		assertEquals(maintain.users.size(), 2);
	}
	
	@Test
	public void test3() throws Exception {
		String path = "C:\\Users\\Joseph\\Desktop\\test.txt";
		MaintainUser maintain = new MaintainUser();
	
		maintain.load(path);
		User newUser = new User("first","last","email","password");
		maintain.users.add(newUser);
		maintain.update(path);
		assertEquals(maintain.users.size(), 3);
	}
	
	@Test
	public void test4() throws Exception {
		String path = "C:\\Users\\Joseph\\Desktop\\test2.txt";
		MaintainUser maintain = new MaintainUser();
	
		maintain.load2(path);
		User newOfficer = new User("first","last","email","password");
		maintain.officers.add(newOfficer);
		maintain.update2(path);
		newOfficer.toString();
		assertEquals(maintain.officers.size(), 2);
	}
	
	@Test
	public void test5() throws Exception {
		String path = "C:\\Users\\Joseph\\Desktop\\test3.txt";
		ParkingLot maintain = new ParkingLot();
	
		maintain.load(path);
		assertEquals(maintain.parkingLot.size(), 4);
	}
	
	@Test
	public void test6() throws Exception {
		String path = "C:\\Users\\Joseph\\Desktop\\test4.txt";
		MaintainBooking maintain = new MaintainBooking();
	
		maintain.load(path);
		assertEquals(maintain.bookings.size(), 1);
	}
	
	@Test
	public void test7() throws Exception {
		String path = "C:\\Users\\Joseph\\Desktop\\test3.txt";
		ParkingLot maintain = new ParkingLot();
	
		maintain.load(path);
		ParkingSpace newUser = new ParkingSpace(true,"5");
		maintain.parkingLot.add(newUser);
		maintain.update(path);
		newUser.toString();
		ParkingSpace newUser2 = new ParkingSpace(false,"5");
		newUser2.toString();
		assertEquals(maintain.parkingLot.size(), 5);
	}
	
	@Test
	public void test8() throws Exception {
		String path = "C:\\Users\\Joseph\\Desktop\\test4.txt";
		MaintainBooking maintain = new MaintainBooking();
	
		maintain.load(path);
		Booking newOfficer = new Booking("1000000","email","1","12:00","paid");
		maintain.bookings.add(newOfficer);
		maintain.update(path);
		newOfficer.toString();
		UniqueID id2 = new UniqueID();
		id2.incID();
		int temp = id2.getID();
		assertEquals(maintain.bookings.size(), 2);
	}

}
