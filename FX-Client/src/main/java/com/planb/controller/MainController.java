package com.planb.controller;

import java.net.URL;
import java.util.ResourceBundle;

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
	private Button login;
	
	@FXML
	private Label infoLabel;
	
	@FXML
	private CheckBox keepLoginBox;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	public void keyPressedOnField(KeyEvent key) {
		if(key.getCode() == KeyCode.ENTER) {
			// Enter -> Login button event
			loginButtonOnAction();	
		} else if(key.getCode() == KeyCode.TAB) {
			// Tab -> switch other text field
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
		
		if(id.isEmpty()) {
			infoLabel.setText("Insert ID");
			return;
		} else if(pw.isEmpty()) {
			infoLabel.setText("Insert Password");
			return;
		}
		
		infoLabel.setText("Waiting");
	}
}
