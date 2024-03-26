package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.web.WebHistory;
import javafx.stage.Stage;

public class Controller2 implements Initializable{
	@FXML
	ListView<String> historyList;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Controller1.webHistory = Controller1.engine.getHistory();
		ObservableList<WebHistory.Entry> list = Controller1.webHistory.getEntries();
		List<String>urlsList = new ArrayList<>();
		for(WebHistory.Entry i :list) {
			urlsList.add(i.getUrl());
		}
		historyList.getItems().addAll(urlsList);	
	}
	public void goToBrowser(){
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Browser.fxml"));
		try {
			Parent root = loader.load();
			Scene scene = new Scene(root);
			Stage stage = (Stage)historyList.getScene().getWindow();
			stage.setScene(scene);
			stage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
