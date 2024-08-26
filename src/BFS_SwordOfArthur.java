import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BFS_SwordOfArthur {

    static Scanner sc;
    //Size of map
    static int R, C;
    //start position
    static int StrRow, StrCol;
    //end position
    static int EndRow, EndCol;
    static char map[][];
    //save position A, B, C
    static int[] keyPosition = new int[3];
    //to save position
    static int MOD = 10000;
    /*  map to save min distance from
    0 : start position
    1, 2, 3: A, B, C
    4: end position
    */
    static int[][][] distance = new int[5][][];
    static Queue<Integer> queue = new LinkedList<>();
    static int[][] minDist;
    static int direction[][] = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    //biến hỗ trợ backtracking
    static int[] visit;
    static int min, sum;

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("./src/BFS_ABC.txt"));
        sc = new Scanner(System.in);
        int TC = sc.nextInt();
        for (int tc = 1; tc <= TC; tc++) {
            read();
            process();
            System.out.println("#" + tc + " " + min);
        }
    }

    private static void process() {
        //find min distance from start, A, B, C, end to all
        for (int i = 0; i < 5; i++) {
            BFS(i);
        }

        for (int i = 1; i < 4; i++) {
            minDist[0][i] = distance[0][keyPosition[i - 1] / MOD][keyPosition[i - 1] % MOD] - 1;
            for (int j = 1; j < 4; j++) {
                minDist[i][j] = distance[i][keyPosition[j - 1] / MOD][keyPosition[j - 1] % MOD] - 1;
            }
        }
        visit = new int[4];
        min = Integer.MAX_VALUE;
        sum = 0;
        BTK(0, 0);
    }

    private static void BTK(int k, int current) {
        if (k == 3) {
            int tmp = sum + distance[4][keyPosition[current - 1] / MOD][keyPosition[current - 1] % MOD] - 1;
            if (tmp < min) min = tmp;
        }
        for (int i = 1; i < 4; i++) {
            if (visit[i] == 0) {
                visit[i] = 1;
                sum += minDist[current][i];
                BTK(k + 1, i);
                sum -= minDist[current][i];
                visit[i] = 0;
            }
        }
    }

    private static void BFS(int i) {
        queue.clear();
        // add first element
        if (i == 0) {
            distance[i][StrRow][StrCol] = 1;
            queue.add(StrRow * MOD + StrCol);
        } else if (i == 4) {
            distance[i][EndRow][EndCol] = 1;
            queue.add(EndRow * MOD + EndCol);
        } else {
            distance[i][keyPosition[i - 1] / MOD][keyPosition[i - 1] % MOD] = 1;
            queue.add(keyPosition[i - 1]);
        }

        int x, y, u, v;
        while (!queue.isEmpty()) {
            int temp = queue.poll();
            x = temp / MOD;
            y = temp % MOD;
            for (int t = 0; t < direction.length; t++) {
                u = x + direction[t][0];
                v = y + direction[t][1];
                if (u >= 0 && u < R && v >= 0 && v < C && distance[i][u][v] == 0) {
                    if (i != 4 && map[u][v] == 'X') continue;
                    distance[i][u][v] = distance[i][x][y] + 1;
                    queue.add(u * MOD + v);
                }
            }
        }
    }

    private static void read() {
//get size of map
        R = sc.nextInt();
        C = sc.nextInt();
//get start position
        StrRow = sc.nextInt() -1;
        StrCol = sc.nextInt() -1;
//init map
        map = new char[R][C];
        for (int i = 0; i < 5; i++) {
            distance[i] = new int[R][C];
        }
        minDist = new int[4][4];
        sc.nextLine();
        String temp;
        for (int i = 0; i < R; i++) {
            temp = sc.nextLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = temp.charAt(j);
                if (map[i][j] == 'S') {
                    EndRow = i;
                    EndCol = j;
                }
                if (map[i][j] == 'A' || map[i][j] == 'B' || map[i][j] == 'C') {
                    int location = map[i][j] - 'A';
                    keyPosition[location] = i * MOD + j;
                }
            }
        }
    }
}
