package DAK.Graph;

import java.util.ArrayList;
import java.util.List;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * a graph that uses an adjacency matrix
 * for its underlying represnetiation
 */
public class AdjGraph extends Graph{
    private int [][] edges;
    
    private void load_size(int size) {
        //set up the adjacency matrix
        edges = new int[size][size];
    }
    AdjGraph(int size) {
     load_size(size);   
    }
    public AdjGraph(File f) throws FileNotFoundException {
        Scanner cin = new Scanner(f);
     
        
        String [] split_line = cin.nextLine().split(" ");
        load_size(split_line.length);//initilize the adjecency matrix
   
        //we run while we can read and dont overflow
        for (int i = 0; i < nodeCount(); i++) {
            for (int j = 0; j < nodeCount();j++) {
                edges[i][j] = Integer.parseInt(split_line[j]);
            }
            if (cin.hasNextLine()) split_line = cin.nextLine().split(" ");
        }

        cin.close();
    }
    @Override
    public int getEdgeWeight(int nodeA,int nodeB) {
        return edges[nodeA][nodeB];
    }

    @Override
    public int weight() {
        int ret_val = 0;
        for (int i = 0; i < nodeCount();i++)  {
            for (int j = 0; j < nodeCount();j++) {
                ret_val += edges[i][j];
            }
        }

        return ret_val;
    }
    /** 
     * returns the wieght in the case that
     * the edges do not contain direction information
    */
    public int wieghtSym() {
        int ret_val = 0;
        for (int i = 0; i < nodeCount();i++)  {
            //note that j starts at i here
            //this has the effect of making the for loops
            //look like a "triangle" as apposed to a square
            for (int j = i; j < nodeCount();j++) {
                ret_val += edges[i][j];
            }
        }
        return ret_val;
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
        File f = new File("./input.txt");
        try {
            AdjGraph g = new AdjGraph(f);
            AdjGraph minTree = (AdjGraph)minSpanTree(g); 
            System.out.println(minTree);
        } catch (Exception e) {
            System.out.println(e);
        }
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
        int runs = g.nodeCount() - 1;
        for (int i = 0; i < runs;i+=1) {
            int least_edge = -1;
            int least_next_node = -1;
            int least_from_node = -1;

            for (int boundry_idx = 0; boundry_idx < boundry.size(); boundry_idx+=1) {
                for (int j = 0; j < g.nodeCount(); j++) {
                    if (!boundry.contains(j)) { //the node j is outside the boundry
                        int out_edge = g.getEdgeWeight(boundry.get(boundry_idx),j); //this is the bridge that sends us to j
                        if ((out_edge != 0) && 
                            (least_edge == -1 || out_edge < least_edge)) { //if that bridge has weight less than our least wieght
 
                            least_edge = out_edge; //get the new best node
                            least_next_node = j; //get the new best node
                            least_from_node = boundry.get(boundry_idx);//get the new best node

                        }
                    }
                }
            }
            
            //add the given edge to the tree 
            ret_val.addEdgeSym(least_from_node,least_next_node,least_edge);
            //add the next node to the boundry
            boundry.add(least_next_node);
        }



        return ret_val;
    }

    public static List<DijkstrasEntry> dijkstras(AdjGraph g) {
        return dijkstras(g,0);
    }

    public static List<DijkstrasEntry> dijkstras(AdjGraph g,int initial_node) {
        
        List<DijkstrasEntry> ret_val = new ArrayList<DijkstrasEntry>();
        List<Integer> visited = new ArrayList<Integer>();
        
        //initilize the table with starting values

        for (int i = 0; i < g.nodeCount();i++) {
            //use Integer.MAX_VALUE as infinity for convinence
            //-1 indicates no previous parent
            ret_val.add(new DijkstrasEntry(i,-1,Integer.MAX_VALUE));
        }
        
        //make sure that the initial node has 0 distance from itself
        //and no previous node
        ret_val.get(initial_node).distance = 0;
        ret_val.get(initial_node).previous = -1; 


        //since dijkstras finishes when we visit every node,
        //and we visit one new node each iteration,
        //we need to iterate the algorithm once for each node we have
        for (int o = 0; o < g.nodeCount();o++) {

            //get the lowest non visited node
            //this should really be a priority queue or some other data structure
            //but this works for only 10 nodes
            
                //get the first node that we have not visited
                int lowest = 0;
                while (visited.contains(lowest)) {
                    lowest += 1;
                }

                //loop over the nodes and get the lowest one that has not been visited
                for (int i = 0; i < g.nodeCount();i++){
                    if (!visited.contains(i) && ret_val.get(i).distance < ret_val.get(lowest).distance) {
                        lowest = i;
                    }
                }

            //we visited this node
            visited.add(lowest);

            //loop over the connections of the focus node
            //and update the lowest connection
            for (int i = 0; i < g.nodeCount();i+=1) {
                //the distance from this node out to another given node
                int test_val = ret_val.get(lowest).distance + g.edges[lowest][i];
                if (g.edges[lowest][i] != 0 && //the edge exists
                    test_val < ret_val.get(i).distance // and our walk to it is less than the current walk
                    ) {

                        //update the entry in the table
                        ret_val.get(i).distance = test_val;
                        ret_val.get(i).previous = lowest;
                }
            }
        }

        //return the table
        return ret_val;
    }
}
