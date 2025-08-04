package grafos.run;
import grafos.Grafo;
import grafos.frontend.ExibirGrafo;

public class Main {

	public static void main(String[] args) {
		
			
		Grafo grafo = new Grafo(4);
		ExibirGrafo exibir = new ExibirGrafo("Meu Grafo");
		
		int numVerticies = grafo.getNumVertices();
		int[][] matriz = grafo.getMatrizAdj();
		
		grafo.insereAresta(0, 1, 2);
		grafo.insereAresta(1, 2, 1);
		grafo.insereAresta(0, 2, 3);
		grafo.insereAresta(3, 1, 4);
		grafo.insereAresta(1, 1, 0);
		
		for (int i = 0; i < numVerticies; i++) {
			for (int j = 0; j < numVerticies; j++) {
				System.out.println(matriz[i][j]); }
			}
		
		
		exibir.run(numVerticies, matriz);
		
		
	}

}

