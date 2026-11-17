import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class FireDrillExits {

    // Computes minimum steps to the nearest exit for every cell using Multi-Source BFS
    public static int[][] exitDistance(char[][] plan) {
        if (plan == null || plan.length == 0 || plan[0].length == 0) {
            return new int[0][0];
        }

        int rows = plan.length;
        int cols = plan[0].length;
        int[][] dist = new int[rows][cols];
        Queue<int[]> queue = new ArrayDeque<>();

        // Initialize distance matrix with -1 and enqueue all exits ('E') at distance 0
        for (int r = 0; r < rows; r++) {
            Arrays.fill(dist[r], -1);
            for (int c = 0; c < cols; c++) {
                if (plan[r][c] == 'E') {
                    dist[r][c] = 0;
                    queue.offer(new int[]{r, c});
                }
            }
        }

        // 4-directional movement: up, down, left, right
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0];
            int c = curr[1];

            for (int[] d : dirs) {
                int nr = r + d[0];
                int nc = c + d[1];

                // Check bounds, ensure cell is an open room ('.'), and not yet visited (dist == -1)
                if (nr >= 0 && nr < rows && nc >= 0 && nc < cols
                        && plan[nr][nc] == '.' && dist[nr][nc] == -1) {
                    dist[nr][nc] = dist[r][c] + 1;
                    queue.offer(new int[]{nr, nc});
                }
            }
        }

        return dist;
    }

    public static void main(String[] args) {
        char[][] plan = {
            {'.', '#', '.', 'E'},
            {'.', '.', '.', '#'},
            {'E', '#', '.', '.'}
        };

        int[][] result = exitDistance(plan);
        for (int[] row : result) {
            for (int j = 0; j < row.length; j++) {
                System.out.print(row[j] + (j < row.length - 1 ? " " : ""));
            }
            System.out.println();
        }
        // Expected Output:
        // 2 -1 1 0
        // 1 2 2 -1
        // 0 -1 3 4
    }
}
