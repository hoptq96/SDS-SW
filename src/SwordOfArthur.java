import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class SwordOfArthur {

    static Scanner sc;
    static String[][] map;
    static int row, col, minResult, MOD = 1000;
    static int[] keyPosition;
    static int[][][] distance;
    static int[][] minDistanceToPoint;
    static int[][] direction = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    static Queue<Integer> queue = new LinkedList<>();
    static boolean[] visited = new boolean[3];
    static int kc = 0;

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("./src/BFS_ABC.txt"));
        sc = new Scanner(System.in);
        int tc = sc.nextInt();
        for (int i = 1; i <= tc; i++) {
            readAndInit();
            for (int j = 0; j < 5; j++) {
                BFS(j);
                getMinDistanceToABC(j);
            }
            minResult = Integer.MAX_VALUE;
            visited = new boolean[3];
            BTK(0, 0); // tim do dai ngan nhat
            print(i);
        }
    }

    private static void BTK(int i, int current) {
//        minDistanceToPoint: Start - A - B - C - S
        for (int j = 0; j < 3; j++) {
            if (!visited[j]) {
                kc += minDistanceToPoint[current][j];
                visited[j] = true;
                if (i == 2) {
                    kc += minDistanceToPoint[4][j];
                    if (minResult > kc) minResult = kc;
                    kc -= minDistanceToPoint[4][j];
                } else {
                    BTK(i + 1, j + 1);
                }
                visited[j] = false;
                kc -= minDistanceToPoint[current][j];
            }
        }
    }

    private static void BFS(int i) {
        queue.clear();
        queue.add(keyPosition[i]);
        distance[i][keyPosition[i] / MOD][keyPosition[i] % MOD] = 1;
        while (!queue.isEmpty()) {
            int curPosition = queue.poll();
            int x = curPosition / MOD;
            int y = curPosition % MOD;

            int nextRow, nextCol;
            for (int[] ints : direction) {
                nextRow = x + ints[0];
                nextCol = y + ints[1];

                if (nextRow >= 0 && nextCol >= 0 && nextRow < row && nextCol < col && distance[i][nextRow][nextCol] == 0) {
                    if (i == 4 || !"X".equals(map[nextRow][nextCol])) {
                        distance[i][nextRow][nextCol] = distance[i][x][y] + 1;
                        queue.add(nextRow * MOD + nextCol);
                    }
                }
            }
        }
    }

    static void getMinDistanceToABC(int j) {
        for (int i = 0; i < 3; i++) {
            minDistanceToPoint[j][i] = (distance[j][keyPosition[i + 1] / MOD][keyPosition[i + 1] % MOD]) - 1;
        }
    }

    private static void print(int tc) {
//        int min = minDistanceToPoint[0][0] + minDistanceToPoint[1][1] + minDistanceToPoint[2][2] + minDistanceToPoint[4][2];
//        minResult = min;
//        min = minDistanceToPoint[0][0] + minDistanceToPoint[1][2] + minDistanceToPoint[3][1] + minDistanceToPoint[4][1];
//        if (minResult > min) {
//            minResult = min;
//        }
//        min = minDistanceToPoint[0][1] + minDistanceToPoint[2][0] + minDistanceToPoint[1][2] + minDistanceToPoint[4][2];
//        if (minResult > min) {
//            minResult = min;
//        }
//        min = minDistanceToPoint[0][1] + minDistanceToPoint[2][2] + minDistanceToPoint[3][0] + minDistanceToPoint[4][0];
//        if (minResult > min) {
//            minResult = min;
//        }
//        min = minDistanceToPoint[0][2] + minDistanceToPoint[3][0] + minDistanceToPoint[1][1] + minDistanceToPoint[4][1];
//        if (minResult > min) {
//            minResult = min;
//        }
//        min = minDistanceToPoint[0][2] + minDistanceToPoint[3][1] + minDistanceToPoint[2][0] + minDistanceToPoint[4][0];
//        if (minResult > min) {
//            minResult = min;
//        }

        System.out.println("#" + tc + " " + minResult);
    }

    private static void readAndInit() {
        row = sc.nextInt();
        col = sc.nextInt();
        distance = new int[5][row][col];
        keyPosition = new int[5];
        keyPosition[0] = (sc.nextInt() - 1) * MOD + (sc.nextInt() - 1);
        map = new String[row][col];
        minDistanceToPoint = new int[5][3];

        sc.nextLine();
        for (int i = 0; i < row; i++) {
            String line = sc.nextLine();
            for (int j = 0; j < col; j++) {
                map[i][j] = String.valueOf(line.charAt(j));
                if ("A".equals(map[i][j])) {
                    keyPosition[1] = i * MOD + j;
                }
                if ("B".equals(map[i][j])) {
                    keyPosition[2] = i * MOD + j;
                }
                if ("C".equals(map[i][j])) {
                    keyPosition[3] = i * MOD + j;
                }
                if ("S".equals(map[i][j])) {
                    keyPosition[4] = i * MOD + j;
                }
//                System.out.print(map[i][j]);
            }
//            System.out.println();
        }
    }

//    #1 12
//    #2 15
//    #3 4
//    #4 4
//    #5 19
//    #6 47
//    #7 16
//    #8 20
//    #9 16
//    #10 16
}
