package grafos.userinterfaces;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainWindowFX extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		Button btn = new Button("Teste");
		
		btn.setOnAction(e ->  System.out.println("Hello World!"));
		
		
		StackPane root = new StackPane();
		
		root.getChildren().add(btn);
		
		
		Scene scene = new Scene(root, 300, 200);
		primaryStage.setTitle("Primeiro Painel");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		}
	
	
	
}
