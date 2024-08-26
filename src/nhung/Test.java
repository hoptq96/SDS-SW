package nhung;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Test {

    static int[][] map = new int[3][12];
    static int[][] resultMap = new int[3][12];
    static Scanner sc;

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
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 12; j++) {
                int index = 0, indexplus = 0, indexmin = 0, temp = 0;
                if (map[i][j] != 1) {// vì dòng 28 index = 0 rồi nên dùng map[i][j] != 1 để làm điều kiện sẽ hay hơn ==
//                    index = 0;
//                } else {
                    for (int k = j + 1; k <= j + 1 + 6; k++) {
                        System.out.println("k: " + k);
                        if (k >= 12) {
                            k = k - 12; // sai chỗ này, nếu có đồng hồ không có số 1 thì vòng lặp của e sẽ chạy vô hạn
                                        // không nên gán lại k ở đây mà có thể dùng biến tạm. vì k là biến của vòng lặp e gán lại k
                                        // thì k sẽ nhận giá trị mới, nếu k vẫn thỏa mãn điều kiện lặp thì vòng lặp vẫn chạy
                        }
                        indexplus++;
                        if (map[i][k] == 1) {
                            break;
                        }
                    }
                    for (int m = j - 1; m >= j - 1 - 6; m--) {
                        System.out.println("m: " + m);
                        if (m < 0) {
                            m = 12 + m; // sai chỗ này, nếu có đồng hồ không có số 1 thì vòng lặp của e sẽ chạy vô hạn. Tương tự như trên
                        }
                        indexmin++;
                        if (map[i][m] == 1) {
                            break;
                        }

                    }
                    if (indexmin <= indexplus) {
                        index = indexmin;
                    } else {
                        index = indexplus;
                    }

                }
                resultMap[i][j] = index;
            }
        }
    }

    public static void print(int i) {
        int min = 0;
        for (int j = 0; j < 12; j++) {
            if (j == 0 || min >= (resultMap[0][j] + resultMap[1][j] + resultMap[2][j])) {
                min = resultMap[0][j] + resultMap[1][j] + resultMap[2][j];
            }

        }
        System.out.println("#" + i + " " + min);
    }


    public static void readFileAndInitMap() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 12; j++) {
                map[i][j] = sc.nextInt();

            }
//            System.out.println(Arrays.toString(map[i]));

        }
    }
}
