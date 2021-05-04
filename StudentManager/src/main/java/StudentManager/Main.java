package StudentManager;

import java.io.BufferedReader;
import java.io.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.*;

public class Main extends Application implements EventHandler<ActionEvent> {
	
	
	Button loadBtn;
	Button saveBtn;
	Label dataLabel;
	Map Students = new HashMap<String, Student>();
	
	
	public static void main(String[] args) {
		
		launch(args);
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Student Manager");
		loadBtn = new Button("Load");
		saveBtn = new Button("Save");
		dataLabel = new Label();
		
		VBox layout = new VBox(20);

		layout.getChildren().addAll(loadBtn, saveBtn, dataLabel);
		//layout.getChildren().add(saveBtn);
		
		loadBtn.setOnAction(this);
		saveBtn.setOnAction(this);
		
		Scene scene = new Scene(layout, 300, 250);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
		
		
//		Students.add(new Student("Jimmy", "Bones", 345));
//		Students.add(new Student("Jeff", "Valentine", 355));
		
	}

	@Override
	public void handle(ActionEvent event) {
		if(event.getSource() == loadBtn) {
			System.out.println("loading");
			Students = Data.load("C:/Users/nicol/git/StudentManager/StudentManager/test.txt");
			dataLabel.setText("Succesfully loaded " + Students.size() + " students");
		}
		if(event.getSource() == saveBtn) {
			System.out.println("saving");
			Data.save(Students, "C:/Users/nicol/git/StudentManager/StudentManager/test.txt");
			dataLabel.setText("Succesfully saved " + Students.size() + " students");
		}
		
		
	}
	
	
}
