package com.planb.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
	private CheckBox keepLogin;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	public void keyPressedOnField() {
		
	}
	
	public void loginButtonOnAction() {
		
	}
}
