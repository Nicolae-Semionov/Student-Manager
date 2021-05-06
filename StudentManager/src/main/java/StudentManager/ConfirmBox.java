package StudentManager;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmBox {
	
	static boolean answer = false;
	static Stage window = new Stage();
	
	public static boolean display(String message) {
		
		window.initModality(Modality.APPLICATION_MODAL); // this line wont allow user to exit unless they pick an option
		window.setTitle("Student Manager");
		Label label = new Label(message);
		Button yesBtn = new Button("Yes");
		Button noBtn = new Button("No");
		
		yesBtn.setOnAction(e -> {answer = true; window.close();});
		noBtn.setOnAction(e -> {answer = false; window.close();});
		
		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, yesBtn, noBtn);
		layout.setAlignment(Pos.CENTER);
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait(); // window must close before returning to previous window
		
		return answer;
		
	}
}
