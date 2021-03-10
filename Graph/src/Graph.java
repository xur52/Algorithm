import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;

public class Graph {
    private ArrayList<String> vertexList;
    private int[][] edges;
    private int numOfEdges;
    private boolean[] isVisted;

    public static void main(String[] args) {
        int n = 5;
        String[] VertexValue = {"A","B","C","D","E"};
        Graph graph = new Graph(n);
        for (String vertex: VertexValue){
            graph.insertVertex(vertex);
        }
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(1, 4, 1);

        graph.showGraph();
        graph.dfs();


    }

    public Graph(int n){
        this.edges = new int[n][n];
        this.numOfEdges = 0;
        vertexList = new ArrayList<String>(n);
        isVisted = new boolean[n];
    }

    public int getFirstNeighbour(int index){
        for (int j = 0; j < vertexList.size(); j++){
            if (edges[index][j] > 0){
                return j;
            }
        }
        return -1;
    }
    public int getNextNeighbour(int v1, int v2){
        for (int j = v2+1; j<vertexList.size(); j++){
            if (edges[v1][j] > 0){
                return j;
            }
        }
        return -1;
    }
    private void dfs(int i){
        System.out.print(getValueByIndex(i) + "->");
        isVisted[i] = true;

        int w = getFirstNeighbour(i);
        while (w != -1){
            if (!isVisted[w]){
                dfs(w);
            }
            w = getNextNeighbour(i, w);
        }

    }
    public void dfs(){
        for (int i = 0; i < getNumOfVertex(); i++){
            if (!isVisted[i]){
                dfs(i);
                System.out.println();
            }
        }

    }

    private void bfs(int i){
        int u;
        int w;


    }




    public int getNumOfEdges(){
        return numOfEdges;
    }

    public int getNumOfVertex(){
        return vertexList.size();
    }

    public String getValueByIndex(int i){
        return vertexList.get(i);
    }

    public int getWeight(int v1, int v2){
        return edges[v1][v2];
    }

    public void showGraph(){
        for (int[] link : edges){
            System.err.println(Arrays.toString(link));
        }
    }

    public void insertVertex(String vertex){
        vertexList.add(vertex);
    }

    public void insertEdge(int v1, int v2, int weight){
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges ++;
    }
}
