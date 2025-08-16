package grafos.frontend;

import grafos.Grafo;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class PainelVertical {
	
	public static VBox criaPainelVertical(Grafo grafo, ExibirGrafo exibir) {
		
		VBox cxVertical = new VBox(10);
		cxVertical.setStyle("-fx-padding: 20; -fx-background-color: #eeeeee;");
		
		/* Botoes e Labels */
		Label configLabel = new Label("Configurações");
		configLabel.setFont(new Font("Arial", 20));
		configLabel.setAlignment(Pos.TOP_CENTER);
		
		Label criaGrafoLabel = new Label("Novo Grafo === ------------------------------------");
		criaGrafoLabel.setFont(new Font("Arial", 15));
		criaGrafoLabel.setAlignment(Pos.TOP_CENTER);
		
		/* Cria grafos novos */
		Label nVerticesLabel = new Label("Numero de vertices: ");
		TextField txtNVertices = new TextField();
		Button criarGrafo = new Button("Novo Grafo");
		
		criarGrafo.setOnAction(e -> {
			int nVertices = Integer.parseInt(txtNVertices.getText());
			System.out.println(nVertices);
			grafo.atualizaGrafo(nVertices);
			exibir.run(grafo);
			
		});
		
		HBox criaGrafosFieldGroup = new HBox(5, nVerticesLabel, txtNVertices, criarGrafo);
		cxVertical.getChildren().addAll(configLabel, criaGrafoLabel, criaGrafosFieldGroup);
		
		return cxVertical;
	}
}
