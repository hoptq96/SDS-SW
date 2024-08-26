import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class InheritingLand {

    static int row = 2, col;
    static int[][] map;
    static Scanner sc;
    static int finalA, finalB, mid;

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("./src/InheritingLand.txt"));
        sc = new Scanner(System.in);
        int tc = sc.nextInt();
        for (int i = 1; i <= tc; i++) {
            read();
            devideLand();
            print(i);

            finalA = 0;
            finalB = 0;
        }
    }

    static void devideLand() {
        int A = 0, B = 0, tempA, tempB;
        for (int i = 0; i < col - 1; i++) {
            A += map[0][i];
            B += map[1][i];
            tempA = A;
            tempB = B;

            for (int j = col - 1; j > i; j--) {
                A += map[1][j];
                B += map[0][j];
            }
            if (i == 0 || Math.abs(A - B) <= Math.abs(finalA - finalB)) {
                if (finalA < A) {
                    finalA = A;
                    finalB = B;
                }
            }

            A = tempA;
            B = tempB;
        }
    }

    private static void print(int tc) {
        System.out.println("#" + tc + " " + finalA + " " + finalB);
        System.out.println();
    }

    private static void read() {
        col = sc.nextInt();
        map = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                map[i][j] = sc.nextInt();
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
}
