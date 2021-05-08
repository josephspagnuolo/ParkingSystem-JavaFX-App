package main;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CustomerCancellationsController {
	
	@FXML
    private TextField idField;

    @FXML
    private Text exist;
	
	@FXML
    void customerCancelBooking(ActionEvent event) throws Exception {
		exist.setVisible(false);
		boolean idExists = false;
		String path = "C:\\Users\\Joseph\\Desktop\\test4.txt";
		MaintainBooking bookings = new MaintainBooking();
	
		bookings.load(path);
		String id = idField.getText();
		int temp = 0;
		int i = 0;
		for(Booking b: bookings.bookings){
			System.out.println(b.toString());
			if(id.equals(b.getBookingID())) {
				if(b.getEmail().equals(MaintainUser.currentUser.getEmail())) {
					idExists = true;
					temp = i;
				}
			}
			i++;
		}
		if(idExists) {
			String idRemoved = bookings.bookings.get(temp).getBookingID();
			ParkingLot lot = new ParkingLot();
			String path2 = "C:\\Users\\Joseph\\Desktop\\test3.txt";
			lot.load(path2);
			for(ParkingSpace p: lot.parkingLot){
				System.out.println(p.toString());
				if(bookings.bookings.get(temp).getParkingSpaceNumber().equals(p.getSpaceNumber())) {
					p.setVacant(true);
				}
			}
			bookings.bookings.remove(temp);
			bookings.update(path);
			lot.update(path2);
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.initStyle(StageStyle.UNDECORATED);
			alert.setContentText("Booking " + idRemoved + " has been cancelled.");
			alert.showAndWait();
			int temp2 = 0;
			for(Booking b: bookings.bookings) {
				if(b.getEmail().equals(MaintainUser.currentUser.getEmail())) {
					temp2++;
				}
			}
			if(temp2 > 0) {
				try {
	    			Parent homePageParent = FXMLLoader.load(getClass().getResource("CustomerViewBookings.fxml"));
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
		else {
			exist.setVisible(true);
		}
	}
	
	@FXML
    void back(ActionEvent event) throws Exception {
    	try {
			Parent homePageParent = FXMLLoader.load(getClass().getResource("CustomerViewBookings.fxml"));
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
