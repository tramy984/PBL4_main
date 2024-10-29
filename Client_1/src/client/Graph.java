
package client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class DrawDothi extends JPanel 
{
    private final int[][] adjacencyMatrix;
    private Point[] vertices;
     private final String[] labels;
    public DrawDothi(int[][] adjacencyMatrix) 
    {
        this.adjacencyMatrix = adjacencyMatrix;
        this.vertices = new Point[adjacencyMatrix.length];
        this.labels = generateLabels(adjacencyMatrix.length);  // Tạo nhãn cho các đỉnh
        setPreferredSize(new Dimension(400, 400));
        calculateVerticesPositions();
    }

//Tính toán vị trí điểm/đỉnh/ và các cạnh
    private void calculateVerticesPositions() {
        int radiusX = 150;
        int radiusY = 100;
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int n = adjacencyMatrix.length;

        vertices = new Point[n];

        for (int i = 0; i < n; i++) {
            double angle = 2 * Math.PI * i / n;
            int x = centerX + (int) (radiusX * Math.cos(angle));
            int y = centerY + (int) (radiusY * Math.sin(angle));
            vertices[i] = new Point(x, y);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        calculateVerticesPositions(); 

        g.setColor(Color.YELLOW);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.YELLOW);
         // Draw edges
        for (int i = 0; i < adjacencyMatrix.length; i++) 
        {
            for (int j = i + 1; j < adjacencyMatrix[i].length; j++) 
            {
                if (adjacencyMatrix[i][j] > 0) 
                {
                    //g.setColor(adjacencyMatrix[i][j] == 1 ? Color.BLUE : Color.RED);
                    g.setColor(Color.red);
                    g2d.setStroke(new BasicStroke(2));
                    
                    g2d.drawLine(vertices[i].x, vertices[i].y, vertices[j].x, vertices[j].y);
                    g2d.setColor(Color.BLACK);  // Đặt màu cho trọng số
                    g2d.setFont(new Font("SansSerif", Font.ITALIC, 12));
                    g2d.drawString(String.valueOf(adjacencyMatrix[i][j]), 
                        (vertices[i].x + vertices[j].x) / 2, 
                        (vertices[i].y + vertices[j].y) /2-3);
                }
            }
        }
        // Vẽ các đỉnh
        for (int i = 0; i < vertices.length; i++) {
            // Vẽ đỉnh dưới dạng hình tròn
            g.setColor(Color.YELLOW);
            g.fillOval(vertices[i].x -7, vertices[i].y-7 , 16, 16);
            // Vẽ tên đỉnh lên trên hình tròn
            g2d.setColor(Color.BLACK);  // Đặt màu cho tên đỉnh
            g2d.setFont(new Font("SansSerif", Font.BOLD, 12));
            // Điều chỉnh tọa độ để tên đỉnh nằm ngoài
            int offset = 25;  // Khoảng cách ra ngoài
            int labelX = vertices[i].x + (int) (offset * Math.cos(2 * Math.PI * i / vertices.length));
            int labelY = vertices[i].y + (int) (offset * Math.sin(2 * Math.PI * i / vertices.length));
            g.drawString(labels[i], labelX, labelY);
            g.setColor(Color.YELLOW);
        }

       
    }
    
    protected void highlightPath(Graphics2D g2d, int destination, int[] prev) {
    g2d.setColor(Color.GREEN);  // Màu cho các đỉnh trong prev[]
    int i=destination;
      // System.out.println(prev[i]);
        while(i>=0 && prev[i]>=0){
                // Tô màu cạnh nối hai đỉnh
                g2d.setStroke(new BasicStroke(3));
                g2d.drawLine(vertices[i].x, vertices[i].y, vertices[prev[i]].x, vertices[prev[i]].y);
                // Tô màu đỉnh
                g2d.fillOval(vertices[i].x - 7, vertices[i].y - 7, 16, 16);

            
        i=prev[i];
        
    }
        g2d.fillOval(vertices[0].x - 7, vertices[0].y - 7, 16, 16);
}

    private String[] generateLabels(int numLabels) 
    {
        String[] labels = new String[numLabels];
        for (int i = 0; i < numLabels; i++) {
            labels[i] = String.valueOf((char) ('A' + i));
        }
        return labels;
    }
    
}
public class Graph extends javax.swing.JFrame 
{
    private String filename;
    private int length;
    private int[][] adjacencyMatrix;
    private int[] dist;
    private int[] prev;    
    public Graph() {
        initComponents();
        initializeGraph();  
    }
    public Graph(int[][] adjacencyMatrix, int n,int[] dist,int[] prev)
    {
        this.adjacencyMatrix = adjacencyMatrix;
        this.length=n;
        this.dist=dist;
        this.prev=prev;
        initComponents();
        initializeGraph();
    }
    public String Timduong(int des){
        StringBuilder sb=new StringBuilder();
       if (des == -1) return sb.toString();
        sb.append(Timduong(prev[des]));
        sb.append((char)(65+des)).append(" ");
        return sb.toString();
    }
    private void initializeGraph() {
        

        // Create an instance of DrawDothi with the adjacency matrix
        DrawDothi graphPanel = new DrawDothi(adjacencyMatrix);

        // Add the DrawDothi panel to jPanel3
        jPanel3.setLayout(new BorderLayout());
        jPanel3.add(graphPanel, BorderLayout.CENTER);
        
        jPanel2.setLayout(new FlowLayout());
        ButtonGroup group = new ButtonGroup();
        for (int i = 1; i < length; i++) {
            char label = (char) ('A' + i);
            JRadioButton button = new JRadioButton(String.valueOf(label));
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {                 
                    // Handle button click                  
                    int destination = button.getText().charAt(0) - 'A';
                    Graphics g = graphPanel.getGraphics(); 
                     graphPanel.paintComponent(g);
                    Graphics2D g2d = (Graphics2D) g;
                    graphPanel.highlightPath(g2d,destination, prev);
                        
                }
            });
            group.add(button);
            jPanel2.add(button);
        }
        
        // Add labels and text fields to jPanel1
        jPanel1.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        for (int i = 1; i < length; i++) {
            char label = (char) ('A' + i);
            JLabel nodeLabel = new JLabel(String.valueOf(label));
            JTextField textField1 = new JTextField(10);
            JTextField textField2 = new JTextField(10);
            textField1.setText("Đường đi: "+Timduong(i));
            textField2.setText("Khoảng cách: "+dist[i]);
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.add(textField1);
            panel.add(textField2);

            gbc.gridx = 0;
            gbc.gridy = i - 1;
            gbc.weightx = 0.1;
            jPanel1.add(nodeLabel, gbc);

            gbc.gridx = 1;
            gbc.weightx = 1;
            jPanel1.add(panel, gbc);

        }
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Định đường phân tán", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14), new java.awt.Color(51, 51, 51))); // NOI18N
        jPanel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(230, 429));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 220, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 402, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Đích ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14), new java.awt.Color(51, 51, 51))); // NOI18N
        jPanel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 552, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 93, Short.MAX_VALUE)
        );

        jPanel3.setMaximumSize(new java.awt.Dimension(30767, 30767));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 261, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(153, 204, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("GRAPHIC");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(236, 236, 236)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(271, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 562, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Graph().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    // End of variables declaration//GEN-END:variables
}
