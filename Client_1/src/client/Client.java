package client;
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.filechooser.*;


public class Client extends javax.swing.JFrame {

    Socket socket;
    String s="";
    int n;
    int[][] array;
    ObjectOutputStream out;
    ObjectInputStream in;
    int[] dist;
    int[] prev;
    public Client() {
        initComponents();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtPort = new javax.swing.JTextField();
        btnStart = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        history = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnFile = new javax.swing.JButton();
        btnSend = new javax.swing.JButton();
        New = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtMessage = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("PORT NO");

        txtPort.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtPort.setText("7788");

        btnStart.setBackground(new java.awt.Color(153, 204, 255));
        btnStart.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        btnStart.setText("CONNECT");
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });

        history.setColumns(20);
        history.setRows(5);
        jScrollPane1.setViewportView(history);

        jPanel2.setBackground(new java.awt.Color(153, 204, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("CLIENT");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(192, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(171, 171, 171))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnFile.setIcon(new javax.swing.ImageIcon("C:\\Users\\ADMIN\\Pictures\\Screenshots\\Screenshot 2024-10-02 223931.png")); // NOI18N
        btnFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFileActionPerformed(evt);
            }
        });

        btnSend.setIcon(new javax.swing.ImageIcon("C:\\Users\\ADMIN\\Pictures\\Screenshots\\Screenshot 2024-10-03 151205.png")); // NOI18N
        btnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendActionPerformed(evt);
            }
        });

        New.setIcon(new javax.swing.JLabel() {
            public javax.swing.Icon getIcon() {
                try {
                    return new javax.swing.ImageIcon(
                        new java.net.URL("https://th.bing.com/th/id/OIP.DKSGLYzyCUazXVWtJ0tRjgHaHa?pid=ImgDet&w=70&h=60&c=7&dpr=1.3")
                    );
                } catch (java.net.MalformedURLException e) {
                }
                return null;
            }
        }.getIcon());
        New.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewActionPerformed(evt);
            }
        });

        txtMessage.setColumns(20);
        txtMessage.setRows(5);
        jScrollPane2.setViewportView(txtMessage);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(New, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnFile, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnSend, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnStart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(8, 8, 8)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtPort, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(24, 24, 24)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(btnStart, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnFile, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(New, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnSend, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2)))
                    .addComponent(jScrollPane1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartActionPerformed
       try {
            history.setText("Client connecting....");
            socket = new Socket("localhost", Integer.parseInt(txtPort.getText()));
           out = new ObjectOutputStream(socket.getOutputStream());
           in = new ObjectInputStream(socket.getInputStream());
            history.append("\nClient is connected");
        } catch (Exception e) {
            e.printStackTrace();
            history.append("\nFailed to connect to server.");
        }

    }//GEN-LAST:event_btnStartActionPerformed
    private boolean ktra(String input) {
    try {
        // Tách các dòng
        String[] lines = input.split("\n");

        // Kiểm tra số lượng dòng phải lớn hơn 1
        if (lines.length < 2) {
            return false;
        }

        // Kiểm tra dòng đầu tiên là số nguyên đại diện cho kích thước ma trận
        int n = Integer.parseInt(lines[0].trim());

        // Kiểm tra số lượng dòng của ma trận (phải có n dòng)
        if (lines.length != n + 1) {
            return false;
        }

        // Kiểm tra từng dòng ma trận
        for (int i = 1; i <= n; i++) {
            String[] values = lines[i].trim().split("\\s+");

            // Mỗi dòng phải có n phần tử
            if (values.length != n) {
                return false;
            }

            // Kiểm tra tất cả các phần tử đều là số nguyên
            for (String value : values) {
                Integer.parseInt(value); // Sẽ ném ra NumberFormatException nếu không phải số
            }
        }
        // Nếu không có lỗi, trả về true
        return true;
    } catch (NumberFormatException e) {
        // Nếu có bất kỳ lỗi nào khi phân tích số, trả về false
        return false;
    }
}
    private boolean ktr_matrandoixung(int[][] a,int n){
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(a[i][j]!=a[j][i]) return false;
            }
        }
        return true;
    }
    private void btnSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendActionPerformed
          try {
            if (array == null) {
                String input = txtMessage.getText();

                // Gọi hàm kiểm tra
                if (!ktra(input)) {
                    JOptionPane.showMessageDialog(this, "Phải nhập vào n+1 dòng với dòng dầu là n(số đỉnh) và n dòng tiếp theo là khoảng cách", "Lỗi định dạng", JOptionPane.ERROR_MESSAGE);
                    return;
                }
             
                // Nếu ma trận chưa được nạp từ file, lấy ma trận từ text area
                String[] lines = txtMessage.getText().split("\n");
                n = Integer.parseInt(lines[0].trim());  // Kích thước ma trận
                array = new int[n][n];

                for (int i = 1; i <= n; i++) {
                    String[] values = lines[i].trim().split("\\s+");
                    for (int j = 0; j < n; j++) {
                        array[i - 1][j] = Integer.parseInt(values[j]);
                    }
                }
            }
            if (!ktr_matrandoixung(array,n)) {
                JOptionPane.showMessageDialog(this, "Ma trận trọng số phải là ma trận đối xứng", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            // Gửi ma trận lên server
            out.writeObject(array);
            out.flush();
            history.append("\nMatrix sent to server.");

            // Nhận kết quả từ server
            Object[] result = (Object[]) in.readObject();
            dist = (int[]) result[0];
            prev = (int[]) result[1];

            
            openGraphFrame();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            history.append("\nError sending data to server.");
        }
           
    }//GEN-LAST:event_btnSendActionPerformed

    private void openGraphFrame() {        
        Graph graphFrame = new Graph(array, array.length,dist,prev);
        graphFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        graphFrame.setVisible(true);
        graphFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                Client.this.setVisible(true);
                array = null;
                dist = null;
                prev = null;
                txtMessage.setText("");
            }
    });
     
    this.setVisible(false);
}

    private void btnFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFileActionPerformed
        
        // Tạo một đối tượng JFileChooser
        JFileChooser fileChooser = new JFileChooser();
        
        // Tùy chọn bộ lọc file (nếu cần, ví dụ để chọn file .txt)
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");
        fileChooser.setFileFilter(filter);
        
        // Hiển thị hộp thoại chọn file (Open dialog)
        int result = fileChooser.showOpenDialog(null);

        // Kiểm tra xem người dùng có chọn file hay không
        if (result == JFileChooser.APPROVE_OPTION) {
            // Lấy file được chọn
            File selectedFile = fileChooser.getSelectedFile();
            
            s=selectedFile.getAbsolutePath();
             ReadFile reader = new ReadFile();
        
        // Gọi phương thức read và lưu kết quả vào biến array
        array = reader.read(s,n);
        n=array.length;
        StringBuilder sb = new StringBuilder();
            sb.append(n+"\n");
            for (int[] row : array) {
                for (int value : row) {
                    sb.append(value).append(" ");
                }
                sb.append("\n");
            }
            txtMessage.setText(sb.toString());
        } 
        
       
    }//GEN-LAST:event_btnFileActionPerformed

    private void NewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewActionPerformed
        MatrixInput input = new MatrixInput();
        input.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        input.setVisible(true);
        input.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                int[][] matrix = input.getMatrix();
                if(matrix!=null){
                    array = matrix;
                    n=array.length;
                    Client.this.setVisible(true);
                    StringBuilder sb = new StringBuilder();
                    sb.append(array.length).append("\n");
                    for (int[] row : array) {
                        for (int value : row) {
                            sb.append(value).append(" ");
                        }
                        sb.append("\n");
                    }
                    txtMessage.setText(sb.toString());
                }
                else{
                     Client.this.setVisible(true);;
                }
            }
        });
        this.setVisible(false);
    }//GEN-LAST:event_NewActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Client().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton New;
    private javax.swing.JButton btnFile;
    private javax.swing.JButton btnSend;
    private javax.swing.JButton btnStart;
    private javax.swing.JTextArea history;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea txtMessage;
    private javax.swing.JTextField txtPort;
    // End of variables declaration//GEN-END:variables
}
