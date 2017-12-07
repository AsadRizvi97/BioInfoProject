import java.util.LinkedList;

/**
 * Created by Bradley Pham on 12/4/2017.
 */
public class DeluxeBST<Key extends Comparable<Key>, Object> {

    private class Node {
        private Node right;
        private Node left;
        private Object val;
        private Key keyNode;
        private boolean color;
        public Node(Key key, Object value, boolean col) {
            keyNode = key;
            val = value;
            color = col;
        }
    }
    private static final boolean RED = true;
    private Node root; //the root of the tree
    private int numOfSample; //to store the total number of samples

    public DeluxeBST(int numOfSamp) {
        this.numOfSample = numOfSamp;
    }

    public void put(Key key, Object value, int index) {
        //need to check the score to put in the tree
        if (!(value instanceof String[])) {
            throw new IllegalArgumentException();
        }
        root = put(root, key, value, index);
    }

    private Node put(Node h, Key key, Object val, int indexFile) {
        if (h == null) {
            if (val instanceof String[]) {
                //create new String array for that position node
                String[] temp = ((String[]) val);
                String[] valArray = new String[numOfSample + 2];
                valArray[0] = temp[1]; //copy the position
                valArray[numOfSample + 1] = temp[3]; //copy the reference
                valArray[indexFile] = temp[2];
                return new Node(key, (Object) valArray, RED);
            } else {
                throw new IllegalArgumentException();
            }
        }
        int cmp = key.compareTo(h.keyNode);
        if (cmp < 0) h.left = put(h.left, key, val, indexFile);
        else if (cmp > 0) h.right = put(h.right, key, val, indexFile);
        else {
            if (h.val instanceof String[]) {
                String[] temp = ((String[]) h.val);
                String[] temp2 = ((String[]) val);
                temp[indexFile] = temp2[2];

            } else {
                //we can modify the code in case we need to use BST for different type
                throw new IllegalArgumentException();
            }
        }
        if (isRed(h.right) && !isRed(h.left))      h = rotateLeft(h);
        if (isRed(h.left)  &&  isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left)  &&  isRed(h.right))     flipColors(h);
        return h;
    }
    private boolean isRed(Node x) {
        if (x == null) return false;
        return x.color == RED;
    }
    private Node rotateRight(Node h) {
        // assert (h != null) && isRed(h.left);
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = x.right.color;
        x.right.color = RED;
        return x;
    }
    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = x.left.color;
        x.left.color = RED;
        return x;
    }
    private void flipColors(Node h) {
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }

    //print out string with format: position
    public void print() {
        Node t = root;
        LinkedList<Object> allNode = new LinkedList<>();
        putToList(t, allNode);
        while (!allNode.isEmpty()) {
            String[] temp = (String[])allNode.removeFirst();
            for (int i = 0; i < temp.length; i++) {
                if (temp[i] == null) {
                    System.out.printf("%10s", temp[numOfSample + 1]);
                } else {
                    System.out.printf("%10s", temp[i]);
                }
            }
            System.out.println();
        }

    }
    private void putToList(Node h, LinkedList<Object> output) {
        if (h == null)
            return;
        putToList(h.left, output);
        output.addLast(h.val);
        putToList(h.right, output);
    }

    //testing
    public static void main(String[] args) {
        //Check condition
       /* Chromosome a = new Chromosome();
        String[] b = new String[3];

        System.out.println(b.getClass());
        if (b instanceof String[]) {
            System.out.println("True");
        }
        if (a instanceof Chromosome) {
            System.out.println("Also true");
        }*/
        //test tree
        String[] array1 = {"1", "25", "G", "A"};
        String[] array2 = {"2", "30", "G", "T"};
        String[] array3 = {"3", "45", "G", "A"};
        String[] array4 = {"4", "75", "C", "A"};
        String[] array5 = {"5", "100", "G", "T"};
        String[] array6 = {"1", "25", "C", "A"};
//        int[] array7 = {1, 3, 4};
        DeluxeBST<Integer, String[]> testingTree = new DeluxeBST<>(5);
        testingTree.put(25, array1, 1);
        testingTree.put(30, array2, 2);
        testingTree.put(45, array3,3);
        testingTree.put(75, array4, 4);
        testingTree.put(100, array5, 5);
        testingTree.put(25, array6, 3);
//        testingTree.put(105, array7, 2);
        System.out.printf("%10s", "Position");
        for (int i = 0; i < 5; i++) {
            System.out.printf("%9s%d", "Sample ", i+1);
        }
        System.out.printf("%10s\n", "Reference");
        testingTree.print();
    }
}
