package main;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AdminChangePaymentController {
	
	@FXML
    private TextField emailField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField firstNameField;

    @FXML
    private Text booked;

    @FXML
    private Text exists;

    @FXML
    private TextField parkingSpaceNumberField;

    @FXML
    private Text verify;
    
    @FXML
    void adminChangePayment(ActionEvent event) throws Exception {
		exists.setVisible(false);
		verify.setVisible(false);
		booked.setVisible(false);
		String path = "C:\\Users\\Joseph\\Desktop\\test4.txt";
		MaintainBooking bookings = new MaintainBooking();
	
		bookings.load(path);
		String first = firstNameField.getText();
		String last = lastNameField.getText();
		String email = emailField.getText();
		String parkingSpaceNumber = parkingSpaceNumberField.getText();
		String path2 = "C:\\Users\\Joseph\\Desktop\\test.txt";
		MaintainUser maintain = new MaintainUser();
	
		maintain.load(path2);
		
		boolean customerExists = false;
		boolean bookingExists = false;
		boolean verified = false;
		for(User u: maintain.users){
			System.out.println(u.toString());
			if(email.equals(u.getEmail())) {
				if(first.equals(u.getFirstName())) {
					if(last.equals(u.getLastName())) {
						customerExists = true;
						for(Booking b: bookings.bookings){
							if(b.getEmail().equals(email) && b.getParkingSpaceNumber().equals(parkingSpaceNumber)) {
								bookingExists = true;
								if(b.getPaymentStatus().equals("Verified")) {
									verified = true;
									b.setPaymentStatus("Paid");
									bookings.update(path);
									Alert alert = new Alert(AlertType.INFORMATION);
									alert.initStyle(StageStyle.UNDECORATED);
									alert.setContentText("Payment Status has been updated.");
									alert.showAndWait();
									try {
						    			Parent homePageParent = FXMLLoader.load(getClass().getResource("AdminMain.fxml"));
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
						}
					}
				}
			}
		}
		if(!customerExists) {
			exists.setVisible(true);
		}
		else if(!bookingExists) {
			booked.setVisible(true);
		}
		else if(!verified) {
			verify.setVisible(true);
		}
	}
    
    @FXML
    void back(ActionEvent event) throws Exception {
    	try {
			Parent homePageParent = FXMLLoader.load(getClass().getResource("AdminMain.fxml"));
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
