package com.planb.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/com/planb/layout/layout_main.fxml"));
		
		Scene scene = new Scene(root);
		// root에 대해 scene을 생성
		
		primaryStage.setScene(scene);
		// stage에 scene 설정
		
		primaryStage.setTitle("페이스북 메신저 매크로");
//		primaryStage.getIcons().add(new Image("/com/watchover/layout/xxx.png"));
		// 타이틀과 아이콘 설정
		
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
