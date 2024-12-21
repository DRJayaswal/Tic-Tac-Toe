import javax.swing.JButton;

class Check {
    // Check for a win condition
    public int[][] isCheck(JButton[][] board, String player) {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if (board[i][0].getText().equals(player) && board[i][1].getText().equals(player) && board[i][2].getText().equals(player)) {
                // If all three buttons in a row match the player, return their positions
                int ans[][] = {
                    {i, 0},
                    {i, 1},
                    {i, 2}
                };
                return ans;
            }
            if (board[0][i].getText().equals(player) && board[1][i].getText().equals(player) && board[2][i].getText().equals(player)) {
                // If all three buttons in a column match the player, return their positions
                int ans[][] = {
                    {0, i},
                    {1, i},
                    {2, i}
                };
                return ans;
            }
        }
        // Check diagonals
        if (board[0][0].getText().equals(player) && board[1][1].getText().equals(player) && board[2][2].getText().equals(player)) {
            // If all three buttons in the main diagonal match the player, return their positions
            int ans[][] = {
                {0, 0},
                {1, 1},
                {2, 2}
            };
            return ans;
        }
        if (board[0][2].getText().equals(player) && board[1][1].getText().equals(player) && board[2][0].getText().equals(player)) {
            // If all three buttons in the anti-diagonal match the player, return their positions
            int ans[][] = {
                {0, 2},
                {1, 1},
                {2, 0}
            };
            return ans;
        }
        // If no win condition is met, return {-1, -1}
        int ans[][] = {
            {-1, -1},
        };
        return ans;
    }

    // Check for a draw condition
    public boolean isCheckDraw(JButton[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j].getText().equals("")) {
                    // If any button is empty, it's not a draw
                    return false;
                }
            }
        }
        // If no buttons are empty, it's a draw
        return true;
    }
}