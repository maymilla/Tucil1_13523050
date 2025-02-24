import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.err.println("-+-+-+-+-+-+-+-+-+-+-+-+-+-");
        System.err.println("  IQ Puzzler Pro Solver  ");
        System.err.println("-+-+-+-+-+-+-+-+-+-+-+-+-+-");

        ArrayList<String> fileContent = IO.readFile();
        
        if (fileContent.isEmpty()) {
            System.out.println("File kosong atau tidak ditemukan.");
        } else {
            String[] firstLine = fileContent.get(0).split(" ");
            int row, col, pieces;
            try {
                row = Integer.parseInt(firstLine[0]);
                col = Integer.parseInt(firstLine[1]);
                pieces = Integer.parseInt(firstLine[2]);

                if (row <= 0 || col <= 0) {
                    System.out.println("Jumlah baris dan kolom harus bilangan bulat positif.");
                    return;
                }

                if (pieces <= 0 || pieces > 26) {
                    System.out.println("Jumlah potongan block harus di antara 1 dan 26.");
                    return;
                }

            } catch (NumberFormatException e) {
                System.out.println("Input format salah, pastikan jumlah baris, kolom, dan potongan block adalah bilangan bulat.");
                return;
            }

            try {
                String mode = fileContent.get(1);
                if (!mode.equals("DEFAULT")){
                    System.err.println("Mode harus DEFAULT.");
                    return;
                }
            } catch (Exception e) {

            }

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