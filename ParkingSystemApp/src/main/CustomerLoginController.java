package main;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CustomerLoginController {

	@FXML
	private PasswordField passwordField;

	@FXML
	private TextField emailField;
	
    @FXML
    private Text invalid;
    
    
	
	@FXML
    void customerRegister(ActionEvent event) {
		
		try {
			Parent homePageParent = FXMLLoader.load(getClass().getResource("CustomerRegister.fxml"));
			Scene homePageScene = new Scene(homePageParent);
			Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			appStage.setScene(homePageScene);
			appStage.centerOnScreen();
			appStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

    }
	
	@FXML
    void customerLogin(ActionEvent event) throws Exception {
		
		String path = "C:\\Users\\Joseph\\Desktop\\test.txt";
		MaintainUser maintain = new MaintainUser();
	
		maintain.load(path);
		String email = emailField.getText();
		String password = passwordField.getText();
		if(email.isEmpty() || password.isEmpty()) {
			invalid.setVisible(true);
		}
		else {
			for(User u: maintain.users){
				System.out.println(u.toString());
				if(email.equals(u.getEmail())) {
					if(password.equals(u.getPassword())) {
						maintain.setCurrentUser(u);
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
						invalid.setVisible(true);
					}
				}
				else {
					invalid.setVisible(true);
				}
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
