package grafos.algoritimos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

import org.graphstream.graph.Graph;

import grafos.Grafo;
import grafos.frontend.ExibirGrafo;

/**
 * Implementação do algoritmo de Dijkstra para grafos representados
 * por matriz de adjacência.
 * 
 * Objetivo: calcular o caminho mínimo de um vértice de origem
 * até todos os outros vértices do grafo.
 * 
 * Predecessores: É o vértice que vem imediatamente antes dele no caminho mínimo a partir da origem.
 * 
 */

public class Dijkstra {
	
    private Grafo grafoBack;     // Referência ao grafo backend (matriz de adjacência)
    private int[] dist;          // Vetor com as distâncias mínimas calculadas
    private int[] pai;           // Vetor de predecessores (para reconstruir caminhos)
    private boolean[] visitado;  // Marca os vértices já processados (conjunto S)
    
    
    private ExibirGrafo exibir;  // Classe responsavel por alterar front
    Graph grafoFront;			 // Grafo no front-end
    
    // Construtor recebe um objeto Grafo já existente
    public Dijkstra(Grafo grafoBack, ExibirGrafo exibir) {
        this.grafoBack = grafoBack;
        
        this.exibir = exibir;					// Carrega exibir para reconstrucao das aparencias
        this.grafoFront = exibir.getGraph();	// Busca objeto Graph para permitir alteracoes
    }

    /**
     * Executa o algoritmo de Dijkstra a partir de um vértice origem.
     * 
     * @param origem vértice inicial
     */
    
    public void executar(int origem) {
    	
    	new Thread(() -> {					// Importante: permite que o grafo atualize em tempo real
        int n = grafoBack.getNumVertices(); // número de vértices do grafo
        dist = new int[n];
        pai = new int[n];
        visitado = new boolean[n];
        
        exibir.construirGrafo(grafoBack);								// limpa todo o grafo no front antes de executar
        grafoFront.getNode(origem).setAttribute("ui.class", "source");	// Altera primeiro vertice de origem

        // INITIALIZE-SINGLE-SOURCE
        Arrays.fill(dist, Integer.MAX_VALUE); // todas as distâncias começam como "infinito"
        Arrays.fill(pai, -1);                 // nenhum vértice possui predecessor no início
        dist[origem] = 0;                     // distância da origem até ela mesma é 0

        // Fila de prioridade (min-heap) armazenando pares [distância, vértice]
        PriorityQueue<int[]> fila = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        fila.add(new int[]{0, origem}); // adiciona a origem na fila com distância 0

        // Enquanto houver vértices na fila de prioridade
        while (!fila.isEmpty()) {
            int[] atual = fila.poll(); // remove o vértice com menor distância
            int u = atual[1];    // pega o índice do vértice
            
            
            grafoFront.getNode(String.valueOf(u)).setAttribute("ui.class", "current");	// Vertice que está sendo verificado
            
            if (visitado[u]) continue; // se já foi processado, pula
            visitado[u] = true;        // marca como processado
            
            grafoFront.getNode(String.valueOf(u)).setAttribute("ui.class", "visited");					// Vertice que foi "visitado"
            	
            try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
            
            
            // Para cada vizinho v de u
            List<Integer> adj = grafoBack.verticiesAdj(u); // lista de adjacentes de u
            for (int v : adj) {
                int peso = grafoBack.getMatrizAdj()[u][v]; // peso da aresta (u, v)
                relaxa(u, v, peso, fila);                  // aplica o relaxamento
                try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
            }
        }
        
        
        // Adiciona o melhor caminho calculado
        for (int v = 0; v < dist.length; v++) {
            if (v == origem) continue; // não precisa marcar a origem
            int atual = v;
            while (pai[atual] != -1) {
                int u = pai[atual];
                // marca os nós
                grafoFront.getNode(String.valueOf(u)).setAttribute("ui.class", "path");
                grafoFront.getNode(String.valueOf(atual)).setAttribute("ui.class", "path");
                
                // marca a aresta
                String edgeId = u + "-" + atual;
                if (grafoFront.getEdge(edgeId) != null) {
                    grafoFront.getEdge(edgeId).setAttribute("ui.class", "path");
                } else {
                    // se o grafo for não-direcionado
                    edgeId = atual + "-" + u;
                    if (grafoFront.getEdge(edgeId) != null) {
                        grafoFront.getEdge(edgeId).setAttribute("ui.class", "path");
                    }
                }

                atual = u; // sobe para o predecessor
            }
        }
        
        }).start();
    }

    /**
     * Função RELAX do algoritmo de Dijkstra.
     * 
     * Verifica se o caminho passando por u até v é menor do que
     * o valor armazenado atualmente em dist[v].
     * Se sim, atualiza dist[v] e define pai[v] = u.
     * 
     * @param u vértice atual
     * @param v vértice vizinho
     * @param peso peso da aresta (u, v)
     * @param fila fila de prioridade usada pelo algoritmo
     */
    
    private void relaxa(int u, int v, int peso, PriorityQueue<int[]> fila) {
        // Só relaxa se existir caminho para u (dist[u] != infinito)
        // e se a nova distância for melhor que a atual de v
        if (dist[u] != Integer.MAX_VALUE && dist[v] > dist[u] + peso) {
            dist[v] = dist[u] + peso;   // atualiza a distância mínima para v
            pai[v] = u;                 // define u como predecessor de v
            fila.add(new int[]{dist[v], v}); // insere v na fila com sua nova distância
            
            grafoFront.getNode("" + v).setAttribute("ui.label", v + "(" + dist[v] + ")");

            
            String edgeId = u + "-" + v;
            if (grafoFront.getEdge(edgeId) != null) {
                grafoFront.getEdge(edgeId).setAttribute("ui.class", "relaxed");			// Aresta "Relaxada"
            }
        }
    }

    /**
     * Retorna o vetor de distâncias mínimas da origem
     * até cada vértice do grafo.
     * 
     * @return array de distâncias
     */
    
    public int[] getDistancias() {
        return dist;
    }

    /**
     * Retorna o vetor de predecessores (pai[v] é o
     * vértice anterior no caminho mínimo da origem até v).
     * 
     * @return array de predecessores
     */
    
    public int[] getPais() {
        return pai;
    }

    /**
     * Reconstrói o caminho mínimo da origem até um vértice destino.
     * Usa o vetor de predecessores (pai).
     * 
     * @param destino vértice final
     * @return lista com os vértices no caminho (na ordem correta)
     */
    
    public List<Integer> getCaminho(int destino) {
        List<Integer> caminho = new ArrayList<>();
        for (int v = destino; v != -1; v = pai[v]) {
            caminho.add(0, v); // insere no início da lista (reverso do percurso)
        }
        return caminho;
    }
}

