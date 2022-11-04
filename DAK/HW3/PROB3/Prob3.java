package DAK.HW3.PROB3;

import java.io.File;
import java.util.List;

import DAK.Graph.AdjGraph;
import DAK.Graph.DijkstrasEntry;

public class Prob3 {
    public static void main(String [] args){

        File f; 

        if (args.length > 0) 
            f = new File(args[0]);
        else
            f = new File("problem_2_sample_input.txt");

        try {

            //create the graph from the givne data
            AdjGraph g = new AdjGraph(f);
            
            //generate the data from the algorithm
            List<DijkstrasEntry> de = AdjGraph.dijkstras(g);

            //print out the result of the table data
            for (int i = 0; i < de.size();i++) {
                System.out.println(de.get(i));
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    
}
