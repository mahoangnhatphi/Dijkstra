import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author Phi
 */

class Dijktra {

    /**
     * @param args the command line arguments
     */
    int g[][];
    int size;
    int begin, end;

    void loadDate(String filename) {

        try {
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);
            String s = br.readLine();
            Scanner sc = new Scanner(s);
            size = sc.nextInt();
            begin = sc.nextInt();
            end = sc.nextInt();
            g = new int[size][size];
            int i = 0;
            while ((s = br.readLine().trim()) != null) {
                String arr[] = s.split(" ");
                for (int j = 0; j < arr.length; j++) {
                    g[i][j] = Integer.parseInt(arr[j].trim());
                }
                i++;
            }
            br.close();
            fr.close();
        } catch (Exception e) {
        }
    }

    String trace(int pre[]) {
        int i = end - 1;
        StringBuilder sb = new StringBuilder();
        Stack<Integer> s = new Stack<>();
        while (i != -1) {
            s.push(i);
            i = pre[i];
        }
        while (!s.isEmpty()) {
            int value = s.pop();
            sb.append((value + 1) + (s.isEmpty() == true ? "" : "->"));
        }
        return sb + "";
    }

    int dijktra() {
        int lens[] = new int[size];
        boolean isOptimized[] = new boolean[size];
        int pre[] = new int[size];
        for (int i = 0; i < size; i++) {
            lens[i] = Integer.MAX_VALUE;
            isOptimized[i] = false;
            pre[i] = -1;
        }

        int vBegin = begin - 1;
        int vEnd = end - 1;
        lens[vBegin] = 0;
        while (!isOptimized[vEnd]) {
            int i;
            for (i = 0; i < size; i++) {
                if (isOptimized[i] == false && lens[i] < Integer.MAX_VALUE) {
                    break;
                }
            }
            if (i >= size) {
                return -1;
            }
            for (int j = 0; j < size; j++) {
                if (isOptimized[j] == false && lens[j] < lens[i]) {
                    i = j;
                }
            }
            
            isOptimized[i] = true;

            for (int j = 0; j < size; j++) {
                if (isOptimized[j] == false && g[i][j] != 0 && lens[j] > lens[i] + g[i][j]) {
                    lens[j] = lens[i] + g[i][j];
                    pre[j] = i;
                }
            }
        }
        System.out.println("Must be come to " + trace(pre) + " to has the shortest path");
        return lens[vEnd];
    }

    void solveDijktra() {
        loadDate("input.txt");
        System.out.println("Vectex: " + size + ", begin: " + begin + ", end: " + end);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(g[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("Must be for it: " + dijktra());
    }

    public static void main(String[] args) {
        new Dijktra().solveDijktra();
    }

}