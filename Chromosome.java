/**
 * Created by Bradley Pham on 12/4/2017.
 */
public class Chromosome {

    private DeluxeBST<Integer,String[]> tree; //to store position with key is the site and value is nucleotide at that position
    private int numOfSample;//to initialise array value for tree
    private String[][] matrixOutput;//to output
    private String[] input;//input line
    private int fileIndex;

    public void Chromosome(String[] line, int sample, int fileIndex) {
        numOfSample = sample;
        tree = new DeluxeBST<>(numOfSample);
        input = line;
        this.fileIndex = fileIndex;
    }

    //print each chromosome with all position.
    //Format: -------------------------------
    //Position    Sample1     Sample2     Reference
    public void print() {
        tree.print();
    }

    //to update chromosome if it already existed
    public void update() {
        tree.put(Integer.parseInt(input[1]), input, fileIndex);
    }


}
