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

public class CustomerRegisterController {
	
    @FXML
    private PasswordField passwordField;

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
    void customerRegister(ActionEvent event) throws Exception {
    	
    	exists.setVisible(false);
    	empty.setVisible(false);
    	boolean emailExists = false;
    	
    	String path = "C:\\Users\\Joseph\\Desktop\\test.txt";
		MaintainUser maintain = new MaintainUser();
	
		maintain.load(path);
		
		String first = firstNameField.getText();
		String last = lastNameField.getText();
		String email = emailField.getText();
		String password = passwordField.getText();
		
		if(first.isEmpty() || last.isEmpty() || email.isEmpty() || password.isEmpty()) {
			empty.setVisible(true);
		}
		else {
			for(User u: maintain.users){
				System.out.println(u.toString());
				if(email.equals(u.getEmail())) {
					emailExists = true;
				}
			}
			if(emailExists) {
				exists.setVisible(true);
			}
			else {
				User newUser = new User(first,last,email,password);
				maintain.users.add(newUser);
				maintain.update(path);
				maintain.setCurrentUser(newUser);
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
    }
    
    @FXML
    void back(ActionEvent event) throws Exception {
    	try {
			Parent homePageParent = FXMLLoader.load(getClass().getResource("CustomerLogin.fxml"));
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
