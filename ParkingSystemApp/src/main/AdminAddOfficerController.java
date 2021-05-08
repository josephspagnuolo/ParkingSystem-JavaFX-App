package main;

import java.io.IOException;
import java.util.UUID;

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

public class AdminAddOfficerController {
	
	@FXML
    private TextField emailField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField firstNameField;

    @FXML
    private Text empty;

    @FXML
    private Text exists;
	
	@FXML
    void officerRegister(ActionEvent event) throws Exception {
		exists.setVisible(false);
    	empty.setVisible(false);
    	boolean emailExists = false;
    	
    	String path = "C:\\Users\\Joseph\\Desktop\\test2.txt";
		MaintainUser maintain = new MaintainUser();
	
		maintain.load2(path);
		String first = firstNameField.getText();
		String last = lastNameField.getText();
		String email = emailField.getText();
		if(first.isEmpty() || last.isEmpty() || email.isEmpty()) {
			empty.setVisible(true);
		}
		else {
			for(User o: maintain.officers){
				System.out.println(o.toString());
				if(email.equals(o.getEmail())) {
					emailExists = true;
				}
			}
			if(emailExists) {
				exists.setVisible(true);
			}
			else {
				UniqueID id = new UniqueID();
				if(id.getID() == 1000000) {
					if(maintain.officers.isEmpty()) {
						
					}
					else {
						String y = maintain.officers.get(maintain.officers.size() - 1).getPassword();
						int temp = Integer.parseInt(y);
						for(int i = 0; i < (temp - 1000000) + 1; i++){
							id.incID();
						}
					}
					
				}
				User newOfficer = new User(first,last,email,"" + UniqueID.id);
				id.incID();
				maintain.officers.add(newOfficer);
				maintain.update2(path);
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.initStyle(StageStyle.UNDECORATED);
				alert.setContentText("Officer " + (id.getID() - 1) + " has been added to the system.");
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
