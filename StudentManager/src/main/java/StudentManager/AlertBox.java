package StudentManager;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {

static Stage window = new Stage();
	
	static Label label = new Label();
	
	public static void initialize() {
		
		window.initModality(Modality.APPLICATION_MODAL); // this line wont allow user to exit unless they pick an option
		window.setTitle("Student Manager");
		
		Button button = new Button("Ok");
		
		
		button.setOnAction(e -> window.close());
		
		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, button);
		layout.setAlignment(Pos.CENTER);
		Scene scene = new Scene(layout);
		window.setScene(scene);
		
	}
	
	public static void display(String message) {
		label.setText(message);
		window.showAndWait(); // window must close before returning to previous window
	}
}
