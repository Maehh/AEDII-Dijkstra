package grafos.frontend;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;

public class ExibirGrafo {
	
	private Graph grafo;
	
	public ExibirGrafo(String nome) {
		System.setProperty("org.graphstream.ui", "swing");
		
		
		grafo = new MultiGraph(nome);
		String cssPath = ExibirGrafo.class.getResource("/style/style.css").toExternalForm();
		
		if (cssPath == null)
			System.out.println("Stylepath não encontrado");
		
		grafo.setAttribute("ui.stylesheet", "url('"+ cssPath + "')");
	}
	
	public void run(int numVerticies, int[][] matrizAdj) {
	
		for (int i = 0; i < numVerticies; i++) {
			grafo.addNode(""+ i).setAttribute("ui.label", ""+ i);
		}
		
		for (int i = 0; i < numVerticies; i++) {
			for (int j = 0; j < numVerticies; j++) {
				String idAresta = i + "-"+ j;
				System.out.println("Aresta: " + idAresta);
				
				if (matrizAdj[i][j] != -1) {
						System.out.println("Aceito.");
						Edge e = grafo.addEdge(idAresta, String.valueOf(i), String.valueOf(j), true);
						e.setAttribute("peso", matrizAdj[i][j]);
						e.setAttribute("ui.label", matrizAdj[i][j]);
						
				}
			}
		}
		
		for (Edge e : grafo.getEachEdge()) {
			if(e.getSourceNode().equals(e.getTargetNode())) {
				Node no = e.getSourceNode();
				no.setAttribute("ui.class", "auto");
				no.setAttribute("ui.label", no.getId() + "🔁");
				no.setAttribute("autoLopp", true);
			}
		}
		
		grafo.display();
	}
	
	
	
}
