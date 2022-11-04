package DAK.HW3.PROB3;

import java.io.File;
import java.util.List;

import DAK.Graph.AdjGraph;
import DAK.Graph.DijkstrasEntry;

public class Prob3 {
    public static void main(String [] args){
        File f = new File("problem_2_sample_input.txt");

        try {
            AdjGraph g = new AdjGraph(f);
            System.out.println(g); 
            List<DijkstrasEntry> de = AdjGraph.dijkstras(g);

            for (int i = 0; i < de.size();i++) {
                System.out.println(de.get(i));
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    
}
