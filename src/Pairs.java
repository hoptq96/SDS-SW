import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Pairs {
    static int N;
    static int[][] map;
    static int[] mapResult;
    static Scanner sc;
    static boolean[] visited;
    static int min, max;
    static int temp;
    static boolean isBreak;
    static int[][] oldValueTemp;

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("./src/Pairs.txt"));
        sc = new Scanner(System.in);

        int tc = sc.nextInt();
        for (int i = 1; i <= tc; i++) {
            readFileAndInit();
            process();
            print(i);
        }
    }

    //        #1 14 91    #2 10 100    #3 3 8       #4 6 6      #5 7 13     #6 5 25     #7 9 23     #8 10 47
//        #9 7 21     #10 9 47     #11 11 32    #12 8 34    #13 11 43
//        #14 13 79   #15 16 76    #16 10 76    #17 16 75   #18 14 57   #19 14 59   #20 20 95
    private static void process() {
        BtkMin(0, 0);
    }

    private static void BtkMin(int k, int current) {
        int priority = 0;
        for (int i = 0; i < N; i++) {
            if (!visited[i] && temp < min) {
                visited[i] = true;
                priority = getPriority(current, true);
                temp += priority;
                if (k == N - 1) {
                    if (temp < min) {
                        min = temp;
                    }
                } else {
                    oldValueTemp[current + 1] = new int[N];
                    BtkMin(k + 1, current + 1);
                }
                visited[i] = false;
                temp -= priority;
                mapResult[current] = 0;
            }
        }
    }

    private static void BtkMax(int k, int current) {
        int priority = 0;
        for (int i = 0; i < N; i++) {
            if (!visited[i] && temp < min) {
                visited[i] = true;
                priority = getPriority(current, false);
                temp += priority;
                if (k == N - 1) {
                    if (temp < min) {
                        min = temp;
                    }
                } else {
                    oldValueTemp[current + 1] = new int[N];
                    BtkMax(k + 1, current + 1);
                }
                visited[i] = false;
                temp -= priority;
                mapResult[current] = 0;
            }
        }
    }

    private static int getPriority(int current, boolean isFindMin) {
        if (isFindMin) {
            for (int i = 0; i < N; i++) {
                return getConstantMap(current, i);
            }
        } else {
            for (int i = N - 1; i >= 0; i--) {
                return getConstantMap(current, i);
            }
        }
        return 0;
    }

    static int getConstantMap(int current, int i) {
        List objList = Arrays.asList(toObject(mapResult));
        List oldValueList = Arrays.asList(toObject(oldValueTemp[current]));
        if (!objList.contains(Integer.valueOf(map[current][i])) && !oldValueList.contains(Integer.valueOf(map[current][i]))) {
            oldValueTemp[current][i] = map[current][i];
            mapResult[current] = map[current][i];
            return i + 1;
        }
        return 0;
    }

    private static void readFileAndInit() {
        N = sc.nextInt();
        map = new int[N][N];
        mapResult = new int[N];
        visited = new boolean[N];
        temp = 0;
        min = Integer.MAX_VALUE;
        max = N * N;
        isBreak = false;
        oldValueTemp = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = sc.nextInt();
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Convert int[] to Integer[]
    public static Integer[] toObject(int[] intArray) {

        Integer[] result = new Integer[intArray.length];
        for (int i = 0; i < intArray.length; i++) {
            result[i] = Integer.valueOf(intArray[i]);
        }
        return result;

    }

    private static void print(int tc) {
        System.out.println("#" + tc + " " + min);
    }
}
