package DAK.Graph;

public class DijkstrasEntry {
        @Override
        public String toString() {
            return "Source -> Node" + Integer.toString(node) + ":" + Integer.toString(distance);
        }
        public DijkstrasEntry() {}

        /**convinence function to create a new entry 
         * 
         * note that -1 indicates null or infinity depending on the context
        */
        public DijkstrasEntry(int node,int previous,int distance) {
            this.node = node;
            this.previous = previous;
            this.distance = distance;
        }
        public int node;
        public int previous;
        public int distance;
    
}
