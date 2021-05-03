package StudentManager;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.io.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application{
	
	public static void main(String[] args) {
		
		launch(args);
		


	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Student Manager");
		
		StackPane layout = new StackPane();
		
		Scene scene = new Scene(layout, 300, 250);
		
		primaryStage.setScene(scene);
		
		primaryStage.show();
		
		
		ArrayList Students = Data.load("C:/Users/nicol/git/StudentManager/StudentManager/test.txt");
		
		for(int i = 0; i < Students.size(); i++) {
			System.out.println(Students.get(i));
		}
		
		Students.add(new Student("Jimmy", "Jones", 345));
		Students.add(new Student("Jeff", "Valantine", 355));
		
		Data.save(Students, "C:/Users/nicol/git/StudentManager/StudentManager/test.txt");
	}
}
