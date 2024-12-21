import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class TicTacToe {
    int boardWidth = 600; // Width of the game board
    int boardHeight = 650; // Height of the game board

    JFrame frame = new JFrame(); // Main frame for the game
    JLabel label = new JLabel(); // Label to display game status
    JPanel panel = new JPanel(); // Panel to hold the label and restart button
    JPanel boardPanel = new JPanel(); // Panel to hold the game board (buttons)
    JButton[][] board = new JButton[3][3]; // 2D array of buttons representing the game board
    String XPlayer = "X"; // String representing player X
    String OPlayer = "O"; // String representing player O
    String CPlayer = XPlayer; // Current player, starting with X
    boolean gameOn = true; // Flag to indicate if the game is ongoing

    public void resetGame() {
        // Reset the game board and status
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                board[r][c].setText(""); // Clear the text on each button
                board[r][c].setForeground(Color.black); // Reset the text color
                board[r][c].setEnabled(true); // Enable the button
            }
        }
        CPlayer = XPlayer; // Reset current player to X
        gameOn = true; // Set game status to ongoing
        label.setText("TIC TAC TOE"); // Reset the label text
    }

    public void gameOver(int[][] winPos) {
        // Highlight the winning positions or indicate a draw
        if (winPos[0][0] != -1) {
            for (int[] pos : winPos) {
                board[pos[0]][pos[1]].setForeground(Color.green); // Highlight winning positions
            }
        } else {
            for (int r = 0; r < board.length; r++) {
                for (int c = 0; c < board[r].length; c++) {
                    board[r][c].setForeground(Color.ORANGE); // Highlight all positions for a draw
                }
            }
        }
    }

    TicTacToe() {
        frame.setVisible(true); // Make the frame visible

        frame.setSize(boardWidth, boardHeight); // Set the size of the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set default close operation
        frame.setTitle("Tic Tac Toe"); // Set the title of the frame
        frame.setResizable(false); // Prevent resizing of the frame
        frame.setLocationRelativeTo(null); // Center the frame on the screen
        frame.setLayout(new BorderLayout()); // Set the layout manager for the frame

        label.setBackground(Color.black); // Set the background color of the label
        label.setForeground(Color.white); // Set the text color of the label
        label.setFont(new Font("Arial", Font.BOLD, 50)); // Set the font of the label
        label.setHorizontalAlignment(JLabel.CENTER); // Center align the text in the label
        label.setText("TIC TAC TOE"); // Set the initial text of the label
        label.setOpaque(true); // Make the label opaque

        panel.setLayout(new BorderLayout()); // Set the layout manager for the panel
        panel.add(label, BorderLayout.NORTH); // Add the label to the top of the panel

        JButton restartButton = new JButton("Restart"); // Create a restart button
        restartButton.setBackground(Color.white); // Set the background color of the button
        restartButton.setForeground(Color.black); // Set the text color of the button
        restartButton.setFont(new Font("Arial", Font.BOLD, 25)); // Set the font of the button
        restartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetGame(); // Reset the game when the button is clicked
            }
        });
        panel.add(restartButton, BorderLayout.SOUTH); // Add the button to the bottom of the panel

        frame.add(panel, BorderLayout.NORTH); // Add the panel to the top of the frame

        boardPanel.setLayout(new GridLayout(3, 3)); // Set the layout manager for the board panel
        boardPanel.setBackground(Color.black); // Set the background color of the board panel
        frame.add(boardPanel); // Add the board panel to the frame

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                JButton tile = new JButton(); // Create a new button for each tile
                board[r][c] = tile; // Add the button to the board array
                boardPanel.add(tile); // Add the button to the board panel

                tile.setForeground(Color.black); // Set the text color of the button
                tile.setFont(new Font("Arial", Font.BOLD, 120)); // Set the font of the button
                tile.setFocusable(false); // Disable focus for the button

                tile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (gameOn) { // Check if the game is ongoing
                            JButton tile = (JButton) e.getSource(); // Get the source of the event
                            if (tile.getText().equals("")) { // Check if the tile is empty
                                tile.setText(CPlayer); // Set the text of the tile to the current player
                                if (CPlayer.equals(XPlayer)) { // Check if the current player is X
                                    label.setText(OPlayer + " Turn"); // Set the label text to O's turn
                                    CPlayer = OPlayer; // Switch to player O
                                } else {
                                    label.setText(XPlayer + " Turn"); // Set the label text to X's turn
                                    CPlayer = XPlayer; // Switch to player X
                                }
                                Check check = new Check(); // Create a new Check object
                                int[][] winPos = check.isCheck(board, XPlayer); // Check if X wins
                                if (winPos[0][0] != -1) {
                                    label.setText(XPlayer + " Wins"); // Set the label text to X wins
                                    gameOn = false; // Set game status to over
                                    gameOver(winPos); // Highlight the winning positions
                                } else {
                                    winPos = check.isCheck(board, OPlayer); // Check if O wins
                                    if (winPos[0][0] != -1) {
                                        label.setText(OPlayer + " Wins"); // Set the label text to O wins
                                        gameOn = false; // Set game status to over
                                        gameOver(winPos); // Highlight the winning positions
                                    } else if (check.isCheckDraw(board)) {
                                        label.setText("Draw"); // Set the label text to draw
                                        gameOn = false; // Set game status to over
                                        gameOver(new int[][] { { -1, -1 } }); // Highlight all positions for a draw
                                    }
                                }
                            }
                        }
                    }
                });
            }
        }
    }
}