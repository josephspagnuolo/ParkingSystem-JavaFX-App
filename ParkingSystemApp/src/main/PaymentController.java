package main;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PaymentController {
	
	@FXML
    private Text empty;

    @FXML
    private CheckBox box2;

    @FXML
    private CheckBox box1;

    @FXML
    private CheckBox box3;
    
    @FXML
    private Button checkout;

    @FXML
    private Text tot;
    
    @FXML
    private Text total;
    
    @FXML
    private TextField detailField2;

    @FXML
    private TextField detailField1;

    @FXML
    private TextField nameField;

    @FXML
    private Text empty1;

    @FXML
    private Text invalid;
    
    @FXML
    private ComboBox<String> combo;
    
    @FXML
    private PasswordField paypalPassword;
    
    @FXML
    private Button confirm;
    
    public int totalInt = 0;
    
    public boolean[] bool = new boolean[3];
    
    @FXML
    public void initialize() throws Exception {
    	combo.setVisible(false);
    	paypalPassword.setVisible(false);
    	confirm.setVisible(false);
    	nameField.setVisible(false);
		detailField1.setVisible(false);
		detailField2.setVisible(false);
		combo.getItems().add("PayPal");
		combo.getItems().add("Credit");
		combo.getItems().add("Debit");
    	total.setText("$ 0.00");
    	String path = "C:\\Users\\Joseph\\Desktop\\test4.txt";
		MaintainBooking bookings = new MaintainBooking();
		bookings.load(path);
		int temp = 0;
		String temp1 = "";
		String temp2 = "";
		String temp3 = "";
		for(Booking b: bookings.bookings){
			System.out.println(b.toString());
			if(b.getEmail().equals(MaintainUser.currentUser.getEmail())) {
				if(b.getPaymentStatus().equals("Unpaid")) {
					temp++;
					if(temp == 1) {
						temp1 = "Booking " + b.getBookingID();
					}
					else if(temp == 2) {
						temp2 = "Booking " + b.getBookingID();
					}
					else if(temp == 3) {
						temp3 = "Booking " + b.getBookingID();
					}
				}
			}
		}
		if(temp == 1) {
			box1.setText(temp1);
			box2.setVisible(false);
			box3.setVisible(false);
		}
		else if(temp == 2) {
			box1.setText(temp1);
			box2.setText(temp2);
			box3.setVisible(false);
		}
		else if(temp == 3) {
			box1.setText(temp1);
			box2.setText(temp2);
			box3.setText(temp3);
		}
    }
    
    @FXML
    void box1(ActionEvent event) throws Exception {
    	if(box1.isSelected()) {
    		totalInt+=10;
    		total.setText("$ " + totalInt + ".00");
    	}
    	else if(!box1.isSelected()) {
    		totalInt-=10;
    		total.setText("$ " + totalInt + ".00");
    	}
    }
    
    @FXML
    void box2(ActionEvent event) throws Exception {
    	if(box2.isSelected()) {
    		totalInt+=10;
    		total.setText("$ " + totalInt + ".00");
    	}
    	else if(!box2.isSelected()) {
    		totalInt-=10;
    		total.setText("$ " + totalInt + ".00");
    	}
    }
    
    @FXML
    void box3(ActionEvent event) throws Exception {
    	if(box3.isSelected()) {
    		totalInt+=10;
    		total.setText("$ " + totalInt + ".00");
    	}
    	else if(!box3.isSelected()) {
    		totalInt-=10;
    		total.setText("$ " + totalInt + ".00");
    	}
    }
    
    @FXML
    void checkout(ActionEvent event) throws Exception {
    	empty.setVisible(false);
    	if(!box1.isSelected() && !box2.isSelected() && !box3.isSelected()) {
    		empty.setVisible(true);
    	}
    	else {
    		bool[0] = box1.isSelected();
    		bool[1] = box2.isSelected();
    		bool[2] = box3.isSelected();
    		combo.setVisible(true);
        	confirm.setVisible(true);
    		box1.setVisible(false);
        	box2.setVisible(false);
        	box3.setVisible(false);
    		checkout.setVisible(false);
    		tot.setVisible(false);
    		total.setVisible(false);
        	empty.setVisible(false);
//    		try {
//    			Parent homePageParent = FXMLLoader.load(getClass().getResource("Checkout.fxml"));
//    			Scene homePageScene = new Scene(homePageParent);
//    			Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//    			appStage.setScene(homePageScene);
//    			appStage.centerOnScreen();
//    			appStage.show();
//    		} catch (IOException e) {
//    			e.printStackTrace();
//    		}
    	}
    }
    
    @FXML
    void confirm(ActionEvent event) throws Exception {
		invalid.setVisible(false);
    	empty1.setVisible(false);
    	String name = nameField.getText();
		String detail1 = detailField1.getText();
		String detail2 = detailField2.getText();
		String paypal = paypalPassword.getText();
    	if(name.isEmpty() || detail1.isEmpty() || (detail2.isEmpty() && detailField2.isVisible()) || (paypal.isEmpty() && paypalPassword.isVisible())) {
			empty1.setVisible(true);
		}
    	else if(!name.equals(MaintainUser.currentUser.getFirstName() + " " + MaintainUser.currentUser.getLastName())){
    		invalid.setVisible(true);
    	}
    	else {
    		String path = "C:\\Users\\Joseph\\Desktop\\test4.txt";
    		MaintainBooking bookings = new MaintainBooking();
    		bookings.load(path);
    		int temp4 = 0;
    		for(Booking b: bookings.bookings){
    			System.out.println(b.toString());
    			if(b.getEmail().equals(MaintainUser.currentUser.getEmail())) {
    				if(b.getPaymentStatus().equals("Unpaid")) {
    					if(bool[temp4]) {
    						b.setPaymentStatus("Verified");
    					}
    					temp4++;
    				}
    			}
    		}
    		bookings.update(path);
    		Alert alert = new Alert(AlertType.INFORMATION);
			alert.initStyle(StageStyle.UNDECORATED);
			alert.setContentText("Payment of $" + totalInt + ".00 has been verified and confirmed.");
			alert.showAndWait();
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
    
    public void selection() throws Exception {
		if("PayPal".equals(combo.getValue())) {
			nameField.setVisible(true);
			detailField1.setVisible(true);
			detailField2.setVisible(false);
			paypalPassword.setVisible(true);
			nameField.setText("");
			detailField1.setText("");
			detailField2.setText("");
			paypalPassword.setText("");
			nameField.setPromptText("PayPal Full Name");
			detailField1.setPromptText("PayPal Email");
		}
		else if("Credit".equals(combo.getValue())) {
			nameField.setVisible(true);
			detailField1.setVisible(true);
			detailField2.setVisible(true);
			paypalPassword.setVisible(false);
			nameField.setText("");
			detailField1.setText("");
			detailField2.setText("");
			paypalPassword.setText("");
			nameField.setPromptText("Full Name on Credit Card");
			detailField1.setPromptText("16-digit Credit Card Number");
			detailField2.setPromptText("CVV");
		}
		else if("Debit".equals(combo.getValue())) {
			nameField.setVisible(true);
			detailField1.setVisible(true);
			detailField2.setVisible(true);
			paypalPassword.setVisible(false);
			nameField.setText("");
			detailField1.setText("");
			detailField2.setText("");
			paypalPassword.setText("");
			nameField.setPromptText("Full Name on Debit Card");
			detailField1.setPromptText("16-digit Debit Card Number");
			detailField2.setPromptText("CVV");
		}
    }
    
    @FXML
    void back(ActionEvent event) throws Exception {
    	if(tot.isVisible()) {
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
    		combo.setVisible(false);
        	confirm.setVisible(false);
    		checkout.setVisible(true);
    		tot.setVisible(true);
    		total.setVisible(true);
    		box1.setVisible(true);
    		box2.setVisible(true);
			box3.setVisible(true);
        	nameField.setVisible(false);
    		detailField1.setVisible(false);
    		detailField2.setVisible(false);
        	total.setText("$ 0.00");
        	String path = "C:\\Users\\Joseph\\Desktop\\test4.txt";
    		MaintainBooking bookings = new MaintainBooking();
    		bookings.load(path);
    		int temp = 0;
    		String temp1 = "";
    		String temp2 = "";
    		String temp3 = "";
    		for(Booking b: bookings.bookings){
    			System.out.println(b.toString());
    			if(b.getEmail().equals(MaintainUser.currentUser.getEmail())) {
    				if(b.getPaymentStatus().equals("Unpaid")) {
    					temp++;
    					if(temp == 1) {
    						temp1 = "Booking " + b.getBookingID();
    					}
    					else if(temp == 2) {
    						temp2 = "Booking " + b.getBookingID();
    					}
    					else if(temp == 3) {
    						temp3 = "Booking " + b.getBookingID();
    					}
    				}
    			}
    		}
    		if(temp == 1) {
    			box1.setText(temp1);
    			box2.setVisible(false);
    			box3.setVisible(false);
    		}
    		else if(temp == 2) {
    			box1.setText(temp1);
    			box2.setText(temp2);
    			box3.setVisible(false);
    		}
    		else if(temp == 3) {
    			box1.setText(temp1);
    			box2.setText(temp2);
    			box3.setText(temp3);
    		}
    	}
    }

}
