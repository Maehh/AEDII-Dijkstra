package grafos;

import java.util.ArrayList;
import java.util.List;

public class Grafo {

	private int[][] matrizAdjacentes;
	private int numVertices;
	
	/* Construtor para armazenar grafo;
	 * Retorna uma matriz nXn, n sendo o numero de vertices inserido;
	 * Todos os vertices iniciam sem nenhuma relacao (valores -1).
	 * */
	
	public Grafo(int numVertices) {
		this.numVertices = numVertices;
		matrizAdjacentes = new int[numVertices][numVertices];

		for (int i = 0; i < numVertices; i++)
			for (int j = 0; j < numVertices; j++) {
				matrizAdjacentes[i][j] = -1;
			}
	}
	
	public void atualizaGrafo(int numVertices) {
		
		this.liberaGrafo();
		this.numVertices = numVertices;
		matrizAdjacentes = new int[numVertices][numVertices];
		
		for (int i = 0; i < numVertices; i++)
			for (int j = 0; j < numVertices; j++) {
				matrizAdjacentes[i][j] = -1;
			}
	}
	public int[][] getMatrizAdj() { return matrizAdjacentes; }
	public int getNumVertices() { return numVertices; }
	
	
	/* Insere arestas com base no numero dos verticies e seu peso  */
	public boolean insereAresta(int v1, int v2, int peso) {

		if (this.existeAresta(v1, v2) == 0) {
			matrizAdjacentes[v1][v2] = peso;
			return true;
		}
		
		return false;

	}
	
	public int existeAresta(int v1, int v2) {
		
		boolean teste = v1 < matrizAdjacentes.length && v2 < matrizAdjacentes[v1].length; 
		
		if (!teste) 
			return -1;
		
		if (matrizAdjacentes[v1][v2] != -1) {
			return 1;
			}
		return 0;
	}

	public List<Integer> verticiesAdj(int vertice) {

		List<Integer> vertices = new ArrayList<>();
		for (int u = 0; u < numVertices; u++) {

			if (this.existeAresta(vertice, u) == 1)
				vertices.add(u);
		}

		return vertices;

	}

	public boolean removeAresta(int v1, int v2) { 
		
		if (this.existeAresta(v1, v2) == 1) {
			matrizAdjacentes[v1][v2] = -1; 
			return true;
			}
		
		return false;
		}

	public void liberaGrafo() {

		for (int i = 0; i < numVertices; i++)
			for (int j = 0; j < numVertices; j++) {
				matrizAdjacentes[i][j] = -1;
			}

	}


}
