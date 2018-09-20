package graphs;
import sequences.*;

/**
 * TODO
 *
 * Complete the AdjacencyList class, implementing all of the
 * methods needed for the Graph interface and
 * finish the constructor.
 *
 */
public class AdjacencyList implements Graph<Integer> {
    Array<SLinkedList<Integer>> adjacent;

    public AdjacencyList(int num_vertices) {
        adjacent = new Array<>(num_vertices, null);
        for(int i = 0; i < num_vertices; ++i){
            adjacent.set(i, new SLinkedList<Integer>());
        }
    }

    public void addEdge(Integer i, Integer j ){
        adjacent.get(i).push(j);
    }

    public int numVertices(){
        return adjacent.size();
    }

    public SLinkedList<Integer> adjacent(Integer i){
        return adjacent.get(i);
    }

    public Array<Integer> vertices(){
        Array<Integer> result = new Array<>(numVertices(),0);
        for (int i = 0; i != numVertices();++i){
            result.set(i, i);
        }
        return result;
    }
}