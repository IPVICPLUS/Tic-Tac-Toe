import javax.swing.*;
import java.awt.*;


public class Main{
    private JFrame frame;
    private JButton[] buttons = new JButton[9];
    private boolean xTurn = true;
    private JLabel statusLabel;

    public Main(){
        frame = new JFrame("Tic Tac Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setLayout(new BorderLayout());

        statusLabel = new JLabel("X Starts", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 24));
        frame.add(statusLabel, BorderLayout.NORTH);

        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(3, 3));

        for (int i = 0; i< 9; i++){
            buttons[i] = new JButton("");
            buttons[i].setFont(new Font("Arial", Font.BOLD, 60));
            buttons[i].setFocusPainted(false);

            int index = i;
            buttons[i].addActionListener(e -> handleMove(index));

            boardPanel.add(buttons[i]);

        }
        frame.add(boardPanel, BorderLayout.CENTER);

        JButton resetButton = new JButton("Reset");
        resetButton.setFont(new Font("Arial", Font.PLAIN, 20));
        resetButton.addActionListener(e -> resetGame());

        frame.add(resetButton, BorderLayout.SOUTH);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void handleMove(int index){
        if(!buttons[index].getText().equals("")){
            return;
        }
        if(xTurn){
            buttons[index].setText("X");
            statusLabel.setText("O:s Turn");
        }else{
            buttons[index].setText("O");
            statusLabel.setText("X:s turn");
        }

        if(checkWinner()){
            String winner = xTurn ? "X" : "O";
            statusLabel.setText(winner + " won!");
            disableButtons();
            return;
        }
        if(isDraw()){
            statusLabel.setText("Draw!");
            return;
        }

        xTurn = !xTurn;
    }
    private boolean checkWinner(){
        int[][] winPatterns = {
                {0, 1, 2},
                {3, 4, 5},
                {6, 7, 8},
                {0, 3, 6},
                {1, 4, 7},
                {2, 5, 8},
                {0, 4, 8},
                {2, 4, 6}
        };
        for(int[] pattern: winPatterns){
            String a = buttons[pattern[0]].getText();
            String b = buttons[pattern[1]].getText();
            String c = buttons[pattern[2]].getText();

            if(!a.equals("") && a.equals(b) && b.equals(c)){
                return  true;
            }
        }
        return false;
    }
    private boolean isDraw(){
        for(JButton button : buttons){
            if(button.getText().equals("")){
                return false;
            }
        }
        return true;
    }
    private void disableButtons(){
        for(JButton button : buttons){
            button.setEnabled(false);
        }
    }
    private void resetGame(){
        for(JButton button : buttons){
            button.setText("");
            button.setEnabled(true);
        }
        xTurn = true;
        statusLabel.setText("X starts");
    }
    public static void main(String[] args){
        new Main();
    }
}

