import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class check {
    public static boolean checker(String str) {
        for(int i = 0; i < str.length(); i++) {
            if(!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}

class init extends JPanel {
//    vs Computer
    JTextField textField_1 = new JTextField(5); //姓名
    JTextField textField_2 = new JTextField(5); //行
    JTextField textField_3 = new JTextField(5); //列
    JLabel jLabel_1 = new JLabel("username:");
    JLabel jLabel_2 = new JLabel("*");
    JLabel jLabel_3 = new JLabel("size:");
    JLabel jLabel_4 = new JLabel("-------");
    JButton button_1 = new JButton("vs Computer");
//    vs Human
    JTextField textField_4 = new JTextField(5); //姓名1
    JTextField textField_5 = new JTextField(5); //姓名2
    JTextField textField_6 = new JTextField(5); //行
    JTextField textField_7 = new JTextField(5); //列
    JLabel jLabel_5 = new JLabel("user1:");
    JLabel jLabel_6 = new JLabel("user2:");
    JLabel jLabel_7 = new JLabel("*");
    JLabel jLabel_8 = new JLabel("size:");
    JLabel jLabel_9 = new JLabel("-------");
    JButton button_2 = new JButton("vs Human");
//    user information
    temp t1;
    temp_2 t2;
    int row;
    int column;
    String username;
    String username_1;
    String username_2;
//    init_all
    public init() {
//      vs computer input_info
        setBackground(Color.white);
        setPreferredSize(new Dimension(600, 75));
        setLayout(new BorderLayout());
        JPanel p1 = new JPanel();
        p1.add(jLabel_1);
        p1.add(textField_1);
        p1.add(jLabel_3);
        p1.add(textField_2);
        p1.add(jLabel_2);
        p1.add(textField_3);
        p1.add(button_1);
        p1.add(jLabel_4);
        add(p1, BorderLayout.NORTH);
        button_1.addActionListener(new ButtonListener());
//      vs man input_info
        JPanel p2 = new JPanel();
        p2.add(jLabel_5);
        p2.add(textField_4);
        p2.add(jLabel_6);
        p2.add(textField_5);
        p2.add(jLabel_8);
        p2.add(textField_6);
        p2.add(jLabel_7);
        p2.add(textField_7);
        p2.add(button_2);
        p2.add(jLabel_9);
        add(p2, BorderLayout.CENTER);
        button_2.addActionListener(new ButtonListener());
    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == button_1) {
                if (check.checker(textField_2.getText()) && check.checker(textField_3.getText())
                    && textField_2.getText() != null && textField_3.getText() != null &&
                    textField_1.getText() != null) {
                    username = textField_1.getText();
                    row = Integer.parseInt(textField_2.getText());
                    column = Integer.parseInt(textField_3.getText());
                    jLabel_4.setText("let's compete");
                    t1 = new temp(row, column, username);
                    JFrame frame = new JFrame("CHESSBOARD(vs Computer)");
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.add(t1);
                    frame.pack();
                    frame.setVisible(true);
                } else {
                    jLabel_4.setText("illegal input");
                }
            } else if(e.getSource() == button_2) {
                if (check.checker(textField_6.getText()) && check.checker(textField_7.getText())
                        && textField_6.getText() != null && textField_6.getText() != null &&
                        textField_4.getText() != null && textField_5.getText() != null) {
                    button_1.setEnabled(false);
                    username_1 = textField_4.getText();
                    username_2 = textField_5.getText();
                    row = Integer.parseInt(textField_6.getText());
                    column = Integer.parseInt(textField_7.getText());
                    t2 = new temp_2(row, column, username_1, username_2);
                    JFrame frame = new JFrame("CHESSBOARD(vs Human)");
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.add(t2);
                    frame.pack();
                    frame.setVisible(true);
                } else {
                    jLabel_9.setText("illegal input");
                }
            }
        }
    }
}

public class Play {
    public static void main(String[] args) {
        JFrame frame_2 = new JFrame("hello");
        frame_2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame_2.add(new init());
        frame_2.pack();
        frame_2.setVisible(true);
    }
}