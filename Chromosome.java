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
    //line: format Chromosome Position Reference SNPs
    //numberOfSample: total number of SNPs files being considered
    //fileIndex: order of SNP file (first, second, third, etc sample)
    public Chromosome(String[] line, int numberOfSample, int fileIndex) {
        this.numOfSample = numberOfSample;
        tree = new DeluxeBST<>(numOfSample);
        input = line;
        this.fileIndex = fileIndex;
        update(line, numberOfSample, fileIndex);
    }

    //print each chromosome with all position.
    //Format: -------------------------------
    //Position     Reference     Sample1     Sample2
    public void print() {
        tree.print();
    }

    //to update chromosome if it already existed
    public void update(String[] line, int numberOfSample, int fileIndex) {
        tree.put(Integer.parseInt(line[1]), line, fileIndex);
    }

    //to take out which chromosome this is
    public String position() {
        return input[0];
    }

    //compare two chromosome. They are equal iff their positions are the same
    public boolean equals(Chromosome that) {
        return that.position().equals(this.input[0]);
    }
}
