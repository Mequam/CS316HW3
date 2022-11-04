package DAK.HW3.PROB2;

import DAK.Graph.AdjGraph;
import java.io.File;

public class Prob2 {
    public static void main(String [] args){

        //set up the file that we want to read from
        File f = null;
        if (args.length > 0) f = new File(args[0]);
        else f = new File("input.txt");

        try {
            //create the graph
            AdjGraph g = new AdjGraph(f);
            //get the minimum spanning tree
            AdjGraph minTree = (AdjGraph)AdjGraph.minSpanTree(g); 
            //print out the tree to the screen
            System.out.println(minTree);

        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
