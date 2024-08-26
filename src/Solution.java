import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {
    static int row, coll, strRow, strColl, endR, endC;
    static String[][] map;
    static boolean[][] visited;
    static int[][] direction = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    static Queue<Node> queue;
    static Scanner sc;
    static Node[] sToKey;
    static int[][] keyToKey;
//    static int[] endToKey;

    static class Node {
        int x, y, step;

        public Node(int x, int y, int step) {
            this.x = x;
            this.y = y;
            this.step = step;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("./src/BFS_SABCE.txt"));
        sc = new Scanner(System.in);

        int tc = sc.nextInt();
        for (int i = 1; i <= tc; i++) {
            init();
            process();
            System.out.println("#" + i + " " + min);
        }
    }

    private static void process() {
        bfsSToKey();

        for (int i = 0; i < 4; i++) {
            bfsKeyToKey(i);
        }
        btk(0);

    }

    static int[] btkResult;
    static boolean[] isVisited;
    static int min, sum;

    private static void btk(int index) {

        if (index == 3) {
            sum = sToKey[btkResult[0]].step;
            for (int i = 0; i < 2; i++) {
                sum += keyToKey[btkResult[i]][btkResult[i + 1]];
            }
//            sum += endToKey[btkResult[2]];
            sum += keyToKey[3][btkResult[2]];
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

    private static void bfsKeyToKey(int keyIndex) {
        queue.clear();
        visited = new boolean[row][coll];
        Node node = new Node(keyIndex < 3 ? sToKey[keyIndex].x : endR, keyIndex < 3 ? sToKey[keyIndex].y : endC, 0);
        queue.add(node);

        visited[node.x][node.y] = true;
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

                if (nextX < 0 || nextY < 0 || nextX >= row || nextY >= coll || visited[nextX][nextY]
                        || ("X".equals(map[nextX][nextY]) && keyIndex != 3)) {
                    continue;
                }

                visited[nextX][nextY] = true;
                nextNode = new Node(nextX, nextY, step);
                queue.add(nextNode);
                if ("A".equals(map[nextX][nextY])) {
                        keyToKey[keyIndex][0] = step;
                        continue;
                }
                if ("B".equals(map[nextX][nextY])) {
                        keyToKey[keyIndex][1] = step;
                        continue;
                }
                if ("C".equals(map[nextX][nextY])) {
                        keyToKey[keyIndex][2] = step;
                }

            }

        }
    }

    private static void bfsSToKey() {
        queue.clear();
        Node node = new Node(strRow, strColl, 0);
        queue.add(node);
        visited[strRow][strColl] = true;

        Node nextNode;
        int x, y, nextX, nextY, step;
        while (!queue.isEmpty()) {
            node = queue.poll();
            x = node.x;
            y = node.y;
            step = node.step + 1;

            for (int[] arr : direction) {
                nextX = x + arr[0];
                nextY = y + arr[1];

                if (sToKey[0] != null && sToKey[1] != null && sToKey[2] != null) {
                    return;
                }
                if (nextX < 0 || nextY < 0 || nextX >= row || nextY >= coll || visited[nextX][nextY] || "X".equals(map[nextX][nextY])) {
                    continue;
                }

                visited[nextX][nextY] = true;
                nextNode = new Node(nextX, nextY, step);
                queue.add(nextNode);
                if ("A".equals(map[nextX][nextY])) {
                    sToKey[0] = nextNode;
                }
                if ("B".equals(map[nextX][nextY])) {
                    sToKey[1] = nextNode;
                }
                if ("C".equals(map[nextX][nextY])) {
                    sToKey[2] = nextNode;
                }
            }

        }

    }

    private static void init() {
        row = sc.nextInt();
        coll = sc.nextInt();
        strRow = sc.nextInt() - 1;
        strColl = sc.nextInt() - 1;

        map = new String[row][coll];
        visited = new boolean[row][coll];
        queue = new LinkedList<>();
        sToKey = new Node[3];
        keyToKey = new int[4][3];
//        endToKey = new int[3];

        btkResult = new int[3];
        isVisited = new boolean[3];
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
