package DAK.Graph;

/**
 * generic class that represents a graph
*/
public abstract class Graph {
    /** 
     * an entry in the table used in dijkstras algorithm
    */
    public void addEdge(int nodeA,int nodeB) {
        addEdge(nodeA, nodeB,1);
    }
    abstract public void addEdge(int nodeA,int nodeB,int wieght);
    abstract public int nodeCount();
    abstract public int getEdgeWeight(int nodeA,int nodeB);
    abstract public int weight();

}