/**
 * Created by Bradley Pham on 12/4/2017.
 */
public class Chromosome {
    private DeluxeBST<Integer,String[]> tree; //to store position with key is the site and value is nucleotide at that position
    private String[] input;//input line

    //@argument:
    //line: format Chromosome Position Reference SNPs
    //numberOfSamples: total number of SNP files being considered
    //fileIndex: order of SNP file (first, second, third, etc sample)
    public Chromosome(String[] line, int numberOfSamples, int fileIndex) {
        tree = new DeluxeBST<>(numberOfSamples);
        input = line;
        update(line, fileIndex);
    }

    //print each chromosome with all position.
    //Format: -------------------------------
    //Position     Reference     Sample1     Sample2
    public void print(int label) {
        tree.print(label);
    }

    //to update chromosome if it already existed
    public void update(String[] line, int fileIndex) {
        tree.put(Integer.parseInt(line[1]), line, fileIndex);
    }

    //to take out which chromosome this is
    public String identifier() {
        return input[0];
    }
}
