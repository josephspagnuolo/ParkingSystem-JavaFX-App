package main;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class OfficerViewDetailsController {
	
	@FXML
    private ComboBox<String> bookingOptions;

    @FXML
    private Text numberText;

    @FXML
    private Text paymentText;

    @FXML
    private Text timeText;

    @FXML
    private Text name;

    @FXML
    public void initialize() throws Exception {
    	name.setText("Viewing Bookings of " + MaintainUser.currentUser.getFirstName() + " " + MaintainUser.currentUser.getLastName() + ".");
    	numberText.setVisible(false);
    	paymentText.setVisible(false);
    	timeText.setVisible(false);
    	String path = "C:\\Users\\Joseph\\Desktop\\test4.txt";
		MaintainBooking bookings = new MaintainBooking();
		bookings.load(path);
		for(Booking b: bookings.bookings){
			System.out.println(b.toString());
			if(b.getEmail().equals(MaintainUser.currentUser.getEmail())) {
				bookingOptions.getItems().add("Booking " + b.getBookingID());
			}
		}
	}
    
    @FXML
    void selection(ActionEvent event) throws Exception {
    	
    	String path = "C:\\Users\\Joseph\\Desktop\\test4.txt";
		MaintainBooking bookings = new MaintainBooking();
		bookings.load(path);
		for(Booking b: bookings.bookings){
			System.out.println(b.toString());
			if(("Booking " + b.getBookingID()).equals(bookingOptions.getValue())) {
				numberText.setText("" + b.getParkingSpaceNumber());
		    	paymentText.setText("" + b.getPaymentStatus());
		    	timeText.setText("" + b.getExpiryTime());
		    	numberText.setVisible(true);
		    	paymentText.setVisible(true);
		    	timeText.setVisible(true);
			}
		}
    }
    
    @FXML
    void back(ActionEvent event) throws Exception {
    	try {
			Parent homePageParent = FXMLLoader.load(getClass().getResource("OfficerViewCustomer.fxml"));
			Scene homePageScene = new Scene(homePageParent);
			Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			appStage.setScene(homePageScene);
			appStage.centerOnScreen();
			appStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

}
