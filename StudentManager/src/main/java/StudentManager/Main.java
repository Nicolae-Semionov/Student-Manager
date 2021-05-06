package StudentManager;

import java.io.BufferedReader;
import java.io.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.*;

public class Main extends Application implements EventHandler<ActionEvent> {
	
	boolean unsaved = false;
	int numAt = 0;
	
	Stage window;
	
	Button loadBtn;
	Button saveBtn;
	Button addBtn;
	
	TextField fnameText;
	TextField lnameText;
	TextField numText;
	
	Label dataLabel;
	
	ListView<Student> studentList = new ListView<>();
	Map Students = new HashMap<String, Student>();
	
	
	public static void main(String[] args) {
		
		launch(args);
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		window.setTitle("Student Manager");
		
		// Buttons
		loadBtn = new Button("Load");
		saveBtn = new Button("Save");
		addBtn = new Button("Add");
		
		
		// TextFields
		fnameText = new TextField();
		fnameText.setPromptText("first name");
		lnameText = new TextField();
		lnameText.setPromptText("last name");
		numText = new TextField();
		numText.setPromptText("student number");
		
		dataLabel = new Label();
		
		VBox layout = new VBox(20);
		layout.setMinSize(400, 200);

		layout.getChildren().addAll(loadBtn, saveBtn, dataLabel, addBtn, fnameText, lnameText, studentList);
		//layout.getChildren().add(saveBtn);
		
		loadBtn.setOnAction(this);
		saveBtn.setOnAction(this);
		addBtn.setOnAction(this);
		
		Scene scene = new Scene(layout, 300, 250);
		window.setScene(scene);
		window.setHeight(600);
		window.show();
		
		window.setOnCloseRequest(e -> closeProgram());
		
		
//		Students.add(new Student("Jimmy", "Bones", 345));
//		Students.add(new Student("Jeff", "Valentine", 355));
		
	}

	private void closeProgram() {
		
		if(unsaved) {
			
			boolean save = ConfirmBox.display("You have unsaved changes, save before exiting?");
			
			if(save) {
				System.out.println("saving");
				Data.save(Students, "C:/Users/nicol/git/StudentManager/StudentManager/test.txt");
			}
			
		}
		
		window.close();
	}

	@Override
	public void handle(ActionEvent event) {
		if(event.getSource() == loadBtn) {
			System.out.println("loading");
			
			Students = Data.load("C:/Users/nicol/git/StudentManager/StudentManager/test.txt");
			numAt = Students.size();
			
			dataLabel.setText("Succesfully loaded " + Students.size() + " students");
			
			studentList.getItems().addAll(Students.values());
		}
		if(event.getSource() == saveBtn) {
			System.out.println("saving");
			
			Data.save(Students, "C:/Users/nicol/git/StudentManager/StudentManager/test.txt");
			
			dataLabel.setText("Succesfully saved " + Students.size() + " students");
			
			unsaved = false;
		}
		if(event.getSource() == addBtn) {
			String f = fnameText.getText();
			String l = lnameText.getText();
			
			Student s = new Student(f, l, numAt);
			numAt++;
			
			System.out.println("adding " + s);
			Students.put(numAt, s);
			
			unsaved = true;
			
			dataLabel.setText("Succesfully added " + s);
			
			studentList.getItems().add(s);
		}
		
	}
	
	public boolean isInt(String a) {
		
		boolean is = true;
		
		try {
			int b = Integer.parseInt(a);
		}
		catch(NumberFormatException e) {
			System.out.println(a + " is not an integer");
			is = false;
		}
		
		return is;
	}
	
	
}
