package com.planb.controller;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import com.planb.support.networking.HttpClient;
import com.planb.support.networking.HttpClientConfig;
import com.planb.support.networking.Response;
import com.planb.user.UserInfo;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class MainController implements Initializable {
	@FXML
	private PasswordField pwField;
	
	@FXML
	private TextField idField;
	
	@FXML
	private Button loginButton;
	
	@FXML
	private Label infoLabel;
	
	@FXML
	private CheckBox keepLoginBox;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Platform.runLater(new Runnable () {
			@Override
			public void run() {
				idField.requestFocus();
			}
		});
	}
	
	public void keyPressedOnField(KeyEvent key) {
		if(key.getCode() == KeyCode.ENTER) {
			if(!keepLoginBox.isFocused() || loginButton.isFocused()) {
				// Enter in text field -> Login button event
				loginButtonOnAction();	
			} else {
				// Enter in check box -> switch selected
				keepLoginBox.setSelected(!keepLoginBox.isSelected());
			}
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
						keepLoginBox.requestFocus();
					}
				});
			} else if(keepLoginBox.isFocused()) {
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
		boolean keepLogin = keepLoginBox.isSelected();
		UserInfo.setKeepLogin(keepLogin);
		
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
				HttpClientConfig config = new HttpClientConfig();
				config.setTargetAddress("http://127.0.0.1");
				config.setTargetPort(82);
				config.setReadTimeout(30000);
				
				HttpClient client = new HttpClient(config);
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("id", id);
				params.put("pw", pw);
				Response response = client.post("/login", null, params);
				
				if(response.getResponseCode() == 201) {
					infoLabel.setText("success");
				} else {
					infoLabel.setText("failed");
				}
			}
		});
	}
}
