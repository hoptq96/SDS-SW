import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Pair_Temp {

    static int n;
    static int[][] rank;
    static int[] groupPeople;
    static int[] visited;
    static int min, max;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("./src/Pairs.txt"));
        int tc = sc.nextInt();
        int x;
        tc = 1;
        for (int t = 1; t <= tc; t++) {
            n = sc.nextInt();
            rank = new int[n + 1][n + 1];
//            Tính thứ tự ưu tiên cho người vừa nhập theo từng hàng (row)
            for (int i = 1; i <= n; i++) { // row
                for (int j = 1; j <= n; j++) { // thứ tự ưu tiên
                    x = sc.nextInt(); // người mang thứ tự ưu tiên j
                    rank[i][x] = j;
                }
                System.out.println(Arrays.toString(rank[i]));
            }

            groupPeople = new int[n + 1];
            visited = new int[n + 1];
            min = Integer.MAX_VALUE;
            max = Integer.MIN_VALUE;
            BTK(1);
            System.out.println("#" + t + " " + min + " " + max);
        }
    }

//    backtracking xếp người thành 1 nhóm và tính độ ưu tiên từ nhóm vừa xếp
    static void BTK(int i) {
//        Đk dừng
        if (i == n + 1) {
            int sum = 0;
            for (int k = 1; k <= n; k++) {
                sum += rank[k][groupPeople[k]];
            }
            min = Math.min(sum, min);
            max = Math.max(sum, max);
            System.out.println("GroupPeople: " + Arrays.toString(groupPeople));
            return;
        }

//        Xếp người thành 1 group theo cách hoán vị
        for (int h = 1; h <= n; h++) {
            if (visited[h] == 0) {
                groupPeople[i] = h;
                visited[h] = 1;
                BTK(i + 1);
                visited[h] = 0;

            }
        }
    }
}
