import java.util.*;

public class Main{
    public static void main(String[] args) {
        ArrayList<String> fileContent = IO.readFile();
        
        if (fileContent.isEmpty()) {
            System.out.println("File kosong atau tidak ditemukan.");
        } else {
            System.out.println("Konten file:");
            System.out.println(fileContent);
            for (String line : fileContent) {
                System.out.println(line);
            }
        }
    }
}