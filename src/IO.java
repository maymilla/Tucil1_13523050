import java.io.*;
import java.util.*;

public class IO {
    public static Scanner scanner = new Scanner(System.in);
    public static ArrayList readFile() {
        System.out.print("Masukkan nama file: ");
        String file = scanner.nextLine();
        String path = String.format("Tucil1_13523050" + File.separator + "test" + File.separator + "Input" + File.separator + file);
                    
        System.out.println("Membaca dari: " + path);
        System.out.println("Full path: " + new File(path).getAbsolutePath());

        
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)))) {
            String firstLine = br.readLine();
            if (firstLine == null) {
                throw new IOException("File kosong");
            }     
             
            ArrayList<String> input = new ArrayList<>();
            Scanner lineScanner = new Scanner(firstLine);

            int i;
            for (i = 0; i < 3; i++){
                String angka = lineScanner.next();
                input.add(angka);
            }
            
            String line;
            while ((line = br.readLine()) != null) {
                input.add(line);
            }
            
            return input;
            
        } catch (IOException ex) {
            System.out.println("File tidak ditemukan.");
            ArrayList<String> input = new ArrayList<>();
            return input;
        }
    }

}