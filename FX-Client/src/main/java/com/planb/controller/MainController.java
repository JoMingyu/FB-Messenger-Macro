package com.planb.controller;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import com.planb.support.networking.HttpClient;
import com.planb.support.networking.Response;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class MainController implements Initializable {
	@FXML
	private PasswordField pwField;
	
	@FXML
	private TextField idField;
	
	@FXML
	private Button loginButton;
	
	@FXML
	private Label infoLabel;
	
	private HttpClient client = null;
	private static Stage primaryStage = null;
	
	public static void setPrimaryStage(Stage primaryStage) {
		MainController.primaryStage = primaryStage;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Platform.runLater(new Runnable () {
			@Override
			public void run() {
				idField.requestFocus();
			}
		});
		
		client = new HttpClient();
		
		Response response = client.get("/account", null, null);
		if(response.getResponseCode() == 200) {
			// If session is already exist
			changeScene();
		}
	}
	
	public void keyPressedOnField(KeyEvent key) {
		if(key.getCode() == KeyCode.ENTER) {
			// Enter in text field -> Login button event
			loginButtonOnAction();	
		} else if(key.getCode() == KeyCode.TAB) {
			// Tab -> switch other view
			if(idField.isFocused()) {
				Platform.runLater(new Runnable () {
					@Override
					public void run() {
						pwField.requestFocus();
					}
				});
			} else if(pwField.isFocused()) {
				Platform.runLater(new Runnable () {
					@Override
					public void run() {
						loginButton.requestFocus();
					}
				});
			} else if(loginButton.isFocused()) {
				Platform.runLater(new Runnable () {
					@Override
					public void run() {
						idField.requestFocus();
					}
				});
			}
		}
	}
	
	public void loginButtonOnAction() {
		String id = idField.getText();
		String pw = pwField.getText();
		
		if(id.isEmpty()) {
			infoLabel.setText("Insert ID");
			return;
		} else if(pw.isEmpty()) {
			infoLabel.setText("Insert Password");
			return;
		}
		
		infoLabel.setText("Waiting");
		
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("id", id);
				params.put("pw", pw);
				Response response = client.post("/account", null, params);
				
				if(response.getResponseCode() == 201) {
					infoLabel.setText("Login Success. Wait for next screen.");
					try {
						Thread.sleep(1000);
						changeScene();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} else {
					infoLabel.setText("failed");
				}
			}
		});
	}
	
	public void changeScene() {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/com/planb/layout/Layout_messages.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
