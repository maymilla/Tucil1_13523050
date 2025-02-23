
import java.util.ArrayList;

public class Block {

    public String block[][];
    public int size;

    public void toBlock(ArrayList a){

    }

    public void createBlock(int size){
        String a[][];
        a = new String[size][size];

        int i, j;
        for(i = 0; i < size; i++){
            for(j = 0; j < size; j++){
                a[i][j] = "-";
            }
        }

        this.block = a;
        this.size = size;
    }

    public int getBlockSize(){
        return this.size;
    }

    public void rotate(Block b){
        int l = 0;
        int r = b.size - 1;
        int i;

        while (l < r){
            for(i = 0; i < r-l; i++){
                int top = l;
                int bottom = r;

                String topLeft = b.block[top][l+i];

                b.block[top][l+i] = b.block[bottom-i][l];
                b.block[bottom-i][l] = b.block[bottom][r-i];
                b.block[bottom][r-i] = b.block[top+i][r];
                b.block[top+i][r] = topLeft;
            }
            r -= 1;
            l += 1;
        }
    }

   public Block mirrorY(Block b){
        Block hasil = new Block();
        hasil.createBlock(b.size);
        int i, j;

        for(i=0; i<b.size; i++){
            for(j=0; j<b.size; j++){
                hasil.block[i][b.size-1-j] = b.block[i][j];
            }
        }
        return hasil;
   }

   public Block mirrorX(Block b){
    Block hasil = new Block();
        hasil.createBlock(b.size);
        int i, j;

        for(i=0; i<b.size; i++){
            for(j=0; j<b.size; j++){
                hasil.block[b.size-1-i][j] = b.block[i][j];
            }
        }
        return hasil;
   }

   public void fillBlock(ArrayList<String> pattern) {
        int max = 0;
        
        for (int i = 2; i < pattern.size(); i++) {
            max = Math.max(max, pattern.get(i).length());
        }
        
        createBlock(max);
        
        for (int i = 0; i < pattern.size() - 2 && i < size; i++) {
            String line = pattern.get(i + 2);
            for (int j = 0; j < line.length() && j < size; j++) {
                block[i][j] = String.valueOf(line.charAt(j));
            }
        }
    }


    public void printBlock() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(block[i][j] + " ");
            }
            System.out.println();
        }
    }

}
