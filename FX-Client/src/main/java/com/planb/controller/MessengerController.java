package com.planb.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.planb.table_model.FriendTableModel;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class MessengerController implements Initializable {
	@FXML
	private TextField friendField;
	
	@FXML
	private TableView<FriendTableModel> friendTable;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
}
