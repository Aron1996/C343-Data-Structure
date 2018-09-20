import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;


public class Routing {

    /**
     * TODO
     * <p>
     * The findPaths function takes a board and a list of points
     * that need to be connected. The function returns
     * a list of paths that connect the points.
     */

    private static ArrayList<Coord> findPath(Board board,Coord[] pair,
                                             int numOfPath) {
        ArrayList<Coord> result = new ArrayList<>();
        Queue<ArrayList<Coord>> frontier = new LinkedList<>();
        HashSet<Coord> explored = new HashSet<>();
        result.add(pair[0]);
        frontier.add(result);

        while (!frontier.isEmpty()) {
            ArrayList<Coord> path = frontier.remove();
            Coord a_coord = path.get(path.size() - 1);
            explored.add(a_coord);
            if (a_coord.equals(pair[1])) {
                for (Coord grid : path) {
                    board.putValue(grid, numOfPath);
                }
                return path;
            } else {
                for (Coord child : board.adj(a_coord)) {
                    if (!explored.contains(child) &&
                            (board.isAvailable(child)||child.equals(pair[1]))) {
                        ArrayList<Coord> newPath = (ArrayList<Coord>) path.clone();
                        newPath.add(child);
                        frontier.add(newPath);
                    }
                }
            }
        }
        return null;
    }
    //static ArrayList<ArrayList<Coord>> paths  = new ArrayList<>();

    public static ArrayList<ArrayList<Coord>>
    findPaths(Board board, ArrayList<Coord[]> points) {
        ArrayList<ArrayList<Coord>> result = new ArrayList<>();
        for (int i = 0; i < points.size();++i){
            result.add(findPath(board,points.get(i),i+1));
        }
        return result;
    }


   /* private static void walk(Board board,
                      int pathNum,Coord[] pair,
                      ArrayList<Coord[]> points){
        for (Coord child: board.adj(pair[0])){
             paths.get(pathNum-1).add(child);
             if (child.equals(pair[1])){
                 if (pathNum == paths.size()){//no more path
                     return;
                 }
                 else{
                     Coord[] fp = new Coord[2];
                     fp[0] = new Coord(points.get(pathNum)[0].
                             x,points.get(pathNum)[0].y);
                     fp[1] = new Coord(points.get(pathNum)[1].x,points.get(pathNum)[1].y);
                     walk(board,pathNum+1,fp,
                             points);
                 }
             }
             else if(board.getValue(child) == 0){
                 board.putValue(child,pathNum);
                 Coord[] newAim = new Coord[2];
                 newAim[0] = new Coord(child.x,child.y);
                 newAim[1] = new Coord(pair[1].x,pair[1].y);
                 walk(board,pathNum,newAim,points);
                 board.putValue(child,0);
             }
             paths.get(pathNum-1).remove(paths.get(pathNum-1).size()-1);
        }
    }*/

}
