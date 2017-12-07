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
        tree = new DeluxeBST<>(5);
        numOfSample = sample;
        input = line;
        this.fileIndex = fileIndex;
    }

    //print each chromosome with all position.
    //Format: -------------------------------
    //Position    Sample1     Sample2     Reference
    public void print() {
        tree.print();
    }

    public void update() {
        tree.put(Integer.parseInt(input[1]), input, fileIndex);
    }
    private void process() {
        //may not have the reference yet, need to create an array to put into the tree
        /*String[] val = tree.get(Integer.parseInt(input[1]));
                val[fileIndex] = input[3];
                tree.put(Integer.parseInt(input[1]), val);
*/

    }

}
