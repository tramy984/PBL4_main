
package server;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;


public class Dijkstra {
    private int[] dist;
    private int[] prev;
    private boolean[] visited;

    public void dijkstra(int[][] graph, int n) {
  // Khởi tạo các mảng với kích thước n
        dist = new int[n];
        prev = new int[n];
        visited = new boolean[n];
        
        // Đặt giá trị khởi tạo cho các mảng
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(prev, -1);
        dist[0] = 0; // Giả sử đỉnh nguồn là đỉnh 0

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(i -> dist[i]));
        pq.add(0);

        while (!pq.isEmpty()) {
            int u = pq.poll();
            visited[u] = true;

            for (int v = 0; v < n; v++) {
                if (!visited[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                    prev[v] = u;
                    pq.add(v);
                }
            }
        }
    }

    public String getPaths(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                sb.append("Không có đường đi từ A đến ").append((char)(65+i)).append("\n");
            } else {
                sb.append("Đường đi từ A đến ").append((char)(65+i)).append(" (quãng đường: ").append(dist[i]).append("): ");
                printPath(i, sb);
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    private void printPath(int i, StringBuilder sb) {
        if (i == -1) return;
        printPath(prev[i], sb);
        sb.append((char)(65+i)).append(" ");
    }
    public int[] getDist() {
        return dist;
    }

    public int[] getPrev() {
        return prev;
    }
    
}
