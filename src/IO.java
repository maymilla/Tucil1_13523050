import java.io.*;
import java.util.*;


public class IO {
    public static Scanner scanner = new Scanner(System.in);
    public static BufferedReader inputFile = new BufferedReader(new InputStreamReader(System.in));

    public static ArrayList<String> readFile() {
        System.out.print("Masukkan nama file: ");
        String fileName = scanner.nextLine();
        
        String path = "Tucil1_13523050" + File.separator + "test" + File.separator + "Input" + File.separator + fileName;
        
        File file = new File(path);
        System.out.println("Trying path: " + file.getAbsolutePath());
        
        if (file.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                ArrayList<String> input = new ArrayList<>();
                String line;
                
                while ((line = br.readLine()) != null) {
                    input.add(line);
                }
                
                if (input.isEmpty()) {
                    System.out.println("File kosong");
                    return new ArrayList<>();
                }
                
                return input;
            } catch (IOException e) {
                System.out.println("Error reading file: " + e.getMessage());
            }
        }
        
        System.out.println("File tidak ditemukan.");
        return new ArrayList<>();
    }

    public static ArrayList<Block> proccessBlock(ArrayList<String> fileContent) {
        ArrayList<ArrayList<String>> blocksArray = new ArrayList<>();
        ArrayList<String> currentBlock = new ArrayList<>();

        char currentLetter = ' ';

        for (String line : fileContent) {

            char firstChar = line.charAt(0);

            if (firstChar != currentLetter) {
                if (!currentBlock.isEmpty()) {
                    blocksArray.add(new ArrayList<>(currentBlock));
                    currentBlock.clear();
                }
                currentLetter = firstChar;
            }

            currentBlock.add(line);
        }

        if (!currentBlock.isEmpty()) {
            blocksArray.add(new ArrayList<>(currentBlock));
        }

        ArrayList<Block> blocks = new ArrayList<>();
        for (ArrayList<String> blockLines : blocksArray) {
            if (!blockLines.isEmpty()) {
                int maxSize = 0;
                for (String line : blockLines) {
                    maxSize = Math.max(maxSize, line.length());
                }
                maxSize = Math.max(maxSize, blockLines.size());

                Block block = new Block();
                block.createBlock(maxSize);

                for (int i = 0; i < blockLines.size(); i++) {
                    String line = blockLines.get(i);
                    for (int j = 0; j < line.length(); j++) {
                        block.block[i][j] = String.valueOf(line.charAt(j));
                    }
                }

                blocks.add(block);
                System.out.println("Created piece:");
                block.printBlock();
                System.out.println();
            }
        }

        return blocks;
    }

    public static void writeFile(String[][] board) {
            String nameFile = "";
            System.out.println("Current working directory: " + System.getProperty("user.dir"));

            System.out.println("Masukkan nama file: ");
            try {
                nameFile = inputFile.readLine();
                String path = "Tucil1_13523050/test/Output/" + nameFile;

                File file = new File(path);
                if (file.exists()) {
                    System.out.println("File sudah ada. Apakah Anda ingin menimpanya? (y/n)");
                    char choice = scanner.next().charAt(0);
                    if (choice != 'y' && choice != 'Y') {
                        System.out.println("Output dibatalkan.");
                        scanner.close();
                        return; 
                    }
                }
                
            } catch (IOException err) {
                err.printStackTrace();
            }

            try {
                FileWriter file = new FileWriter("Tucil1_13523050/test/Output/" + nameFile);
                int i, j;
                for(i = 0; i < board.length; i++){
                    for(j = 0; j < board[0].length; j++){
                        file.write(board[i][j] + ' ');
                    }
                    file.write("\n");
                }
                file.close();
                System.out.println("File berhasil dibuat!");
            } catch (IOException err) {
                err.printStackTrace();
            }
            
    }
}