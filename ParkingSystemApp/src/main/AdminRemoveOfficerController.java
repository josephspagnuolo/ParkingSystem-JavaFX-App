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

public class AdminRemoveOfficerController {
	
	@FXML
    private TextField idField;

    @FXML
    private Text exist;
	
	@FXML
    void officerRemove(ActionEvent event) throws Exception {
		exist.setVisible(false);
		boolean idExists = false;
		String path = "C:\\Users\\Joseph\\Desktop\\test2.txt";
		MaintainUser maintain = new MaintainUser();
	
		maintain.load2(path);
		String id = idField.getText();
		int temp = 0;
		int i = 0;
		for(User o: maintain.officers){
			System.out.println(o.toString());
			if(id.equals(o.getPassword())) {
				idExists = true;
				temp = i;
			}
			i++;
		}
		if(idExists) {
			String idRemoved = maintain.officers.get(temp).getPassword();
			maintain.officers.remove(temp);
			maintain.update2(path);
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.initStyle(StageStyle.UNDECORATED);
			alert.setContentText("Officer " + idRemoved + " has been removed from the system.");
			alert.showAndWait();
			try {
    			Parent homePageParent = FXMLLoader.load(getClass().getResource("AdminManageOfficers.fxml"));
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
			exist.setVisible(true);
		}
	}
	
	@FXML
    void back(ActionEvent event) throws Exception {
    	try {
			Parent homePageParent = FXMLLoader.load(getClass().getResource("AdminManageOfficers.fxml"));
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
