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

public class OfficerRemoveSpaceController {
	
	@FXML
    private TextField parkingSpaceNumberField;

    @FXML
    private Text exists;

    @FXML
    private Text empty;
    
    @FXML
    private Text occupied;
    
    @FXML
    private Text minimum;
	
	@FXML
    void officerRemoveSpace(ActionEvent event) throws Exception {
		exists.setVisible(false);
		empty.setVisible(false);
		minimum.setVisible(false);
		occupied.setVisible(false);
		boolean numberExists = false;
		String path = "C:\\Users\\Joseph\\Desktop\\test3.txt";
		ParkingLot lot = new ParkingLot();
		lot.load(path);
		String number = parkingSpaceNumberField.getText();
		int temp = 0;
		int i = 0;
		for(ParkingSpace p: lot.parkingLot){
			System.out.println(p.toString());
			if(number.equals(p.getSpaceNumber())) {
				numberExists = true;
				temp = i;
			}
			i++;
		}
		if(numberExists) {
			if(lot.parkingLot.size() == 1) {
				minimum.setVisible(true);
			}
			else if(!lot.parkingLot.get(temp).isVacant()) {
				occupied.setVisible(true);
			}
			else {
				String numberRemoved = lot.parkingLot.get(temp).getSpaceNumber();
				lot.parkingLot.remove(temp);
				lot.update(path);
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.initStyle(StageStyle.UNDECORATED);
				alert.setContentText("Parking Space Number " + numberRemoved + " has been removed from the system.");
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
		else {
			exists.setVisible(true);
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
