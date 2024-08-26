package study;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BFS_S_ABC_E {

    static int row, col, strR, strC, endR, endC;
    static String[][] map;
    static int[][] direction = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    static int[][] distance;
    static Node[][] SABCO;
    static Queue<Node> queue;
    static BufferedReader bf;

    static class Node {
        int x, y, step;

        public Node(int x, int y, int step) {
            this.x = x;
            this.y = y;
            this.step = step;
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(Files.newInputStream(Paths.get("./src/study/BFS_S_ABC_E.txt")));
        bf = new BufferedReader(new InputStreamReader(System.in));

        int totalTc = Integer.parseInt(bf.readLine());
        for (int i = 1; i <= totalTc; i++) {
            init();
            bfsSToABC();
            for (int j = 1; j <= 4; j++) {
                clearData();
                bfsABCE(j);
            }
            btk(0);
            System.out.println("#" + i + " " + min);
        }
    }

    static boolean[] visited = new boolean[3];
    static int[] btkResult = new int[3];
    static int min;

    private static void btk(int index) {
        if (index == 3) {
            int temp = SABCO[0][btkResult[0]].step;
            for (int j = 1; j < 3; j++) {
                temp += SABCO[btkResult[j - 1] + 1][btkResult[j]].step;
            }
            temp += SABCO[4][btkResult[2]].step;

            if (min > temp) min = temp;
            return;
        }

        for (int j = 0; j < 3; j++) {
            if (!visited[j]) {
                visited[j] = true;
                btkResult[index] = j;

                btk(index + 1);
                visited[j] = false;
            }
        }
    }

    private static void clearData() {
        queue = new LinkedList<>();
        distance = new int[row][col];
    }

    private static void bfsABCE(int abcIndex) {
        Node node = abcIndex != 4 ? SABCO[0][abcIndex - 1] : new Node(endR, endC, 0);
        distance[node.x][node.y] = 1;
        queue.add(node);
        if (abcIndex != 4) SABCO[abcIndex][abcIndex - 1] = new Node(node.x, node.y, 0);

        int nextX, nextY;
        Node nextNode;
        while (!queue.isEmpty()) {
            node = queue.poll();
            for (int[] dir : direction) {
                nextX = node.x + dir[0];
                nextY = node.y + dir[1];

                if (SABCO[abcIndex][0] != null && SABCO[abcIndex][1] != null && SABCO[abcIndex][2] != null) return;
                if (nextX < 0 || nextY < 0 || nextX >= row || nextY >= col || distance[nextX][nextY] > 0) continue;
                if (abcIndex != 4 & map[nextX][nextY].equals("X")) continue;

                nextNode = new Node(nextX, nextY, distance[node.x][node.y]);
                distance[nextX][nextY] = distance[node.x][node.y] + 1;
                queue.add(nextNode);

                if ("A".equals(map[nextX][nextY])) SABCO[abcIndex][0] = nextNode;
                if ("B".equals(map[nextX][nextY])) SABCO[abcIndex][1] = nextNode;
                if ("C".equals(map[nextX][nextY])) SABCO[abcIndex][2] = nextNode;

            }
        }
    }

    private static void bfsSToABC() {
        Node node = new Node(strR, strC, 0);
        distance[strR][strC] = 1;
        queue.add(node);

        int nextX, nextY;
        Node nextNode;
        while (!queue.isEmpty()) {
            node = queue.poll();
            for (int[] dir : direction) {
                nextX = node.x + dir[0];
                nextY = node.y + dir[1];

                if (SABCO[0][0] != null && SABCO[0][1] != null && SABCO[0][2] != null) return;

                if (nextX < 0 || nextY < 0 || nextX >= row || nextY >= col ||
                        map[nextX][nextY].equals("X") || distance[nextX][nextY] > 0) continue;

                nextNode = new Node(nextX, nextY, distance[node.x][node.y]);
                distance[nextX][nextY] = distance[node.x][node.y] + 1;
                queue.add(nextNode);

                if ("A".equals(map[nextX][nextY])) SABCO[0][0] = nextNode;
                if ("B".equals(map[nextX][nextY])) SABCO[0][1] = nextNode;
                if ("C".equals(map[nextX][nextY])) SABCO[0][2] = nextNode;
            }
        }
    }

    static void init() throws IOException {
        String[] fistLine = bf.readLine().split(" ");
        row = Integer.parseInt(fistLine[0]);
        col = Integer.parseInt(fistLine[1]);
        strR = Integer.parseInt(fistLine[2]) - 1;
        strC = Integer.parseInt(fistLine[3]) - 1;

        map = new String[row][col];
        distance = new int[row][col];
        queue = new LinkedList<>();
        SABCO = new Node[5][3];

        visited = new boolean[3];
        btkResult = new int[3];
        min = Integer.MAX_VALUE;

        String line;
        for (int i = 0; i < row; i++) {
            line = bf.readLine();
            for (int j = 0; j < col; j++) {
                map[i][j] = String.valueOf(line.charAt(j));
                if ("E".equals(map[i][j])) {
                    endR = i;
                    endC = j;
                }
            }
            System.out.println(Arrays.toString(map[i]));
        }
    }
}