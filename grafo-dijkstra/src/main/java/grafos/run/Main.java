package grafos.run;
import grafos.Grafo;
import grafos.frontend.ExibirGrafo;
import grafos.frontend.PainelVertical;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {

		Grafo grafo = new Grafo(0);

		ExibirGrafo exibir = new ExibirGrafo("Meu Grafo");		
		exibir.construirGrafo(grafo);
		
		
		// Parte Central do grafo
		BorderPane grafoView = new BorderPane();
		grafoView.setCenter(exibir.getViewPanel());
		
		/* Carrega o painel vertical a direita do programa. */
		VBox cxVertical = PainelVertical.criaPainelVertical(grafo, exibir);
		grafoView.setRight(cxVertical);
		
		/* Cria uma nova cena */
		Scene scene = new Scene(grafoView, 800, 600);
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

