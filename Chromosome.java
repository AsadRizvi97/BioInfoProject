/**
 * Created by Bradley Pham on 12/4/2017.
 */
public class Chromosome {

    private DeluxeBST<Integer,String[]> tree; //to store position with key is the site and value is nucleotide at that position
    private int numOfSample;//to initialise array value for tree
    private String[][] matrixOutput;//to output
    private String[] input;//input line
    private int fileIndex;

    //@argument:
    //line: format Chromosome Position Reference SNPS
    //numberOfSample: total number of SNPs files being considered
    //fileIndex: order of SNP file (first, second, third, etc sample)
    public Chromosome(String[] line, int numberOfSample, int fileIndex) {
        this.numOfSample = numberOfSample;
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
