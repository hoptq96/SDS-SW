import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Origami {

    static Scanner sc;
    static int N, M, max = -1, sizeStair;
    static int[][] map;
    static int[][] arrResult;

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("./src/Origami.txt"));
        sc = new Scanner(System.in);
        int tc = sc.nextInt();
        for (int i = 1; i <= tc; i++) {
            readFileAndInit();
            resolve();
            print(i);

            max = 0;
            arrResult = new int[N][M];
        }
    }

    private static void resolve() {
        int nextCol = 0;
        for (int i = 1; i <= M; i++) {
            if (i % 2 != 0) {
                for (int j = 0; j < sizeStair; j++, nextCol++) {
                    for (int k = 0; k < N; k++) {
                        arrResult[k][j] += map[k][nextCol];
                    }
                }
            } else {
                nextCol += sizeStair - 1;
                for (int j = 0; j < sizeStair; j++, nextCol--) {
                    for (int k = 0; k < N; k++) {
                        arrResult[k][j] += map[k][nextCol];
                    }
                }
                nextCol += sizeStair + 1;
            }
        }

        for (int j = 0; j < N; j++) {
            for (int k = 0; k < sizeStair; k++) {
                if (max < arrResult[j][k]) {
                    max = arrResult[j][k];
                }
            }
        }

    }

    private static void readFileAndInit() {
        N = sc.nextInt();
        M = sc.nextInt();

        map = new int[N][N];
        sizeStair = N / M;
        arrResult = new int[N][sizeStair];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = sc.nextInt();
//                System.out.print(map[i][j] + " ");
            }
//            System.out.println();
        }
    }

    private static void print(int tc) {
        System.out.println("#" + tc + " " + max);
//    #1 16
//    #2 31
//    #3 33
    }
}