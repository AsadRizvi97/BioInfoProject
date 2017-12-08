import java.util.LinkedList;
import edu.princeton.cs.algs4.In;

public class Main {
    private LinkedList<Chromosome> chromosomes; //Store chromosomes as linked list
    private int numberOfSamples; //Store number of samples for print()

    public Main(String[] dataFiles) {
        LinkedList<String> identifiers = new LinkedList<>(); //Create linked list of identifiers to see if chromosome exists already
        chromosomes = new LinkedList<>();
        numberOfSamples = dataFiles.length; //Set number of samples to number of data files inputted
        for (int i = 0; i < dataFiles.length; i++) { //For each data file, convert each line to Chromosome | Position | Reference | SNP
            In dataInput = new In(dataFiles[i]);
            while (!dataInput.isEmpty()) {
                String[] split = dataInput.readLine().split("\\s+");
                if (split[0].startsWith("#") || split[3].length() != 1 || split[3].equals(".") || split[4].length() != 1 || split[4].equals(".")) {
                    continue;
                }
                String[] line = {split[0], split[1], split[3], split[4]};
                if (identifiers.contains(split[0])) { //If chromosome already exists, just update the old one already there
                    Chromosome chromosome = chromosomes.get(identifiers.indexOf(split[0]));
                    chromosome.update(line, i + 1);
                } else { //If no chromosome exists, create it
                    Chromosome chromosome = new Chromosome(line, dataFiles.length, i + 1);
                    chromosomes.add(chromosome);
                    identifiers.add(split[0]);
                }
            }
        }
    }
    
    public void print() {
        for (Chromosome chromosome : chromosomes) { //For each chromosome, print Position | Reference | Sample 1 | Sample 2 etc.
            System.out.printf("%-12s%-12s\n", "Chromosome",  chromosome.identifier());
            System.out.printf("%-12s%-12s", "Position", "Reference");
            for (int i = 0; i < numberOfSamples; i++) {
                System.out.printf("%-6s %-5d", "Sample", i + 1);
            }
            System.out.println();
            chromosome.print();
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        Main aligner = new Main(args);
        aligner.print();
    }
}
