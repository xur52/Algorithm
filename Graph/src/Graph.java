import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;


public class Graph {
    private ArrayList<String> vertexList;
    private int[][] edges;
    private int numOfEdges;
    private boolean[] isVisted;

    public static void main(String[] args) {
        int n = 8;
        String[] VertexValue = {"1","2","3","4","5","6","7","8"};
        Graph graph = new Graph(n);
        for (String vertex: VertexValue){
            graph.insertVertex(vertex);
        }
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        graph.insertEdge(3, 7, 1);
        graph.insertEdge(4, 7, 1);
        graph.insertEdge(2, 5, 1);
        graph.insertEdge(2, 6, 1);
        graph.insertEdge(5, 6, 1);

        graph.showGraph();
        graph.bfs();


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
        LinkedList queue = new LinkedList();
        System.out.print(getValueByIndex(i) + "=>");
        isVisted[i] = true;
        queue.addLast(i);

        while (! queue.isEmpty()){
            u = (int) queue.removeFirst();
            w = getFirstNeighbour(u);

            while (w != -1){
                if (!isVisted[w]){
                    System.out.print(getValueByIndex(w) + "=>");
                    isVisted[w] = true;
                    queue.addLast(w);
                }
                w = getNextNeighbour(u, w);

            }
        }
    }
    public void bfs(){
        for (int i = 0; i<getNumOfVertex(); i++){
            if (!isVisted[i]){
                bfs(i);
                System.out.println();
            }
        }
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
