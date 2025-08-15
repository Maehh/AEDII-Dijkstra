package grafos.frontend;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.ui.fx_viewer.FxViewPanel;
import org.graphstream.ui.fx_viewer.FxViewer;


public class ExibirGrafo {
	
	private Graph grafo;
    private FxViewer viewer;
    private FxViewPanel viewPanel;

    // Constructor
    public ExibirGrafo(String nome) {
        System.setProperty("org.graphstream.ui", "javaFX"); // Configura para usar javaFX

        // Cria objeto grafo
        grafo = new MultiGraph(nome);

        // Carrega CSS
        String cssPath = ExibirGrafo.class.getResource("/style/style.css").toExternalForm();
        if (cssPath == null) {
            System.out.println("Stylepath não encontrado");
        } else {
            grafo.setAttribute("ui.stylesheet", "url('" + cssPath + "')");
        }

        // Cria Viewer Com javaFX
        viewer = new FxViewer(grafo, FxViewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);

        // Ativa layout automatico para ficar bonito
        viewer.enableAutoLayout();

        // Adiciona view normal
        setViewPanel((FxViewPanel) viewer.addDefaultView(false)); // false = não usa Swing
        
        System.out.println("Criação do Panel Concluido");
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
		
	}

	public FxViewPanel getViewPanel() {
		return viewPanel;
	}

	public void setViewPanel(FxViewPanel viewPanel) {
		this.viewPanel = viewPanel;
	}
	
	
	
}
