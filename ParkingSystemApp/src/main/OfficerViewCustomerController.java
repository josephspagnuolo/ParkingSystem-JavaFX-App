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

public class OfficerViewCustomerController {
	
	@FXML
    private TextField emailField;

    @FXML
    private Text exists;

    @FXML
    private Text empty;
    
    @FXML
    void viewCustomer(ActionEvent event) throws Exception {
    	boolean exists1 = false;
    	String path = "C:\\Users\\Joseph\\Desktop\\test.txt";
		MaintainUser maintain = new MaintainUser();
		maintain.load(path);
		String email = emailField.getText();
		if(email.isEmpty()) {
			empty.setVisible(true);
		}
		for(User u: maintain.users){
			System.out.println(u.toString());
			if(email.equals(u.getEmail())) {
				exists1 = true;
				maintain.setCurrentUser(u);
				String path2 = "C:\\Users\\Joseph\\Desktop\\test4.txt";
				MaintainBooking bookings = new MaintainBooking();
				bookings.load(path2);
				int temp = 0;
				for(Booking b: bookings.bookings) {
					if(b.getEmail().equals(MaintainUser.currentUser.getEmail())) {
						temp++;
					}
				}
				if(temp == 0) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.initStyle(StageStyle.UNDECORATED);
					alert.setContentText("This user currently has no Bookings.");
					alert.showAndWait();
				}
				else {
					try {
						Parent homePageParent = FXMLLoader.load(getClass().getResource("OfficerViewDetails.fxml"));
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
		if(!exists1) {
			exists.setVisible(true);
		}
    	
    }
		
    @FXML
    void back(ActionEvent event) throws Exception {
    	try {
			Parent homePageParent = FXMLLoader.load(getClass().getResource("OfficerMain.fxml"));
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
