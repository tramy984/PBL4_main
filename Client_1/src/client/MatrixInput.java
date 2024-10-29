package client;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MatrixInput extends JFrame {
    private JTextField nField;
    private JPanel matrixPanel;
    private JTextField[][] matrixFields;
    private int[][] matrix; // Mảng hai chiều để lưu ma trận
 //   private Client client;

    public MatrixInput() {
 //       this.client=clientFrame;
        setTitle("Nhập ma trận NxN");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel để nhập số N
        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Nhập N:"));
        nField = new JTextField(5);
        inputPanel.add(nField);
        JButton createMatrixButton = new JButton("Tạo ma trận");
        inputPanel.add(createMatrixButton);
        add(inputPanel, BorderLayout.NORTH);

        // Panel để nhập ma trận
        matrixPanel = new JPanel();
        add(matrixPanel, BorderLayout.CENTER);

        // Nút Lưu ma trận
        JButton saveMatrixButton = new JButton("Lưu ma trận");
        add(saveMatrixButton, BorderLayout.SOUTH);

        // Xử lý khi bấm nút Tạo ma trận
        createMatrixButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int n = Integer.parseInt(nField.getText());
                    createMatrix(n);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập số nguyên hợp lệ.");
                }
            }            
        });
        

        // Xử lý khi bấm nút Lưu ma trận
        saveMatrixButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveMatrix();
            }
        });

        setSize(400, 400);
        setVisible(true);
    }

    // Phương thức tạo ma trận NxN
    private void createMatrix(int n) {
        matrixPanel.removeAll();
        matrixPanel.setLayout(new GridLayout(n, n));
        matrixFields = new JTextField[n][n];
        matrix = new int[n][n]; // Khởi tạo mảng hai chiều để lưu ma trận

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrixFields[i][j] = new JTextField(5);
                matrixPanel.add(matrixFields[i][j]);
            }
        }

        matrixPanel.revalidate();
        matrixPanel.repaint();
    }

    // Phương thức lưu ma trận vào mảng hai chiều
    private void saveMatrix() {
        try {
            for (int i = 0; i < matrixFields.length; i++) {
                for (int j = 0; j < matrixFields[i].length; j++) {
                    matrix[i][j] = Integer.parseInt(matrixFields[i][j].getText());
                }
            }
            //JOptionPane.showMessageDialog(null, "Ma trận đã được lưu thành công!");
           // client.array=matrix;
            
            // Đóng cửa sổ sau khi lưu
           // dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập số nguyên hợp lệ vào tất cả các ô.");
        }
    }
    
    protected int[][] getMatrix(){
        return matrix;
    }
    public static void main(String[] args) {

    }
}
