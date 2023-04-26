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

public class OfficerAddSpaceController {
	
	@FXML
	private TextField parkingSpaceNumberField;

	@FXML
	private Text exists;

	@FXML
	private Text empty;
	
	@FXML
    void officerAddSpace(ActionEvent event) throws Exception {
		exists.setVisible(false);
    	empty.setVisible(false);
    	boolean spaceExists = false;
    	String path = "C:\\Users\\Joseph\\Desktop\\test3.txt";
		ParkingLot lot = new ParkingLot();
		lot.load(path);
		String number = parkingSpaceNumberField.getText();
		if(number.isEmpty()) {
			empty.setVisible(true);
		}
		else {
			for(ParkingSpace p: lot.parkingLot){
				System.out.println(p.toString());
				if(number.equals(p.getSpaceNumber())) {
					spaceExists = true;
				}
			}
			if(spaceExists) {
				exists.setVisible(true);
			}
			else {
				ParkingSpace newSpace = new ParkingSpace(true,number);
				lot.parkingLot.add(newSpace);
				lot.update(path);
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.initStyle(StageStyle.UNDECORATED);
				alert.setContentText("Parking Space Number " + number + " has been added to the system.");
				alert.showAndWait();
				try {
	    			Parent homePageParent = FXMLLoader.load(getClass().getResource("OfficerManageParking.fxml"));
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
	
	@FXML
    void back(ActionEvent event) throws Exception {
    	try {
			Parent homePageParent = FXMLLoader.load(getClass().getResource("OfficerManageParking.fxml"));
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
