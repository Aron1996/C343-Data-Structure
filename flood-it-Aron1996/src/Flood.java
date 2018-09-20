import java.util.List;
import java.util.Map;

public class Flood {

    // Students implement this flood function.

    public static void flood(WaterColor color,
                             List<Coord> flooded_list,
                             Map<Coord, Tile> tiles,
                             Integer board_size) {
        int length = flooded_list.size();
        for (int i = 0; i < length; i++) {

            for (Coord neighbor : flooded_list.get(i).neighbors(board_size)) {

                if (tiles.get(neighbor).getColor().equals(color)) {

                    if (!flooded_list.contains(neighbor)) {

                        flooded_list.add(neighbor);
                        length++;
                    }
                }
            }
        }
    }


    public static void flood1(WaterColor color,                               // older version of flood, with so slower with 3000 in the end
                              List<Coord> flooded_list,
                              Map<Coord, Tile> tiles,
                              Integer board_size) {
        int length = flooded_list.size();
        for (int i = 0; i < length; i++) {

            for (Coord neighbor : flooded_list.get(i).neighbors(board_size)) {

                if (tiles.get(neighbor).getColor().equals(color)) {

                    if (!flooded_list.contains(neighbor)) {

                        flooded_list.add(neighbor);
                        flood1(color, flooded_list, tiles, board_size);
                    }
                }
            }
        }
    }
}
