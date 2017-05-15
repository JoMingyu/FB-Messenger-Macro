package com.planb.controller;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import org.json.JSONArray;
import org.json.JSONObject;

import com.planb.support.networking.HttpClient;
import com.planb.support.networking.Response;
import com.planb.table_model.FriendTableModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class MessengerController implements Initializable {
	@FXML
	private TextField friendField;
	
	@FXML
	private TableView<FriendTableModel> friendTable;
	
	@FXML
	private Label selectedInfoLabel;
	
	private ObservableList<FriendTableModel> friendInfoList = FXCollections.observableArrayList();
	private HttpClient client = null;
	private long friendUid = 0;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		client = new HttpClient();
	}
	
	public void friendInfoTableMouseClicked(MouseEvent event) {
		if(event.getButton() == MouseButton.PRIMARY) {
			FriendTableModel friend = friendTable.getSelectionModel().getSelectedItem();
			this.friendUid = friend.getUid();
			selectedInfoLabel.setText("SELECTED : " + Long.toString(this.friendUid));
		}
	}
	
	public void friendNameFieldKeyPressed(KeyEvent event) {
		if(event.getCode() == KeyCode.ENTER) {
			// Enter in text field -> Login button event
			String friendName = friendField.getText();
			setTableData(friendName);
		}
	}
	
	private void setTableData(String friendName) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("friend_name", friendName);
		Response response = client.get("/friend", null, params);
		
		if(response.getResponseCode() == 200) {
			JSONArray friendInfoArray = new JSONArray(response.getResponseBody());
			for(int i = 0; i < friendInfoArray.length(); i++) {
				JSONObject friendInfo = friendInfoArray.getJSONObject(i);
				long uid = friendInfo.getLong("uid");
				String name = friendInfo.getString("name");
				String alternateName = friendInfo.getString("alternate_name");
				String isFriend = friendInfo.getBoolean("is_friend") == true ? "True" : "False";
				String gender = friendInfo.getInt("gender") == 1 ? "Female" : "Male";
				String timelineUri = friendInfo.getString("uri");
				friendInfoList.add(new FriendTableModel(uid, name, alternateName, isFriend, gender, timelineUri));
			}
		} else {
			friendInfoList.add(new FriendTableModel(0, "데이터 없음", "데이터 없음", "데이터 없음", "데이터 없음", "데이터 없음"));
		}
		
		TableColumn<?, ?> nameCol = friendTable.getColumns().get(0);
		nameCol.setCellValueFactory(new PropertyValueFactory("name"));

		TableColumn<?, ?> alternateNameCol = friendTable.getColumns().get(1);
		alternateNameCol.setCellValueFactory(new PropertyValueFactory("alternateName"));
		
		TableColumn<?, ?> typeCol = friendTable.getColumns().get(2);
		typeCol.setCellValueFactory(new PropertyValueFactory("isFriend"));
		
		TableColumn<?, ?> genderCol = friendTable.getColumns().get(3);
		genderCol.setCellValueFactory(new PropertyValueFactory("gender"));
		
		TableColumn<?, ?> timelineUriCol = friendTable.getColumns().get(4);
		timelineUriCol.setCellValueFactory(new PropertyValueFactory("timelineUri"));
		
		friendTable.setItems(friendInfoList);
	}
}
