import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class DistanceSToABCToE {

    static class Node {
        int x, y, step;

        public Node(int x, int y, int step) {
            this.x = x;
            this.y = y;
            this.step = step;
        }
    }

    static int row, coll, strR, strC, endR, endC;
    static String[][] map;
    static boolean[][] visited;
    static int[][] direction = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    static Scanner sc;
    static Queue<Node> queue = new LinkedList();
    static Node[] SToABC;
    static int[][] ABCO;
    static int[] EToABC;

    public static void main(String[] args) throws FileNotFoundException {
        Long a = System.currentTimeMillis();
        System.setIn(new FileInputStream("./src/BFS_SABCE.txt"));
        sc = new Scanner(System.in);

        int tc = sc.nextInt();
        for (int i = 1; i <= tc; i++) {
            init();
            process();

            System.out.println("#" + 1 + " " + min);
        }
        System.out.println("Run time: " + (System.currentTimeMillis() - a));
    }

    private static void process() {
        bfsSToABC();
        for (int i = 0; i < 4; i++) {
            bfsABCE(i);
        }

        btk(0);

    }

    static boolean[] isVisited;
    static int[] btkResult;
    static int min, sum;

    private static void btk(int index) {
        if (index == 3) {
            sum = SToABC[btkResult[0]].step;
            for (int i = 0; i < 2; i++) {
                sum += ABCO[btkResult[i]][btkResult[i + 1]];
            }
            sum += EToABC[btkResult[2]];
            min = Math.min(min, sum);

            return;
        }

        for (int j = 0; j < 3; j++) {
            if (!isVisited[j]) {
                isVisited[j] = true;
                btkResult[index] = j;

                btk(index + 1);
                isVisited[j] = false;
            }
        }

    }

    private static void bfsABCE(int i) {
        queue.clear();
        visited = new boolean[row][coll];
        Node node = new Node(i != 3 ? SToABC[i].x : endR, i != 3 ? SToABC[i].y : endC, 0);
        queue.add(node);

        Node nextNode;
        int x, y, nextX, nextY, step, breakCount = 0;
        while (!queue.isEmpty()) {
            node = queue.poll();
            x = node.x;
            y = node.y;
            step = node.step + 1;

            for (int[] arr : direction) {
                nextX = x + arr[0];
                nextY = y + arr[1];

                if (breakCount == 3) {
                    return;
                }

                if (nextX < 0 || nextY < 0 || nextX >= row || nextY >= coll || visited[nextX][nextY] ||
                        ("X".equals(map[nextX][nextY]) && i != 3)) {
                    continue;
                }

                visited[nextX][nextY] = true;
                nextNode = new Node(nextX, nextY, step);
                queue.add(nextNode);
                if ("A".equals(map[nextX][nextY])) {
                    breakCount += 1;
                    if (i == 3)
                        EToABC[0] = step;
                    else
                        ABCO[i][0] = step;
                    continue;
                }
                if ("B".equals(map[nextX][nextY])) {
                    breakCount += 1;
                    if (i == 3)
                        EToABC[1] = step;
                    else
                        ABCO[i][1] = step;
                    continue;
                }
                if ("C".equals(map[nextX][nextY])) {
                    breakCount += 1;
                    if (i == 3)
                        EToABC[2] = step;
                    else
                        ABCO[i][2] = step;
                }
            }

        }

    }

    private static void bfsSToABC() {
        queue.clear();
        visited = new boolean[row][coll];
        Node node = new Node(strR, strC, 0);
        queue.add(node);

        int x, y, nextX, nextY, step;
        Node nextNode;
        while (!queue.isEmpty()) {
            node = queue.poll();
            x = node.x;
            y = node.y;
            step = node.step + 1;
            for (int[] arr : direction) {
                nextX = x + arr[0];
                nextY = y + arr[1];

                if (SToABC[0] != null && SToABC[1] != null && SToABC[2] != null) {
                    return;
                }
                if (nextX < 0 || nextY < 0 || nextX >= row || nextY >= coll || "X".equals(map[nextX][nextY]) || visited[nextX][nextY]) {
                    continue;
                }
                visited[nextX][nextY] = true;
                nextNode = new Node(nextX, nextY, step);
                queue.add(nextNode);
                if ("A".equals(map[nextX][nextY])) {
                    SToABC[0] = nextNode;
                    continue;
                }
                if ("B".equals(map[nextX][nextY])) {
                    SToABC[1] = nextNode;
                    continue;
                }
                if ("C".equals(map[nextX][nextY])) {
                    SToABC[2] = nextNode;
                }
            }
        }
    }

    private static void init() {
        row = sc.nextInt();
        coll = sc.nextInt();
        strR = sc.nextInt() - 1;
        strC = sc.nextInt() - 1;

        map = new String[row][coll];
        visited = new boolean[row][coll];
        SToABC = new Node[3];
        ABCO = new int[3][3];
        EToABC = new int[3];

        isVisited = new boolean[3];
        btkResult = new int[3];
        min = Integer.MAX_VALUE;

        String line;
        sc.nextLine();
        for (int i = 0; i < row; i++) {
            line = sc.nextLine();
            for (int j = 0; j < coll; j++) {
                map[i][j] = String.valueOf(line.charAt(j));
                if ("E".equals(map[i][j])) {
                    endR = i;
                    endC = j;
                }
            }
        }
    }
}
