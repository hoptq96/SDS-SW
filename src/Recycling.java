import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Recycling {
    static Scanner sc;
    static int N, resultMin = 0, MOD = 1000;
    static int[][] map;
    static int[][] recycleStationMap;
    static int[][] direction = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    static int[][][] minDistanceToTrashMap;
    static Queue<Integer> queue;
    static int[][][] commonTrashStation;

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("./src/recycling.txt"));
        sc = new Scanner(System.in);
        int tc = sc.nextInt();
        for (int i = 1; i <= tc; i++) {
            N = sc.nextInt();
            readFileAndInitMap();
            BFS();
            findMinStep();
            print(i);
        }
    }

    // không có rác là 0, giấy được đánh dấu là 1, nhựa là 2, lon là 3,
    // 4 thùng rác lần lượt để chứa giấy, nhựa, lon, chứa all.
    // Thùng rác chứa all có thể chứa mỗi loại rác rối đa 1 vật phẩm.
    private static void BFS() {
        for (int i = 1; i < recycleStationMap.length; i++) {
            queue.clear();
            minDistanceToTrashMap[0] = new int[N][N];
            queue.add(recycleStationMap[i][0] * MOD + recycleStationMap[i][1]);
            minDistanceToTrashMap[0][recycleStationMap[i][0]][recycleStationMap[i][1]] = 1;
            int currentPosition, x, y, neighBorX, neighBorY;
            while (!queue.isEmpty()) {
                currentPosition = queue.poll();
                x = currentPosition / MOD;
                y = currentPosition % MOD;

                for (int j = 0; j < direction.length; j++) {
                    neighBorX = x + direction[j][0];
                    neighBorY = y + direction[j][1];
                    if (neighBorX >= 0 && neighBorY >= 0 && neighBorX < N && neighBorY < N && minDistanceToTrashMap[0][neighBorX][neighBorY] == 0) {
                        queue.add(neighBorX * MOD + neighBorY);
                        minDistanceToTrashMap[0][neighBorX][neighBorY] = minDistanceToTrashMap[0][x][y] + 1;
                        if (i == map[neighBorX][neighBorY]) {
                            minDistanceToTrashMap[i][neighBorX][neighBorY] = minDistanceToTrashMap[0][neighBorX][neighBorY] - 1;
                        } else if (i == 4) {
                            if (map[neighBorX][neighBorY] == 1) {
                                commonTrashStation[1][neighBorX][neighBorY] = minDistanceToTrashMap[0][neighBorX][neighBorY] - 1;
                            }
                            if (map[neighBorX][neighBorY] == 2) {
                                commonTrashStation[2][neighBorX][neighBorY] = minDistanceToTrashMap[0][neighBorX][neighBorY] - 1;
                            }
                            if (map[neighBorX][neighBorY] == 3) {
                                commonTrashStation[3][neighBorX][neighBorY] = minDistanceToTrashMap[0][neighBorX][neighBorY] - 1;
                            }
                        }
                    }
                }
            }
        }
    }

    static void findMinStep() {
        int min = 0;
        for (int i = 1; i < minDistanceToTrashMap.length; i++) {
            int temp = 0;
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    if (minDistanceToTrashMap[i][j][k] > 0) {
                        min += minDistanceToTrashMap[i][j][k];
                        if ((minDistanceToTrashMap[i][j][k] - commonTrashStation[i][j][k] > 0) && temp < (minDistanceToTrashMap[i][j][k] - commonTrashStation[i][j][k])) {
                            temp = minDistanceToTrashMap[i][j][k] - commonTrashStation[i][j][k];
                        }
                    }
                }
            }
            min -= temp;
            resultMin += min;
        }

//        #1 19
//        #2 63
//        #3 94
    }

    private static void readFileAndInitMap() {
        queue = new LinkedList();
        map = new int[N][N];
        minDistanceToTrashMap = new int[N + 1][N][N];
        commonTrashStation = new int[N][N][N];
        recycleStationMap = new int[N + 1][2];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = sc.nextInt();
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        for (int i = 1; i <= 4; i++) {
            recycleStationMap[i][0] = sc.nextInt() - 1;
            recycleStationMap[i][1] = sc.nextInt() - 1;
            System.out.print(recycleStationMap[i][0] + " " + recycleStationMap[i][1]);
            System.out.println();
        }
    }

    private static void print(int tc) {
        System.out.println("#" + tc + " " + resultMin);
        resultMin = 0;
    }
}
