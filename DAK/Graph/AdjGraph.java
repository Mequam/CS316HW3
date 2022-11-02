package DAK.Graph;

import java.util.ArrayList;
import java.util.List;

/**
 * a graph that uses an adjacency matrix
 * for its underlying represnetiation
 */
public class AdjGraph extends Graph{
    private int [][] edges;
    AdjGraph(int size) {
        //set up the adjacency matrix
        edges = new int[size][size];
    }
   
    @Override
    public int getEdgeWeight(int nodeA,int nodeB) {
        return edges[nodeA][nodeB];
    }

    public void addEdge(int nodeA,int nodeB,int weight) {
        edges[nodeA][nodeB] = weight;
    } 
    public void addEdgeSym(int nodeA,int nodeB,int weight) {
        addEdge(nodeA, nodeB,weight);
        addEdge(nodeB, nodeA,weight);
    }
    public int nodeCount() {
        return edges.length;
    }

    @Override
    public String toString() {
        String ret_val = "";
        for (int i = 0; i < nodeCount();i+=1) {
            for (int j = 0; j < nodeCount(); j+= 1) {
                ret_val += Integer.toString(getEdgeWeight(i, j)) + " ";
            } 
            ret_val += "\n";
        }
        return ret_val;
    }

    public static void main(String [] args) {
        AdjGraph g = new AdjGraph(6);
        g.addEdgeSym(0, 1,4);
        g.addEdgeSym(0, 5,8);
        g.addEdgeSym(1, 0,4);
        g.addEdgeSym(1, 2,7);
        g.addEdgeSym(1, 5,11);
        g.addEdgeSym(2, 1,7);
        g.addEdgeSym(2, 3,2);
        g.addEdgeSym(2, 4,3); 
        g.addEdgeSym(3, 2,2);
        g.addEdgeSym(3, 4,6);
        g.addEdgeSym(3, 5,7);
        g.addEdgeSym(4, 2,3);
        g.addEdgeSym(4, 3,6);
        g.addEdgeSym(4, 5,1);
        
        System.out.println(g);
    }

    /** 
     * returns a minimal spanning tree of the given graph
     * 
    */
    public static Graph minSpanTree(AdjGraph g) {
        AdjGraph ret_val = new AdjGraph(g.nodeCount());
        List<Integer> boundry = new ArrayList<Integer>();
        
        boundry.add(0);//we allways start from node 0


        //theres got to be a better way to do this
        for (int i = 0; i < g.nodeCount();i+=1) {
            int least_edge = -1;
            int least_next_node = -1;
            int least_from_node = -1;

            for (int boundry_idx = 0; boundry_idx < boundry.size(); boundry_idx+=1) {
                for (int j = 0; j < g.nodeCount(); j++) {
                    if (!boundry.contains(j)) { //the node j is outside the boundry
                        int out_edge = g.getEdgeWeight(boundry.get(boundry_idx),j); //this is the bridge that sends us to j
                        if (least_edge == -1 || out_edge < least_edge) { //if that bridge has weight less than our least wieght
                            
                            least_edge = out_edge; //get the new best node
                            least_next_node = j; //get the new best node
                            least_from_node = boundry.get(boundry_idx);//get the new best node

                        }
                    }
                }
            }
            
            //add the given edge to the tree 
            ret_val.addEdge(least_from_node,least_next_node);
            //add the next node to the boundry
            boundry.add(least_next_node);
        }



        return ret_val;
    }

}
