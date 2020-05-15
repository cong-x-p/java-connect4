import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class temp_2 extends JPanel {
    int row;
    int column;
    String username_1;
    String username_2;
    JButton[] buttons;          //按钮（一排）
    JLabel[][] chessBoard;      //棋盘
    int[][] already_Have;       //存储落子位置
    int player = 1;             //1为玩家1，2为玩家2
    int winner = 0;
    int label_even = 0;
    int count;

    public temp_2(int row, int column, String username_1, String username_2) {
        this.row = row;
        this.column = column;
        this.username_1 = username_1;
        this.username_2 = username_2;
        buttons = new JButton[this.column];
        chessBoard = new JLabel[this.row][this.column];
        already_Have = new int[this.row][this.column];

        JPanel chessBoard_J = new JPanel();
        chessBoard_J.setLayout(new GridLayout(this.row, this.column));
        for(int i = 0; i < this.row; i++) {
            for(int j = 0; j < this.column; j++) {
                chessBoard[i][j] = new JLabel(new ImageIcon("src/start.png"), JLabel.CENTER);
                chessBoard_J.add(chessBoard[i][j]);
            }
        }

        JPanel buttons_J = new JPanel();
        buttons_J.setLayout(new GridLayout(1, this.column));
        for(int j = 0; j < this.column; j++) {
            buttons[j] = new JButton("next_move");
            buttons[j].addActionListener(new ButtonListener());
            buttons_J.add(buttons[j]);
        }

        for(int i = 0; i < this.row; i++) {
            for(int j = 0; j < this.column; j++) {
                already_Have[i][j] = 0;
            }
        }

        setLayout(new BorderLayout());
        add(chessBoard_J, BorderLayout.CENTER);
        add(buttons_J, BorderLayout.NORTH);
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            for(int j = 0; j < column; j++) {
                if(e.getSource() == buttons[j]) {
                    nextMove(j);
                    break;
                }
            }
            if(checkWinState()) {
                over(winner);
            } else if(!checkWinState()) {
                if(count == row * column) {
                    over(0);
                }
            }
        }
    }

    private void nextMove(int j) {
        boolean label_0 = false;
        for(int i = row - 1; i >= 0; i--) {
            if(already_Have[i][j] != 1 && already_Have[i][j] != 2) {
                if(player == 1) {
                    chessBoard[i][j].setIcon(new ImageIcon("src/blue.png"));
                    count++;
                } else {
                    chessBoard[i][j].setIcon(new ImageIcon("src/black.png"));
                    count++;
                }
                already_Have[i][j] = player;
                label_0 = true;
                break;
            }
        }
        if(label_0) changePlayer();
    }

    private void changePlayer(){
        if(player == 1) {
            player = 2;
        } else {
            player = 1;
        }
    }


    private void setWinner(int winner) {
        this.winner = winner;
    }

    private boolean canMove(int row, int col) {
        return (row >= 0) && (col >= 0) && (row <= this.row - 1) && (col <= this.column - 1);
    }

    private boolean checkWinState() {
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.column; j++) {
                if (canMove(i, j + 3) && already_Have[i][j] != 0) {
                    if (already_Have[i][j] == already_Have[i][j + 1]
                            && already_Have[i][j] == already_Have[i][j + 2]
                            && already_Have[i][j] == already_Have[i][j + 3]) {
                        setWinner(already_Have[i][j]);
                        return true;
                    }
                }
            }
        }

        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.column; j++) {
                if (canMove(i - 3, j) && already_Have[i][j] != 0) {
                    if (already_Have[i][j] == already_Have[i - 1][j]
                            && already_Have[i][j] == already_Have[i - 2][j]
                            && already_Have[i][j] == already_Have[i - 3][j]) {
                        setWinner(already_Have[i][j]);
                        return true;
                    }
                }
            }
        }

        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.column; j++) {
                if (canMove(i + 3, j + 3) && already_Have[i][j] != 0) {
                    if (already_Have[i][j] == already_Have[i + 1][j + 1]
                            && already_Have[i][j] == already_Have[i + 2][j + 2]
                            && already_Have[i][j] == already_Have[i + 3][j + 3]) {
                        setWinner(already_Have[i][j]);
                        return true;
                    }
                }
            }
        }

        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.column; j++) {
                if (canMove(i - 3, j + 3) && already_Have[i][j] != 0) {
                    if (already_Have[i][j] == already_Have[i - 1][j + 1]
                            && already_Have[i][j] == already_Have[i - 2][j + 2]
                            && already_Have[i][j] == already_Have[i - 3][j + 3]) {
                        setWinner(already_Have[i][j]);
                        return true;
                    }
                }
            }
        }

        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.column; j++) {
                if (canMove(i + 3, j) && already_Have[i][j] != 0) {
                    if (already_Have[i][j] == already_Have[i + 1][j]
                            && already_Have[i][j] == already_Have[i + 2][j]
                            && already_Have[i][j] == already_Have[i + 3][j]) {
                        setWinner(already_Have[i][j]);
                        return true;
                    }
                }
            }
        }

        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.column; j++) {
                if (canMove(i, j - 3) && already_Have[i][j] != 0) {
                    if (already_Have[i][j] == already_Have[i][j - 1]
                            && already_Have[i][j] == already_Have[i][j - 2]
                            && already_Have[i][j] == already_Have[i][j - 3]) {
                        setWinner(already_Have[i][j]);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void over(int winner) {
        JFrame game_over = new JFrame();
        JLabel label = new JLabel();
        JPanel jPanel_temp = new JPanel();
        game_over.setLayout(new BorderLayout());
        if(winner == 1) {
            label.setText("the winner is " + this.username_1);
            jPanel_temp.add(label);
            game_over.add(jPanel_temp, BorderLayout.CENTER);
        } else if(winner == 2) {
            label = new JLabel("the winner is " + this.username_2);
            jPanel_temp.add(label);
            game_over.add(jPanel_temp, BorderLayout.CENTER);
        } else if(winner == 0) {
            label = new JLabel("even");
            jPanel_temp.add(label);
            game_over.add(jPanel_temp, BorderLayout.CENTER);
        }
//---------------------------------------------------------------------------
        game_over.setSize(100, 50);
        game_over.setVisible(true);
        game_over.setLocationRelativeTo(null);
        game_over.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        for(int j = 0; j < column; j++){
            buttons[j].setEnabled(false);
        }
    }
}