import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Dial {
    static int[][] map = new int[3][12];
    static int result;
    static int[] resultMap = new int[6];
    static Scanner sc;

//    // ((A + or - B) + n ) % n công tính khoảng cách di chuyển B -> A theo 2 chiều xuôi ngược
//    int tongNguoc = ((A - B) + 12) % 12;
//    int tongXuoi = ((B - A) + 12) % 12;

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("./src/Dial.txt"));
        sc = new Scanner(System.in);
        int tc = sc.nextInt();
        for (int i = 1; i <= tc; i++) {
            readFileAndInitMap();
            findMinDistance();
            print(i);
        }
    }
    private static void findMinDistance() {
        resultMap = new int[12];
        int[] visited;
        int resultTemp, indexPlus, indexMinus;
        for (int k = 0; k < 12; k++) {
            visited = new int[3];
            resultTemp = 0;
            for (int i = 0; i <= 6; i++) {
                resultTemp++;
                indexPlus = k + i;
                indexMinus = k - i;
                if (indexPlus >= 12) {
                    indexPlus = indexPlus - 12;
                }
                if (indexMinus < 0) {
                    indexMinus = 12 + indexMinus;
                }

                if (visited[0] == 0 && (map[0][indexPlus] == 1 || map[0][indexMinus] == 1)) {
                    visited[0] = resultTemp;
                }
                if (visited[1] == 0 && (map[1][indexPlus] == 1 || map[1][indexMinus] == 1)) {
                    visited[1] = resultTemp;
                }
                if (visited[2] == 0 && (map[2][indexPlus] == 1 || map[2][indexMinus] == 1)) {
                    visited[2] = resultTemp;
                }
                if (visited[0] != 0 && visited[1] != 0 && visited[2] != 0) {
                    resultMap[k] = visited[0] + visited[1] + visited[2] - 3;
                    break;
                }
            }
        }

        for (int i = 0; i < resultMap.length; i++) {
            if (i == 0 || result > resultMap[i]) {
                result = resultMap[i];
            }
        }
    }

    private static void print(int tc) {
        System.out.println("# " + tc + " " + result);
    }

    private static void readFileAndInitMap() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 12; j++) {
                map[i][j] = sc.nextInt();
            }
        }
    }
}