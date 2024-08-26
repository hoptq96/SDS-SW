import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CycleIndex {

    static Scanner sc;
    static ArrayList[] nodes;

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("./src/CycleIndex.txt"));
        sc = new Scanner(System.in);
        int tc = sc.nextInt();
        for (int i = 1; i <= tc; i++) {
            init();
        }
    }

    private static void init() {
        int totalNode = sc.nextInt();
    }
}
