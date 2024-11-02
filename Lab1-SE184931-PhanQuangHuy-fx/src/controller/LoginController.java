package controller;

import java.io.IOException;

import fall.hsf301.slot02.pojo.Account;
import fall.hsf301.slot02.service.AccountService;
import fall.hsf301.slot02.service.IAccountService;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;


public class LoginController {
private IAccountService iAccountService;
	
	public LoginController(){
		iAccountService = new AccountService("JPAs");
	}
	@FXML
	private TextField txtUserName;
	
	@FXML
	private PasswordField txtPassword;
	
	@FXML public void loginOnAction() throws IOException {
		Stage win =(Stage)txtUserName.getScene().getWindow();
		win.close();
		String userName = txtUserName.getText();
		String passWord = txtPassword.getText();
		Account account =  iAccountService.findByUserName(userName);
		if((account != null)&& account.getPassWord().equals(passWord)) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../application/StudentManagementForm.fxml"));
			Parent root = loader.load();
			StudentManagementController smController = loader.getController();
			smController.setRole(account.getRole());	
			Scene scene = new Scene(root);		
			Stage primaryStage = new Stage();
			primaryStage.setScene(scene);
			primaryStage.show();
	
		}else {
			Alert alert = new Alert(AlertType.ERROR);
	        alert.setTitle("Warning");
	        alert.setContentText("Invalid username or password.");
			alert.show();
			
		}
	}
	@FXML public void cancelOnAction() throws IOException {
		Platform.exit();
	}
}
