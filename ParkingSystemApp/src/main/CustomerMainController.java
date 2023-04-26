package main;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CustomerMainController {
	
	@FXML
    private Text welcome;
	
    @FXML
    private Button checkout;
	
	public Text name = new Text("hello");
	
	@FXML
    public void initialize() throws Exception {
		welcome.setText("Welcome, " + MaintainUser.currentUser.getFirstName() + "!");
		String path = "C:\\Users\\Joseph\\Desktop\\test4.txt";
		MaintainBooking bookings = new MaintainBooking();
		bookings.load(path);
		int temp = 0;
		for(Booking b: bookings.bookings) {
			if(b.getEmail().equals(MaintainUser.currentUser.getEmail())) {
				if(b.getPaymentStatus().equals("Unpaid")) {
					temp++;
				}
			}
		}
		if(temp > 0) {
			checkout.setVisible(true);
		}
		else {
			checkout.setVisible(false);
		}
	}
	
	@FXML
    void customerBookSpace(ActionEvent event) throws Exception {
		String path = "C:\\Users\\Joseph\\Desktop\\test4.txt";
		MaintainBooking bookings = new MaintainBooking();
		bookings.load(path);
		int temp = 0;
		for(Booking b: bookings.bookings) {
			if(b.getEmail().equals(MaintainUser.currentUser.getEmail())) {
				temp++;
			}
		}
		if(temp == 3) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.initStyle(StageStyle.UNDECORATED);
			alert.setContentText("You already have 3 Bookings.");
			alert.showAndWait();
		}
		else {
			try {
				Parent homePageParent = FXMLLoader.load(getClass().getResource("CustomerBookSpace.fxml"));
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
	
	@FXML
    public void buttoncolour() {
		checkout.setStyle("-fx-background-color: #339617; ");
	}
	
	@FXML
    public void buttoncolour2() {
		checkout.setStyle("-fx-background-color: #35c93f; ");
	}
	
	@FXML
    void customerViewBookings(ActionEvent event) throws Exception {
		String path = "C:\\Users\\Joseph\\Desktop\\test4.txt";
		MaintainBooking bookings = new MaintainBooking();
	
		bookings.load(path);
		int temp = 0;
		for(Booking b: bookings.bookings) {
			if(b.getEmail().equals(MaintainUser.currentUser.getEmail())) {
				temp++;
			}
		}
		if(temp < 1) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.initStyle(StageStyle.UNDECORATED);
			alert.setContentText("You have no Bookings.");
			alert.showAndWait();
		}
		else {
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
	
	@FXML
    void checkout(ActionEvent event) throws Exception {
		try {
			Parent homePageParent = FXMLLoader.load(getClass().getResource("Payment.fxml"));
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
