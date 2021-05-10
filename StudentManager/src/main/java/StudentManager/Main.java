package StudentManager;

import java.io.BufferedReader;
import java.io.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
	Button deleteBtn;
	
	TextField fnameText;
	TextField lnameText;
	TextField numText;
	
	Label dataLabel;
	
	AlertBox alert = new AlertBox();
	ConfirmBox confirm = new ConfirmBox();
	
	TableView<Student> studentTable = new TableView<>();
	Map students = new HashMap<String, Student>();
	
	
	public static void main(String[] args) {
		
		launch(args);
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		alert.initialize();
		confirm.initialize();
		
		window = primaryStage;
		window.setTitle("Student Manager");
		
		// Buttons
		loadBtn = new Button("Load");
		saveBtn = new Button("Save");
		addBtn = new Button("Add");
		deleteBtn = new Button("Delete");
		
		
		// TextFields
		fnameText = new TextField();
		fnameText.setPromptText("first name");
		lnameText = new TextField();
		lnameText.setPromptText("last name");
		numText = new TextField();
		numText.setPromptText("student number");
		
		// Table 
		TableColumn<Student, String> firstNameColumn = new TableColumn("First Name");
		firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("first"));
		
		TableColumn<Student, String> lastNameColumn = new TableColumn("Last Name");
		lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("last"));
		
		TableColumn<Student, Integer> idColumn = new TableColumn("ID");
		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		
		studentTable.getColumns().addAll(firstNameColumn, lastNameColumn, idColumn);
		
		
		// Layout
		dataLabel = new Label();
		
		VBox layout = new VBox(20);
		layout.setMinSize(400, 200);

		layout.getChildren().addAll(loadBtn, saveBtn, dataLabel, addBtn, fnameText, lnameText, deleteBtn, studentTable);
		//layout.getChildren().add(saveBtn);
		
		loadBtn.setOnAction(this);
		saveBtn.setOnAction(this);
		addBtn.setOnAction(this);
		deleteBtn.setOnAction(this);
		
		Scene scene = new Scene(layout, 300, 250);
		window.setScene(scene);
		window.setHeight(600);
		window.show();
		
		window.setOnCloseRequest(e -> closeProgram());
		
		
	}

	private void closeProgram() {
		
		if(unsaved) {
			
			boolean save = confirm.display("You have unsaved changes, save before exiting?");
			
			if(save) {
				System.out.println("saving");
				Data.save(students, "C:/Users/nicol/git/StudentManager/StudentManager/test.txt");
			}
			
		}
		
		window.close();
	}

	@Override
	public void handle(ActionEvent event) {
		
		// LOAD
		if(event.getSource() == loadBtn) {  
			System.out.println("loading");
			
			students = Data.load("C:/Users/nicol/git/StudentManager/StudentManager/test.txt");
			
			
			dataLabel.setText("Succesfully loaded " + students.size() + " students");
			
			studentTable.getItems().clear();
			studentTable.setItems(getStudents());
			numAt = studentTable.getItems().get(studentTable.getItems().size() - 1).getId() + 1;
		}
		
		// SAVE
		if(event.getSource() == saveBtn) { 
			System.out.println("saving");
			
			Data.save(students, "C:/Users/nicol/git/StudentManager/StudentManager/test.txt");
			
			dataLabel.setText("Succesfully saved " + students.size() + " students");
			
			unsaved = false;
		}
		
		// ADD
		if(event.getSource() == addBtn) { 
			String f  = fnameText.getText();
			String l = lnameText.getText();
			
			if(!validName(f)) {
				AlertBox.display(f + " is not a valid first name");
			}
			else if(!validName(l)) {
				AlertBox.display(l + " is not a valid last name");
			}
			else if(l.isBlank() || f.isBlank()) {
				AlertBox.display("Missing Information");
			}
			else{
				Student s = new Student(f, l, numAt);
				
				boolean add = true;
				if(exists(students, s))
					add = confirm.display(s.getName() + " already exists, add anyway?");
				
				if(add) {
					numAt++;
					
					System.out.println("adding " + s);
					students.put(numAt, s);
					
					unsaved = true;
					
					dataLabel.setText("Succesfully added " + s);
					
					studentTable.getItems().add(s);
					
					fnameText.clear();
					lnameText.clear();
				}
				
			}
			
		}
		
		// REMOVE
		if(event.getSource() == deleteBtn) {
			
			Student selected = studentTable.getSelectionModel().getSelectedItem();
			System.out.println("deleting " + selected.getName());
			studentTable.getItems().remove(selected);
			students.remove("" + selected.getId());
			
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
	
	public boolean validName(String name) {

		for(int i = 0; i < name.length(); i++) {
			if(!Character.isLetter(name.charAt(i)))
				return false;
		}
			
		return true;
	}
	
	public boolean exists(Map<String, Student> Students, Student g) {
		
		for(Student s : Students.values()) {
			if(g.getName().equals(s.getName()))
				return true;
	    }
		
		return false;
	}
	
	public ObservableList<Student> getStudents() {
		ObservableList<Student> out = FXCollections.observableArrayList();
		out.addAll(students.values());
		return out;
		
	}
}
