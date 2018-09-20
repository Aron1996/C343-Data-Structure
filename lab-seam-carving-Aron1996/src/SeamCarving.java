
public class SeamCarving {


	private static int[][] initialize1(int[][] disruption_matrix){
		int matrix  = disruption_matrix.length;
		if (matrix == 0) return null;
		int n =  disruption_matrix[0].length;
		int[][] result = new int[matrix][n];
		for (int j = 0; j != n;++j){
			result[0][j] = disruption_matrix[0][j];
		}
		for (int i = 1; i < matrix;++i){
			for (int j = 0; j <n; ++j){
				result[i][j] = Integer.MAX_VALUE;
			}
		}
		return result;
	}

	private static SeamSegment[] initialize2(int[][] disruption_matrix){
		int matrix = disruption_matrix.length;
		if (matrix == 0) return null;
		int n = disruption_matrix[0].length;
		SeamSegment[] result = new SeamSegment[n];
		for (int j = 0; j < n; ++j){
			result[j] = new SeamSegment(disruption_matrix[0][j],new Coord(0,j),null);
		}
		return result;

	}

	public static Seam carve_seam(int[][] disruption_matrix) {

		int matrix = disruption_matrix.length;
		int[][] result_table = initialize1(disruption_matrix);
		if (result_table == null) return null;
		int n  = disruption_matrix[0].length;
		SeamSegment[] candidates = initialize2(disruption_matrix);

		for (int i =1; i < matrix; ++i) {
			SeamSegment[] aCopy = candidates.clone();
			for (int j = 0; j < n; ++j) {
				if (j - 1 >= 0) {
					int tempL = result_table[i - 1][j] + disruption_matrix[i][j - 1];
					if (tempL < result_table[i][j - 1]) {
						result_table[i][j - 1] = tempL;
						candidates[j - 1] = new SeamSegment(tempL, new Coord(i, j - 1), aCopy[j]);
					}
				}

				int tempM = result_table[i - 1][j] + disruption_matrix[i][j];
				if (tempM < result_table[i][j]) {
					result_table[i][j] = tempM;
					candidates[j] = new SeamSegment(tempM, new Coord(i, j), aCopy[j]);
				}

				if (j + 1 < n) {
					int tempR = result_table[i - 1][j] + disruption_matrix[i][j + 1];
					if (tempR < result_table[i][j + 1]) {
						result_table[i][j + 1] = tempR;
						candidates[j + 1] = new SeamSegment(tempR, new Coord(i, j + 1), aCopy[j]);
					}
				}
			}
		}
		assert candidates != null;
		Seam best = candidates[0].getSeam();
			for (int i = 1; i < n; ++i){
				Seam current = candidates[i].getSeam();
				if (current.disruption < best.disruption){
					best = current;
				}
			}
			return best;
		}

}
