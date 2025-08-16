package grafos.frontend;

import java.util.function.UnaryOperator;

import grafos.Grafo;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class PainelVertical {
	
	public static VBox criaPainelVertical(Grafo grafo, ExibirGrafo exibir) {
		
		VBox cxVertical = new VBox(10);
		cxVertical.setStyle("-fx-padding: 20; -fx-background-color: #eeeeee;");
		
		
		/* Operador Unário, para filtragem de dados de usuario */
		UnaryOperator<Change> filtro = Change -> {
			String text = Change.getText();
			if (text.matches("[0-9]*"))
				return Change;
			return null;
		};
		
		/* Botoes e Labels */
		Label configLabel = new Label("Configurações");
		configLabel.setFont(new Font("Arial", 20));
		configLabel.setAlignment(Pos.TOP_CENTER);
		
		Separator sep1 = new Separator();
		Separator sep2 = new Separator();
		Separator sep3 = new Separator();
		
		Label criaGrafoLabel = new Label("Novo Grafo");
		criaGrafoLabel.setFont(new Font("Arial", 18));
		criaGrafoLabel.setAlignment(Pos.TOP_CENTER);
		
		/* Cria grafos novos */
		Label nVerticesLabel = new Label("Nº de vertices: ");
		TextField txtNVertices = new TextField();
		txtNVertices.setTextFormatter(new TextFormatter<String>(filtro));
		
		/* Botao para adicionar grafos */
		Button criarGrafo = new Button("Novo Grafo");		
		criarGrafo.setOnAction(e -> {

			if (txtNVertices.getText().isEmpty()) {
				Alert alerta = new Alert(Alert.AlertType.ERROR);
				alerta.setHeaderText("Erro!");
				alerta.setContentText("Campos vazios!");
				alerta.showAndWait();
				return;
			}
			int nVertices = Integer.parseInt(txtNVertices.getText());
			System.out.println(nVertices);
			grafo.atualizaGrafo(nVertices);
			exibir.run(grafo);
		});
		
		VBox criaGrafosFieldGroup = new VBox(5, nVerticesLabel, txtNVertices, criarGrafo);
		
		/* Adiciona Arestas */
		
		Label addArestasLabel = new Label("Adiconar Arestas");
		addArestasLabel.setFont(new Font("Arial", 18));
		addArestasLabel.setAlignment(Pos.TOP_CENTER);
		
		Label vertice1Label = new Label("Vertice Nº1: ");
		TextField txtVertice1 = new TextField();
		txtVertice1.setTextFormatter(new TextFormatter<String>(filtro));
		
		Label vertice2Label = new Label("Vertice Nº2: ");
		TextField txtVertice2 = new TextField();
		txtVertice2.setTextFormatter(new TextFormatter<String>(filtro));
		
		Label pesoLabel = new Label("Valor do peso: ");
		TextField txtPeso = new TextField();
		txtPeso.setTextFormatter(new TextFormatter<String>(filtro));
		
		Button adicionaAresta = new Button("Criar Aresta");
		adicionaAresta.setOnAction(e -> {
			Alert alerta = new Alert(Alert.AlertType.ERROR);
			if (txtVertice1.getText().isEmpty()) {
				alerta.setHeaderText("Erro!");
				alerta.setContentText("Campos vazios!");
				alerta.showAndWait();
				return;
			}
			
			int v1 = Integer.parseInt(txtVertice1.getText());
			int v2 = Integer.parseInt(txtVertice2.getText());
			int peso = Integer.parseInt(txtPeso.getText());
			boolean result = grafo.insereAresta(v1, v2, peso);
			
			if (!result) {
				alerta.setHeaderText("Erro!");
				alerta.setContentText("Vertices " + v1 + " e " + v2 +" não existem OU já possuem relação. Favor inserir vertices válidas");
				alerta.showAndWait();
				return;
			}
			
			exibir.run(grafo);
		});
		Button removeAresta = new Button("Remover Aresta");
		removeAresta.setOnAction(e -> {
			Alert alerta = new Alert(Alert.AlertType.ERROR);
			
			if (txtVertice1.getText().isEmpty()) {
				alerta.setHeaderText("Erro!");
				alerta.setContentText("Campos vazios!");
				alerta.showAndWait();
				return;
			}
			
			int v1 = Integer.parseInt(txtVertice1.getText());
			int v2 = Integer.parseInt(txtVertice2.getText());
			int result = grafo.existeAresta(v1, v2);
			
			if (result == -1 || result == 0) {
				alerta.setHeaderText("Erro!");
				alerta.setContentText("Relação " + v1 + " e " + v2 +" não existem, favor inserir vertices válidas.");
				alerta.showAndWait();
				return;
			}
			grafo.removeAresta(v1, v2);
			exibir.run(grafo);
		});
		
		HBox criaArestasButtons = new HBox(5, adicionaAresta, removeAresta);
		VBox criaArestasFieldGroup = new VBox(2, vertice1Label, txtVertice1, vertice2Label, txtVertice2, pesoLabel, txtPeso, criaArestasButtons);
		
		cxVertical.getChildren().addAll(configLabel, sep1, criaGrafoLabel,  criaGrafosFieldGroup);
		cxVertical.getChildren().addAll(sep2, addArestasLabel, criaArestasFieldGroup);
		
		return cxVertical;
	}
}
