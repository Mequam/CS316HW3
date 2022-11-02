package DAK.Graph;

/**
 * generic class that represents a graph
*/
public abstract class Graph {
    public void addEdge(int nodeA,int nodeB) {
        addEdge(nodeA, nodeB,1);
    }
    abstract public void addEdge(int nodeA,int nodeB,int wieght);
    abstract public int nodeCount();
    abstract public int getEdgeWeight(int nodeA,int nodeB);

}