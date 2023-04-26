package main;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class OfficerLoginController {
	
	@FXML
    private TextField officerIDField;

    @FXML
    private TextField officerEmailField;

    @FXML
    private Text invalid;
    
    @FXML
    void officerLogin(ActionEvent event) throws Exception {
		String path = "C:\\Users\\Joseph\\Desktop\\test2.txt";
		MaintainUser maintain = new MaintainUser();
		maintain.load2(path);
		String email = officerEmailField.getText();
		String id = officerIDField.getText();
		for(User o: maintain.officers){
			System.out.println(o.toString());
			if(email.equals(o.getEmail())) {
				if(id.equals(o.getPassword())) {
					//maintain.setCurrentUser(o);
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
				else {
					invalid.setVisible(true);
				}
			}
			else {
				invalid.setVisible(true);
			}
		}
	}
    
    @FXML
    void back(ActionEvent event) throws Exception {
    	try {
			Parent homePageParent = FXMLLoader.load(getClass().getResource("Opening.fxml"));
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
