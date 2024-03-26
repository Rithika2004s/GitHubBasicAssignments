package application;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class Controller1 implements Initializable{
	@FXML
	Button forwardButton, backButton, refreshButton, searchButton;
	@FXML
	TextField searchBox;
	@FXML
	WebView webView;
	@FXML
	MenuBar menu;
	@FXML
	ListView<String> historyView;
	public static WebEngine engine;
	String homePage = new String("www.google.com");
	public static WebHistory webHistory;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		engine = webView.getEngine();	
		searchBox.setText(homePage);
		loadPage();
	}
	public void loadPage() {
		engine.load("http://"+searchBox.getText());
		
	}
	public void reload() {
		loadPage();
	}
	
	public void goForward() {
		webHistory = engine.getHistory();
		try {
			webHistory.go(1);
		}
		catch(Exception e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Error");
			alert.setHeaderText("Last Page");
			alert.setContentText("You cannot move forward!");
			alert.showAndWait();
		}
	}
	public void goBack() {
		webHistory = engine.getHistory();
		try {
			webHistory.go(-1);
		}
		catch(Exception e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Error");
			alert.setHeaderText("First Page");
			alert.setContentText("You cannot move backward!");
			alert.showAndWait();
		}

	}
	public void zoomIn() {
		webView.setZoom(webView.getZoom()+0.5);
	}
	public void zoomOut() {
		webView.setZoom(webView.getZoom()-0.5);
	}
	public void resize() {
		webView.setZoom(1);
	}
	public void getHistory() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("History.fxml"));
		try {
			Parent root = loader.load();
			Scene scene = new Scene(root);
			loader.getController();
			Stage stage = (Stage)searchBox.getScene().getWindow();
			stage.setScene(scene);
			stage.show();
		} catch (IOException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
	}
}
