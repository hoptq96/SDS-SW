package nhung;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Dial {
    static int[][] map = new int[3][12];
    static int result;
    static Scanner sc;

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("./src/Dial.txt"));
        sc = new Scanner(System.in);
        int tc = sc.nextInt();
        for (int i = 1; i <= tc; i++) {
            readFileAndInitMap();
            process();
            printResult(i);
        }
    }

    private static void process() {
        for (int i = 0; i < 12; i++) { // đây là giờ chỉ định
            int distanceTemp = 0;
            int[] visited = new int[3]; // dùng để đánh dấu đồng hồ nào đã tìm được khoảng cách để di chuyển số 1 tới giờ chỉ định
            for (int j = 0; j <= 6; j++) { // đây là số giờ trước hoặc sau giờ chỉ định
                // (<=6 vì khoảng cách xa nhất của 1 đồng hồ nêếu đi theo 2 chiều thì sẽ là 6)
                distanceTemp++;

//                tìm khoảng cách để di chuyển số 1 tới giờ chỉ định theo chiều tăng
                checkPlusHour(i, j, visited, distanceTemp);
//                tìm khoảng cách để di chuyển số 1 tới giờ chỉ định theo chiều giảm
                checkMinusHour(i, j, visited, distanceTemp);

//                so sánh kết quả sau khi tìm được khoảng cách để di chuyển số 1 tới giờ chỉ định
                if (visited[0] != 0 && visited[1] != 0 && visited[2] != 0) {
//                    -3 vì i = 0 thì resultTemp++ mà đúng ra tại vị trí i = 0 thì khoảng cách là 0;
//                    Vẫn để resultTemp++ kể cả khi i = 0 vì mình đang dùng visited[0] == 0 để đánh dấu đồng hồ nào đã tìm được
//                      khoảng cách để di chuyển số 1 tới giờ chỉ định
                    distanceTemp = visited[0] + visited[1] + visited[2] - 3;

                    result = Math.min(result, distanceTemp);
                    break;
                }
            }
        }
    }

    private static void checkPlusHour(int hourPoint, int j, int[] visited, int distanceTemp) {
        int indexPlus = hourPoint + j;

        // đồng hồ thì có 12h thôi nên quá 12h(0h) sẽ thành 1h
        if (indexPlus >= 12) {
            indexPlus = indexPlus - 12;
        }
        // Kiểm tra xem đồng hồ số 0 nếu chưa tìm được khoảng cách và vị trí đang duyệt đến là số 1 thì gán khoảng cách cho biến để lát tính toán
        if (visited[0] == 0 && map[0][indexPlus] == 1) {
            visited[0] = distanceTemp;
        }
        // tương tự với đồng hồ 1 và 2
        if (visited[1] == 0 && map[1][indexPlus] == 1) {
            visited[1] = distanceTemp;
        }
        if (visited[2] == 0 && map[2][indexPlus] == 1) {
            visited[2] = distanceTemp;
        }
    }

    private static void checkMinusHour(int hourPoint, int j, int[] visited, int distanceTemp) {
        int indexMinus = hourPoint - j;
        if (indexMinus < 0) {
            indexMinus = 12 + indexMinus;
        }

        if (visited[0] == 0 && map[0][indexMinus] == 1) {
            visited[0] = distanceTemp;
        }
        if (visited[1] == 0 && map[1][indexMinus] == 1) {
            visited[1] = distanceTemp;
        }
        if (visited[2] == 0 && map[2][indexMinus] == 1) {
            visited[2] = distanceTemp;
        }
    }


    private static void printResult(int tc) {
        System.out.println("# " + tc + " " + result);
    }

    private static void readFileAndInitMap() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 12; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        result = Integer.MAX_VALUE;
    }
}