import java.util.Arrays;

import edu.princeton.cs.algs4.In;

public class snpAligner {
    String[][] matrix;
    
    public snpAligner(String dataFile) {
        In dataInput = new In(dataFile);
        String[] rawData = dataInput.readAllLines();
        
        int counter = 0;
        for (int i = 0; i < rawData.length; i++) {
            String s = rawData[i];
            if (s.startsWith("##") || s.startsWith("#")) {
                counter++;
            } else {
                break;
            }
        }
        
        String[] data = new String[rawData.length - counter];
        data = Arrays.copyOfRange(rawData, counter, rawData.length);
        
        int valid = 0;
        for (int i = 0; i < data.length; i++) {
            String[] split = data[i].split("\\s+");
            if (split[3].length() == 1 && split[4].length() == 1 && !split[4].equals(".")) {
                valid++;
            }
        }
        
        matrix = new String[valid][5];
        
        int matrixIndex = 0;
        for (int i = 0; i < data.length; i++) {
            String[] split = data[i].split("\\s+");
            if (split[3].length() != 1 || split[4].length() != 1 || split[4].equals(".")) {
                continue;
            }
            matrix[matrixIndex][0] = split[0];
            matrix[matrixIndex][1] = split[1];
            matrix[matrixIndex][2] = split[3];
            matrix[matrixIndex][3] = split[4];
            matrix[matrixIndex][4] = split[5];
            matrixIndex++;
        }
    }
    
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(String.format("%10s %10s %10s %10s %10s", "Chromosome", "Position", "Ref", "SNP", "Quality") + "\n");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                s.append(String.format("%10s ", matrix[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }
    
    public static void main(String[] args) {
        snpAligner aligner = new snpAligner(args[0]);
        System.out.println(aligner.toString());
    }
}
