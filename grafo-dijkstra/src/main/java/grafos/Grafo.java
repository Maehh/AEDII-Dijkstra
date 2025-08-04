package grafos;

import java.util.ArrayList;

public class Grafo {

	private int[][] matrizAdjacentes;
	private int numVertices;

	public Grafo(int numVertices) {
		// Inicia todos os vérticies como -1

		this.numVertices = numVertices;
		matrizAdjacentes = new int[numVertices][numVertices];

		for (int i = 0; i < numVertices; i++)
			for (int j = 0; j < numVertices; j++) {
				matrizAdjacentes[i][j] = -1;
			}

	}

	public void insereAresta(int v1, int v2, int peso) {

		if (this.existeAresta(v1, v2) == false) {
			matrizAdjacentes[v1][v2] = peso;
		}

	}

	public boolean existeAresta(int v1, int v2) {

		if (matrizAdjacentes[v1][v2] != -1)
			return true;

		return false;
	}

	public ArrayList<Integer> verticiesAdj(int vertice) {

		ArrayList<Integer> vertices = new ArrayList<>();
		for (int u = 0; u < numVertices; u++) {

			if (this.existeAresta(vertice, u))
				vertices.add(u);
		}

		return vertices;

	}

	public void retiraAresta(int v1, int v2) {

		matrizAdjacentes[v1][v2] = -1;

	}

	public void liberaGrafo() {

		// Apaga todos os pesos do grafo
		for (int i = 0; i < numVertices; i++)
			for (int j = 0; j < numVertices; i++) {
				matrizAdjacentes[i][j] = -1;
			}

	}

	public int[][] getMatrizAdj() {

		return matrizAdjacentes;

	}

	public void grafoTransposto() {

		// TODO: Criar corpo do metodo

	}

	public void retiraMin() {

		// TODO: Criar corpo do metodo

	}

	public int getNumVertices() {
		return numVertices;
	}

}
