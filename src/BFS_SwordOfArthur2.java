import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BFS_SwordOfArthur2 {
    static Scanner sc;
    //Size of map
    static int R, C;
    //start position
    static int StrRow, StrCol;
    //end position
    static int EndRow, EndCol;
    static char[][] map;
    //save position A = 0, B = 1, C = 2
    static int[] keyPosition = new int[3];
    //to save position
    static int MOD = 10000;

    /*  map to save min distance from
    0 : start position
    1, 2, 3: A, B, C (co the k dung thu tu)
    4: end position
    */
    static int[][][] distance = new int[5][][];
    //use for BFS
    static Queue<Integer> queue = new LinkedList<>();
    //map lưu khoảng cách tối thiểu giữa 2 cặp điểm
    static int[][] distanceOf2Position;
    //direction
    static int[][] direction = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    //biến hỗ trợ backtracking
    static boolean[] visit;
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
//find distance from 'Start, A, B, C, End' to other point
// tính khoảng cách ngắn nhất từ điểm "i" tới tất cả các điểm còn lại
        for (int i = 0; i < 5; i++) {
            BFS(i);
        }

//save distance follow map
        /*Start: 0, A: 1, B: 2, C: 3*/
        /* Ví dụ k/cách từ Start đến A là minDist[0][1],
         * từ A đến C là minDist[1][3],...*/
        for (int i = 0; i < 4; i++) {
            for (int j = 1; j < 4; j++) {
//            //keyPosition[i - 1]: q lưu vị trí của A, B, C là index 0, 1, 2. mà duyệt từ i = 1 nên trừ i - 1
                // Trừ 1 vì mảng distance khời tạo giá trị cho vị trí ban đầu là 1
                distanceOf2Position[i][j] = distance[i][keyPosition[j - 1] / MOD][keyPosition[j - 1] % MOD] - 1;
            }
        }

//Đến đây dùng tiếp Backtracking để vét hết tất cả các trường hợp
        /*vì có nhiều trường hợp từ nguồn đến A, B, C và phải đi qua hết nên k có cách cộng thông thường nào đưa ra kq chính xác*/

    //Khởi tạo cho backtracking
        visit = new boolean[4];
        min = Integer.MAX_VALUE;
        sum = 0;

    //BTK từ vị trí current là Start = 0
        BTK(0, 0);
    }

    private static void BTK(int current, int k) {
        if (k == 3) { // Dừng tại 3 vì chỉ có 3 điểm A, B, C
            //Cộng thêm khoảng cách từ điểm cuối cùng là A, B, C đến điểm END
            int tmp = sum + distance[4][keyPosition[current - 1] / MOD][keyPosition[current - 1] % MOD] - 1;
            if (tmp < min) min = tmp;
        }
        for (int j = 1; j < 4; j++) {
            if (!visit[j]) {
                visit[j] = true;
                sum += distanceOf2Position[current][j];
                BTK(j, k + 1);
                sum -= distanceOf2Position[current][j];
                visit[j] = false;
            }
        }
    }

    private static void BFS(int i) {
        queue.clear();
        if (i == 0) {
            distance[i][StrRow][StrCol] = 1;
            queue.add(StrRow * MOD + StrCol); // add first element
        } else if (i == 4) {
            distance[i][EndRow][EndCol] = 1;
            queue.add(EndRow * MOD + EndCol);
        } else {
            distance[i][keyPosition[i - 1] / MOD][keyPosition[i - 1] % MOD] = 1;
            queue.add(keyPosition[i - 1]); // add first element
        }

        int x, y, u, v;
        while (!queue.isEmpty()) {
            int temp = queue.poll();
            x = temp / MOD;
            y = temp % MOD;
            for (int t = 0; t < 4; t++) {
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
        StrRow = sc.nextInt() - 1;
        StrCol = sc.nextInt() - 1;

//init map
        map = new char[R][C];
        for (int i = 0; i < 5; i++) {
            distance[i] = new int[R][C];
        }
        distanceOf2Position = new int[4][4];

        sc.nextLine();
//create a String to save map
        String temp;
        for (int i = 0; i < R; i++) {
            temp = sc.nextLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = temp.charAt(j);
                if (map[i][j] == 'S') {
                    EndRow = i;
                    EndCol = j;
                }

//Lợi dụng sự liên tiếp của các kí tự theo ASCII
//Lưu A vào index 0, B vào 1, C vào 2 vì 'A' - 'A' = 0, 'B' - 'A' = 1, 'C' - 'A' = 2
                if (map[i][j] == 'A' || map[i][j] == 'B' || map[i][j] == 'C') {
                    int location = map[i][j] - 'A';
                    keyPosition[location] = i * MOD + j;
                }
            }
        }
    }


}
