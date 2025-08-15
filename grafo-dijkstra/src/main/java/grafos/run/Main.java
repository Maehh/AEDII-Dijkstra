package grafos.run;
import grafos.Grafo;
import grafos.frontend.ExibirGrafo;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {

		Grafo grafo = new Grafo(4);
		int numVerticies = grafo.getNumVertices();
		int[][] matriz = grafo.getMatrizAdj();
		
		
		grafo.insereAresta(0, 1, 2);
		grafo.insereAresta(1, 2, 1);
		grafo.insereAresta(0, 2, 3);
		grafo.insereAresta(3, 1, 4);
		grafo.insereAresta(1, 1, 0);
		
		ExibirGrafo exibir = new ExibirGrafo("Meu Grafo");		
		exibir.run(numVerticies, matriz);
		
		StackPane root = new StackPane();
		root.getChildren().add(exibir.getViewPanel());
		
		Scene scene = new Scene(root, 800, 600);
		primaryStage.setTitle("Graphsteam + javaFX");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		primaryStage.setOnCloseRequest(event -> {
			Platform.exit();							// Garante que o projeto não fica aberto após fechar janela
			System.exit(0);
		});
	}
	public static void main(String[] args) {
		
		launch(args);
		
		
		
	}


}

