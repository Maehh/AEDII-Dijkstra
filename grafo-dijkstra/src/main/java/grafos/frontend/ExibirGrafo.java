package grafos.frontend;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.ui.fx_viewer.FxViewPanel;
import org.graphstream.ui.fx_viewer.FxViewer;

import grafos.Grafo;


public class ExibirGrafo {
	
	private Graph grafo;
    private FxViewer viewer;
    private FxViewPanel viewPanel;

    // Constructor
    public ExibirGrafo(String nome) {
        System.setProperty("org.graphstream.ui", "javaFX"); // Configura para usar javaFX

        // Cria objeto grafo
        grafo = new MultiGraph(nome);
        
        this.carregaCSS();
        
        // Cria Viewer Com javaFX
        viewer = new FxViewer(grafo, FxViewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);

        // Ativa layout automatico para ficar bonito
        viewer.enableAutoLayout();

        // Adiciona view normal
        setViewPanel((FxViewPanel) viewer.addDefaultView(false)); // false = não usa Swing
        
        System.out.println("Cria Panel Concluido");
    }
	
	public void construirGrafo(Grafo g) {
		
		int numVertices = g.getNumVertices();
		int[][] matrizAdj = g.getMatrizAdj();
		
		if (numVertices == 0) 
			return;
		
		grafo.clear();
		this.carregaCSS();
		
		
		for (int i = 0; i < numVertices; i++) {
			grafo.addNode(""+ i).setAttribute("ui.label", ""+ i);
		}
		
		for (int i = 0; i < numVertices; i++) {
			for (int j = 0; j < numVertices; j++) {
				String idAresta = i + "-"+ j;
				
				if (matrizAdj[i][j] != -1) {
						Edge e = grafo.addEdge(idAresta, String.valueOf(i), String.valueOf(j), true);
						e.setAttribute("peso", matrizAdj[i][j]);
						e.setAttribute("ui.label", matrizAdj[i][j]);
						
				}
			}
		}
		
	}
	private void carregaCSS() {
		
		String cssPath = ExibirGrafo.class.getResource("/style/style.css").toExternalForm();
        if (cssPath == null) {
            System.out.println("Stylepath não encontrado");
        } else {
            grafo.setAttribute("ui.stylesheet", "url('" + cssPath + "')");
        }
		
	}
	public FxViewPanel getViewPanel() {
		return viewPanel;
	}

	public void setViewPanel(FxViewPanel viewPanel) {
		this.viewPanel = viewPanel;
	}
	
	public Graph getGraph() {
		return grafo;
	}
	
	
}
