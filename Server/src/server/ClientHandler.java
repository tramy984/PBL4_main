package server;
import java.io.*;
import java.net.*;
import java.util.*;

public class ClientHandler extends Thread{
    private Socket socket;
    private ObjectOutputStream out;       // Sử dụng ObjectOutputStream để gửi đối tượng
    private ObjectInputStream in;     
    private javax.swing.JTextArea history; // Tham chiếu đến JTextArea trong Server
    private int[][] matrix;               // Ma trận sẽ được nhận từ client
    private int[] dist;
    private int[] prev;
    public ClientHandler(Socket socket, javax.swing.JTextArea history) {
        this.socket = socket;
        this.history = history;
    }
    
    @Override
    public void run() {
        try {
            // Tạo luồng input/output để truyền/nhận đối tượng
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            while (true) { 
            // Nhận dữ liệu từ client (giả sử client gửi ma trận)
            matrix = (int[][]) in.readObject();
            history.append("\nMa trận trọng số được gửi từ Client " + socket.getInetAddress().getHostAddress() + ":\n");
            printMatrix(matrix);
            
            // Broadcast ma trận cho tất cả các client khác
            broadcastMatrix(matrix);
            Dijkstra dj=new Dijkstra();
            dj.dijkstra(matrix, matrix.length);
            history.setText(history.getText()+dj.getPaths(matrix.length));   
             // Lấy kết quả từ Dijkstra (2 mảng dist và prev)
            dist = dj.getDist();
            prev = dj.getPrev();

        // Đóng gói kết quả vào một mảng hoặc đối tượng để gửi
        Object[] result = new Object[2];
        result[0] = dist;  // Ma trận khoảng cách ngắn nhất
        result[1] = prev;  // Ma trận chỉ đường

        // Gửi kết quả lại cho client
        out.writeObject(result);
        out.flush();
//  // Hiển thị kết quả lên history
//            StringBuilder sb = new StringBuilder();
//            sb.append("\nShortest distances (dist):\n");
//            for (int d : dist) {
//                sb.append(d).append(" ");
//            }
//            sb.append("\nPrevious nodes (prev):\n");
//            for (int p : prev) {
//                sb.append(p).append(" ");
//            }
//
//            history.append("\n" + sb.toString());
        }
        } catch (IOException | ClassNotFoundException e) {
            history.append("\nConnection error: " + e.getMessage());
        } finally {
            try {
                socket.close();
                System.out.print(("Socket closed"));
            } catch (IOException e) {
                history.append("\nError closing socket: " + e.getMessage());
            }
        }
    }

    // In ma trận nhận được từ client lên JTextArea của server
    private void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int elem : row) {
                history.append(elem + " ");
            }
            history.append("\n");
        }
    }

    // Phát lại ma trận đến tất cả các client khác
    private void broadcastMatrix(int[][] matrix) {
//        for (ClientHandler client : clients) {
//            if (client != this) {  // Không gửi lại cho chính client đã gửi ma trận
//                try {
//                    client.out.writeObject(matrix);  // Gửi ma trận tới các client khác
//                    client.out.flush();
//                } catch (IOException e) {
//                    history.append("\nError broadcasting matrix to client: " + e.getMessage());
//                }
//            }
//        }
    }
}
