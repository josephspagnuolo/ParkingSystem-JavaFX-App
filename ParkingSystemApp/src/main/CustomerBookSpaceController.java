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

public class CustomerBookSpaceController {
	
	@FXML
    private TextField bookingTimeField;

    @FXML
    private TextField parkingSpaceNumberField;

    @FXML
    private TextField licensePlateNumberField;

    @FXML
    private Text empty;

    @FXML
    private Text exists;

    @FXML
    private Text occupied;
    
	@FXML
    void customerConfirmBooking(ActionEvent event) throws Exception {
		exists.setVisible(false);
    	empty.setVisible(false);
    	occupied.setVisible(false);
    	boolean spaceExists = false;
    	String path = "C:\\Users\\Joseph\\Desktop\\test4.txt";
		MaintainBooking bookings = new MaintainBooking();
		bookings.load(path);
    	String space = parkingSpaceNumberField.getText();
		String time = bookingTimeField.getText();
		String license = licensePlateNumberField.getText();
		if(space.isEmpty() || time.isEmpty() || license.isEmpty()) {
			empty.setVisible(true);
		}
		else {
			ParkingLot lot = new ParkingLot();
			String path2 = "C:\\Users\\Joseph\\Desktop\\test3.txt";
			lot.load(path2);
			for(ParkingSpace p: lot.parkingLot){
				System.out.println(p.toString());
				if(space.equals(p.getSpaceNumber())) {
					spaceExists = true;
				}
			}
			if(spaceExists) {
				for(ParkingSpace p: lot.parkingLot){
					System.out.println(p.toString());
					if(space.equals(p.getSpaceNumber())) {
						if(p.isVacant()) {
							UniqueID id2 = new UniqueID();
							if(id2.getID() == 1000000) {
								if(bookings.bookings.isEmpty()) {
									
								}
								else {
									String y = bookings.bookings.get(bookings.bookings.size() - 1).getBookingID();
									int temp = Integer.parseInt(y);
									for(int i = 0; i < (temp - 1000000) + 1; i++){
										id2.incID();
									}
								}
							}
							Booking newBooking = new Booking("" + id2.getID(),MaintainUser.currentUser.email,space,time,"Unpaid");
							id2.incID();
							bookings.bookings.add(newBooking);
							bookings.update(path);
							p.setVacant(false);
							lot.update(path2);
							Alert alert = new Alert(AlertType.INFORMATION);
							alert.initStyle(StageStyle.UNDECORATED);
							alert.setContentText("Booking " + (id2.getID() - 1) + " has been confirmed.");
							alert.showAndWait();
							try {
				    			Parent homePageParent = FXMLLoader.load(getClass().getResource("CustomerMain.fxml"));
				    			Scene homePageScene = new Scene(homePageParent);
				    			Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				    			appStage.setScene(homePageScene);
				    			appStage.centerOnScreen();
				    			appStage.show();
				    		} catch (IOException e) {
				    			e.printStackTrace();
				    		}
						}
						else {
							occupied.setVisible(true);
						}
					}
				}
			}
			else {
				exists.setVisible(true);
			}
		}
	}
	
	@FXML
    void back(ActionEvent event) throws Exception {
    	try {
			Parent homePageParent = FXMLLoader.load(getClass().getResource("CustomerMain.fxml"));
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
