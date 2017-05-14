package com.planb.main;

import com.planb.controller.MainController;
import com.planb.support.networking.Config;
import com.planb.support.networking.HttpClientDefaultConfig;
import com.planb.user.UserInfo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	private Config config = new HttpClientDefaultConfig();
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		MainController.setPrimaryStage(primaryStage);
		
		config.setTargetAddress("http://127.0.0.1");
		config.setTargetPort(82);
		config.setReadTimeout(30000);
		
		Parent root = FXMLLoader.load(getClass().getResource("/com/planb/layout/Layout_main.fxml"));
		
		Scene scene = new Scene(root);
		// Set scene of root
		
		primaryStage.setScene(scene);
		// set scene to stage
		
		primaryStage.setTitle("페이스북 메신저 매크로");
//		primaryStage.getIcons().add(new Image("/com/watchover/layout/xxx.png"));
		// Set title and icon
		
		primaryStage.setOnCloseRequest(event -> {
			if(!UserInfo.isKeepLogin()) {
				// Keep login is disabled
				
			}
		});
		
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
