import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.err.println("IQ Puzzler Pro");

        ArrayList<String> fileContent = IO.readFile();
        
        if (fileContent.isEmpty()) {
            System.out.println("File kosong atau tidak ditemukan.");
        } else {
            String[] firstLIne = fileContent.get(0).split(" ");
            int row = Integer.parseInt(firstLIne[0]);
            int col = Integer.parseInt(firstLIne[1]);
            int pieces = Integer.parseInt(firstLIne[2]);
            String mode = fileContent.get(1);

            fileContent.remove(0);
            fileContent.remove(0);
        
            ArrayList<Block> blockPieces = IO.proccessBlock(fileContent);
            
            long startTime = System.nanoTime();
            Solver solver = new Solver(blockPieces, row, col);
            boolean found = solver.solve();
            long endTime = System.nanoTime();
            long time = (endTime - startTime)/1000000;
            
            if (found) {
                System.out.println("Solusi ditemukan!");
                System.out.println("Waktu pencarian: " + time + " ms.");
                int count = solver.getCount();
                System.out.println("Banyak kasus yang ditinjau: " + count);
                solver.printBoard();
                Scanner scanner = new Scanner(System.in);
                System.out.print("Apakah Anda ingin menyimpan solusi? (ya/tidak)");
                String save = scanner.nextLine().trim().toLowerCase();
                
                if (save.equals("ya")) {
                    String[][] board = solver.getBoard();
                    IO.writeFile(board);
                }
            } else {
                System.out.println("Tidak ada solusi.");
                System.out.println("Waktu pencarian: " + time + " ms.");
                int count = solver.getCount();
                System.out.println("Banyak kasus yang ditinjau: " + count);
            }
        
        }
    }
}