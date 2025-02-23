import java.util.ArrayList;
public class Solver {
    private int boardRow;
    private int boardCol;
    private String[][] board;
    private ArrayList<Block> pieces;
    private int count;
    
    public Solver(ArrayList<Block> blockPieces, int row, int col) {
        boardRow = row;
        boardCol = col;
        board = new String[boardRow][boardCol];
        pieces = new ArrayList<>(blockPieces);
        count = 0;
        makeBoard();
    }
    
    private void makeBoard() {
        for (int i = 0; i < boardRow; i++) {
            for (int j = 0; j < boardCol; j++) {
                board[i][j] = "-";
            }
        }
    }
    
    public boolean solve() {
        if (pieces.isEmpty()) {
            return boardFull();
        }
        
        Block currentPiece = pieces.remove(0);
        ArrayList<Block> orientations = getAllOrientations(currentPiece);
        
        for (Block orientation : orientations) {
            for (int row = 0; row < boardRow; row++) {
                for (int col = 0; col < boardCol; col++) {
                    if (canPlacePiece(orientation, row, col)) {
                        count++;
                        String pieceLetter = getPieceLetter(orientation);
                        putPiece(orientation, row, col, pieceLetter);
                        
                        if (solve()) {
                            return true;
                        }
                        
                        removePiece(orientation, row, col);
                    }
                }
            }
        }
        pieces.add(0, currentPiece);
        return false;
    }
    
    private String getPieceLetter(Block piece) {
        for (int i = 0; i < piece.size; i++) {
            for (int j = 0; j < piece.size; j++) {
                if (!piece.block[i][j].equals("-")) {
                    return piece.block[i][j];
                }
            }
        }
        return "-";
    }
    
    private boolean canPlacePiece(Block piece, int row, int col) {
        for (int i = 0; i < piece.size; i++) {
            for (int j = 0; j < piece.size; j++) {
                if (!piece.block[i][j].equals("-")) {
                    if (row + i >= boardRow || col + j >= boardCol) {
                        return false;
                    }
                    if (!board[row + i][col + j].equals("-")) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    private void putPiece(Block piece, int row, int col, String id) {
        for (int i = 0; i < piece.size; i++) {
            for (int j = 0; j < piece.size; j++) {
                if (!piece.block[i][j].equals("-")) {
                    board[row + i][col + j] = id;
                }
            }
        }
    }
    
    private void removePiece(Block piece, int row, int col) {
        for (int i = 0; i < piece.size; i++) {
            for (int j = 0; j < piece.size; j++) {
                if (!piece.block[i][j].equals("-")) {
                    board[row + i][col + j] = "-";
                }
            }
        }
    }
    
    private boolean boardFull() {
        for (int i = 0; i < boardRow; i++) {
            for (int j = 0; j < boardCol; j++) {
                if (board[i][j].equals("-")) {
                    return false;
                }
            }
        }
        return true;
    }
    
    private ArrayList<Block> getAllOrientations(Block piece) {
        ArrayList<Block> orientations = new ArrayList<>();
        
        Block current = new Block();
        current.createBlock(piece.size);
        copyBlock(piece, current);
        
        for (int i = 0; i < 4; i++) {
            Block newOrientation = new Block();
            newOrientation.createBlock(current.size);
            copyBlock(current, newOrientation);
            orientations.add(newOrientation);
            
            Block rotated = new Block();
            rotated.createBlock(current.size);
            copyBlock(current, rotated);
            rotated.rotate(rotated);
            current = rotated;
        }
        
        Block mirrored = piece.mirrorX(piece);
        current = new Block();
        current.createBlock(mirrored.size);
        copyBlock(mirrored, current);
        
        for (int i = 0; i < 4; i++) {
            Block newOrientation = new Block();
            newOrientation.createBlock(current.size);
            copyBlock(current, newOrientation);
            orientations.add(newOrientation);
            
            Block rotated = new Block();
            rotated.createBlock(current.size);
            copyBlock(current, rotated);
            rotated.rotate(rotated);
            current = rotated;
        }
        
        return orientations;
    }
    
    private void copyBlock(Block b1, Block b2) {
        for (int i = 0; i < b1.size; i++) {
            for (int j = 0; j < b1.size; j++) {
                b2.block[i][j] = b1.block[i][j];
            }
        }
    }
    
    public void printBoard() {
        for (int i = 0; i < boardRow; i++) {
            for (int j = 0; j < boardCol; j++) {
                String pieceLetter = board[i][j];
                System.out.print(getColorForLetter(pieceLetter) + pieceLetter + " \033[0m");
            }
            System.out.println();
        }
    }

    public String[][] getBoard() {
        return board;
    }

    public int getCount() {
        return count;
    }

    private String getColorForLetter(String pieceLetter) {
        switch (pieceLetter) {
            case "A": return "\033[31m";  // red
            case "B": return "\033[32m";  // green
            case "C": return "\033[33m";  // yellow
            case "D": return "\033[34m";  // blue
            case "E": return "\033[35m";  // magenta
            case "F": return "\033[36m";  // cyan
            case "G": return "\033[90m";  // gray
            case "H": return "\033[38;5;214m";  // LIGHT red
            case "I": return "\033[92m";  // bright green
            case "J": return "\033[38;5;117m";  // sky blue
            case "K": return "\033[38;5;32m";  // medium Blue
            case "L": return "\033[38;5;201m";  // fuschia
            case "M": return "\033[96m";  // bright cyan
            case "N": return "\033[97m";  // bright white
            case "O": return "\033[38;5;208m"; // orange
            case "P": return "\033[38;5;125m"; // pink
            case "Q": return "\033[38;5;36m";  // sea green
            case "R": return "\033[38;5;226m"; // gold
            case "S": return "\033[38;5;82m";  // lime green
            case "T": return "\033[38;5;51m";  // teal
            case "U": return "\033[38;5;220m"; // peach
            case "V": return "\033[38;5;213m"; // orchid
            case "W": return "\033[38;5;56m";  // dark blue
            case "X": return "\033[38;5;220m"; // amber
            case "Y": return "\033[38;5;58m"; // light yellow
            case "Z": return "\033[38;5;160m"; // brick red
            default: return "\033[0m";  // Default color (reset)
        }
    }


}